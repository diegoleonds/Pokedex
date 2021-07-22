package com.example.pokedex.data.api

import com.example.pokedex.data.model.Pokemon
import com.example.pokedex.data.model.PokemonDetail
import com.example.pokedex.data.model.PokemonWrapper
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface PokemonApi {
    @GET("pokemon")
    suspend fun getPokemons(@Query("offset") offset: Int): PokemonWrapper

    @GET("pokemon/{id}/")
    suspend fun getPokemonInfo(@Path("id") id: Int): PokemonDetail
}