package com.example.ccsa.marvelapp.model

import android.annotation.SuppressLint
import com.example.ccsa.marvelapp.data.network.dto.CharacterMarvelDto
import kotlinx.android.parcel.Parcelize
import android.os.Parcelable

@SuppressLint("ParcelCreator")
@Parcelize

data class MarvelCharacter(
        val name: String,
        val imageUrl: String): Parcelable {

    constructor(dto: CharacterMarvelDto): this(
            name = dto.name,
            imageUrl = dto.imageUrl
    )
}