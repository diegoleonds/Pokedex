package com.example.pokedex.data.di

import com.example.pokedex.data.api.PokemonApi
import com.example.pokedex.data.api.RetrofitInstance
import com.example.pokedex.data.error.http.HttpErrorHandler
import com.example.pokedex.data.repository.PokemonRepositoryImpl
import com.example.pokedex.domain.repository.PokemonRepository
import org.koin.dsl.module

val dataModule = module {
    factory { HttpErrorHandler() }
    single { PokemonRepositoryImpl(RetrofitInstance.api, get()) }
}