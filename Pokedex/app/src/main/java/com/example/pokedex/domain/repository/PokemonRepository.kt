package com.example.pokedex.domain.repository

import com.example.pokedex.data.api.ApiDefaultValues
import com.example.pokedex.data.model.Pokemon
import com.example.pokedex.data.error.Result
import com.example.pokedex.data.model.PokemonDetail
import retrofit2.http.Path

interface PokemonRepository {
    suspend fun getPokemons(offset: Int = ApiDefaultValues.offset): Result<ArrayList<PokemonDetail>>
    suspend fun getPokemonInfo(id: Int): Result<PokemonDetail>
}