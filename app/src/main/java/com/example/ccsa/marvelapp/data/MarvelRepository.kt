package com.example.ccsa.marvelapp.data

import com.example.ccsa.marvelapp.model.MarvelCharacter
import io.reactivex.Single

interface MarvelRepository {

    fun getAllCharacters(): Single<List<MarvelCharacter>>

    companion object: Provider<MarvelRepository>() {
        override fun creator() = MarvelRepositoryImpl()
    }
}

