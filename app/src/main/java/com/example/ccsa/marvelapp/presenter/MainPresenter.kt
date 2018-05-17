package com.example.ccsa.marvelapp.presenter

import com.example.ccsa.marvelapp.data.MarvelRepository
import com.example.ccsa.marvelapp.data.applySchedulers
import com.example.ccsa.marvelapp.data.plusAssign
import com.example.ccsa.marvelapp.data.subscribeBy
import com.example.ccsa.marvelapp.view.main.MainView

class MainPresenter(val view: MainView, val repository: MarvelRepository): BasePresenter() {

    fun onViewCreated() {
        loadCharacters()
    }

    fun onRefresh() {
        loadCharacters()
    }

    fun onSearchChanged(text: String) {
        loadCharacters(text)
    }

    private fun loadCharacters(searchQuery: String? = null) {
        val qualifiedSearchQuery = if (searchQuery.isNullOrBlank()) null else searchQuery
        subscriptions += repository
                .getAllCharacters(qualifiedSearchQuery)
                .applySchedulers()
                .doOnSubscribe { view.refresh = true }
                .doFinally { view.refresh = false }
                .subscribeBy(
                        onSuccess = view::show,
                        onError = view::showError
                )
    }
}