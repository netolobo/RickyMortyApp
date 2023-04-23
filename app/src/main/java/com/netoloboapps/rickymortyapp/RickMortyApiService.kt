package com.netoloboapps.rickymortyapp

import retrofit2.http.GET
import retrofit2.http.Query

interface RickMortyApiService {
    @GET("character/")
    suspend fun getCharacters(@Query("page") page: Int): CharactersResponse
}