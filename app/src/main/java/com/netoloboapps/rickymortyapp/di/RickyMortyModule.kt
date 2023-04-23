package com.netoloboapps.rickymortyapp.di

import com.netoloboapps.rickymortyapp.RickMortyApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RickyMortyModule {

    @Singleton
    @Provides
    fun provideRetrofitApi(): RickMortyApiService {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl("https://rickandmortyapi.com/api/")
            .build().create(RickMortyApiService::class.java)
    }
}