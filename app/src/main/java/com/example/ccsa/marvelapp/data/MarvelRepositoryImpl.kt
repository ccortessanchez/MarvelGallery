package com.example.ccsa.marvelapp.data

import com.example.ccsa.marvelapp.data.network.MarvelApi
import com.example.ccsa.marvelapp.data.network.providers.retrofit
import com.example.ccsa.marvelapp.model.MarvelCharacter
import io.reactivex.Single

class MarvelRepositoryImpl: MarvelRepository {

    val api = retrofit.create(MarvelApi::class.java)

    override fun getAllCharacters(searchQuery: String?): Single<List<MarvelCharacter>> = api.getCharacters(
            offset = 0,
            searchQuery = searchQuery,
            limit = elementsOnListLimit
    ).map {
        it.data?.results.orEmpty().map(::MarvelCharacter)
    }

    companion object {
        const val elementsOnListLimit = 50
    }
}

