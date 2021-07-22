package com.example.pokedex.domain.di

import com.example.pokedex.data.repository.PokemonRepositoryImpl
import com.example.pokedex.domain.repository.PokemonRepository
import com.example.pokedex.domain.transform.PokemonTransform
import com.example.pokedex.domain.usecase.GetPokemonsUseCase
import org.koin.dsl.module

val domainModule = module {
    factory { PokemonTransform() }
    factory { GetPokemonsUseCase(get(), get()) }
    factory { get<PokemonRepositoryImpl>() as PokemonRepository }
}