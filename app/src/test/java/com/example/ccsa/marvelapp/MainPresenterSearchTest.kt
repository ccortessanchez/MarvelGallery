@file:Suppress("IllegalIdentifier")

package com.example.ccsa.marvelapp

import com.example.ccsa.marvelapp.presenter.MainPresenter
import io.reactivex.Single
import org.junit.Assert.*
import org.junit.Test
import io.reactivex.android.plugins.RxAndroidPlugins
import io.reactivex.plugins.RxJavaPlugins
import io.reactivex.schedulers.Schedulers
import org.junit.Before
import sharedTest.com.example.ccsa.marvelapp.helpers.BaseMainView
import sharedTest.com.example.ccsa.marvelapp.helpers.BaseMarvelRepository

class MainPresenterSearchTest {

    @Before
    fun setUp() {
        RxAndroidPlugins.setInitMainThreadSchedulerHandler { Schedulers.trampoline() }
        RxJavaPlugins.setIoSchedulerHandler { Schedulers.trampoline() }
        RxJavaPlugins.setComputationSchedulerHandler { Schedulers.trampoline() }
        RxJavaPlugins.setNewThreadSchedulerHandler { Schedulers.trampoline() }
    }

    @Test
    fun `After start, there is request for with null query`() {
        assertOnAction { onViewCreated() } searchQueryIsEqualTo null
    }

    @Test
    fun `For blank text, there is request with null query`() {
        for (emptyText in listOf("", "   ", "       "))
            assertOnAction { onSearchChanged(emptyText) } searchQueryIsEqualTo null
    }

    @Test
    fun `Search query is the same as provided text when not blank`() {
        for (text in listOf("KKO", "HJ HJ", "And so what?"))
            assertOnAction { onSearchChanged(text) } searchQueryIsEqualTo text
    }

    private fun assertOnAction(action: MainPresenter.() -> Unit) = PresenterActionAssertion(action)

    private class PresenterActionAssertion(val actionOnPresenter: MainPresenter.() -> Unit) {

        infix fun searchQueryIsEqualTo(expectedQuery: String?) {
            var checkApplied = false
            val view = BaseMainView(onShowError = { fail() })
            val marvelRepository = BaseMarvelRepository { searchQuery ->
                assertEquals(expectedQuery, searchQuery)
                checkApplied = true
                Single.never()
            }
            val mainPresenter = MainPresenter(view, marvelRepository)
            mainPresenter.actionOnPresenter()
            assertTrue(checkApplied)
        }
    }
}

