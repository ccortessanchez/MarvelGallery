package com.example.ccsa.marvelapp.model

import com.example.ccsa.marvelapp.data.network.dto.CharacterMarvelDto

data class MarvelCharacter(
        val name: String,
        val imageUrl: String) {

    constructor(dto: CharacterMarvelDto): this(
            name = dto.name,
            imageUrl = dto.imageUrl
    )
}