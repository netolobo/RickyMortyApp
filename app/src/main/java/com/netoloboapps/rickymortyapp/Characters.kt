package com.netoloboapps.rickymortyapp

import com.google.gson.annotations.SerializedName

data class CharactersResponse(
    @SerializedName("results") val characters: List<Character>
)

data class Character(
    val id: Int,
    val name: String,
    val status: String,
    val species: String,
    val gender: String,
    val image: String
)