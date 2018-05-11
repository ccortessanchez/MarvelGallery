package com.example.ccsa.marvelapp.data.network

import com.example.ccsa.marvelapp.data.network.dto.CharacterMarvelDto
import com.example.ccsa.marvelapp.data.network.dto.DataWrapper
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface MarvelApi {

    @GET("characters")
    fun getCharacters(
            @Query("offset") offset: Int?,
            @Query("limit") limit: Int?
    ): Single<DataWrapper<List<CharacterMarvelDto>>>
}