package com.example.ccsa.marvelapp

import com.example.ccsa.marvelapp.data.MarvelRepository
import com.example.ccsa.marvelapp.model.MarvelCharacter
import com.example.ccsa.marvelapp.presenter.MainPresenter
import com.example.ccsa.marvelapp.view.main.MainView
import io.reactivex.Single
import io.reactivex.android.plugins.RxAndroidPlugins
import io.reactivex.plugins.RxJavaPlugins
import io.reactivex.schedulers.Schedulers
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import sharedTest.com.example.ccsa.marvelapp.helpers.BaseMainView
import sharedTest.com.example.ccsa.marvelapp.helpers.BaseMarvelRepository
import sharedTest.com.example.ccsa.marvelapp.helpers.Example

@Suppress("IllegalIdentifier")
class MainPresenterTest {

    @Before
    fun setUp() {
        RxAndroidPlugins.setInitMainThreadSchedulerHandler { Schedulers.trampoline() }
        RxJavaPlugins.setIoSchedulerHandler { Schedulers.trampoline() }
        RxJavaPlugins.setComputationSchedulerHandler { Schedulers.trampoline() }
        RxJavaPlugins.setNewThreadSchedulerHandler { Schedulers.trampoline() }
    }

    @Test fun `When presenter is waiting for response, refresh is displayed`() {
        // Given
        val view = BaseMainView(refresh = false)
        val marvelRepository = BaseMarvelRepository(
                onGetCharacters = {
                    Single.fromCallable {
                        // Then
                        assertTrue(view.refresh)
                        Example.exampleCharacterList
                    }
                }
        )
        val mainPresenter = MainPresenter(view, marvelRepository)
        view.onShow = {_ ->
            // Then
            assertTrue(view.refresh)
        }
        // When
        mainPresenter.onViewCreated()
        // Then
        assertFalse(view.refresh)
    }

    @Test fun `After view was created, list of characters is loaded and displayed`() {
        assertOnAction { onViewCreated() }.thereIsSameListDisplayed()
    }

    @Test fun `New list is shown after view was refreshed`() {
        assertOnAction { onRefresh() }.thereIsSameListDisplayed()
    }

    @Test fun `When API returns error, it is displayed on view`() {
        // Given
        val someError = Error()
        var errorDisplayed: Throwable? = null
        val view = BaseMainView(
                onShow = { _ -> fail() },
                onShowError = { errorDisplayed = it }
        )

        val marvelRepository = BaseMarvelRepository { Single.error(someError) }
        val mainPresenter = MainPresenter(view, marvelRepository)
        // When
        mainPresenter.onViewCreated()
        // Then
        assertEquals(someError, errorDisplayed)
    }

    private fun assertOnAction(action: MainPresenter.() -> Unit) = PresenterActionAssertion(action)

    private class PresenterActionAssertion(val actionOnPresenter: MainPresenter.() -> Unit) {

        fun thereIsSameListDisplayed() {
            // Given
            val exampleCharacterList = listOf(
                    MarvelCharacter("ExampleName", "ExampleImageUrl"),
                    MarvelCharacter("Name1", "ImageUrl1"),
                    MarvelCharacter("Name2", "ImageUrl2")
            )

            var displayedList: List<MarvelCharacter>? = null

            val view = BaseMainView(
                    onShow = { items -> displayedList = items },
                    onShowError = { fail() }
            )

            val marvelRepository = BaseMarvelRepository(
                    onGetCharacters = { Single.just(Example.exampleCharacterList) }
            )

            val mainPresenter = MainPresenter(view, marvelRepository)

            // When
            mainPresenter.actionOnPresenter()

            // Then
            assertEquals(exampleCharacterList, displayedList)
        }
    }
}