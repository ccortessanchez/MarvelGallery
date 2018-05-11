package sharedTest.com.example.ccsa.marvelapp.helpers

import com.example.ccsa.marvelapp.model.MarvelCharacter
import com.example.ccsa.marvelapp.view.main.MainView

class BaseMainView(
        var onShow: (items: List<MarvelCharacter>) -> Unit = {},
        val onShowError: (error: Throwable) -> Unit = {},
        override var refresh: Boolean = false
) : MainView {

    override fun show(items: List<MarvelCharacter>) {
        onShow(items)
    }

    override fun showError(error: Throwable) {
        onShowError(error)
    }
}