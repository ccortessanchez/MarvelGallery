package com.example.ccsa.marvelapp.view.main

import com.example.ccsa.marvelapp.model.MarvelCharacter

interface MainView {
    var refresh: Boolean
    fun show(items: List<MarvelCharacter>)
    fun showError(error: Throwable)
}