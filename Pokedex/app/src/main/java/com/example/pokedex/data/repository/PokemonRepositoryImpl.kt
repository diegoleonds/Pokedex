package com.example.pokedex.data.repository

import com.example.pokedex.data.api.PokemonApi
import com.example.pokedex.data.error.Result
import com.example.pokedex.data.error.http.HttpErrorHandler
import com.example.pokedex.data.model.Pokemon
import com.example.pokedex.data.model.PokemonDetail
import com.example.pokedex.domain.repository.PokemonRepository
import com.example.pokedex.domain.transform.PokemonTransform

class PokemonRepositoryImpl(
    val api: PokemonApi,
    val errorHandler: HttpErrorHandler
): PokemonRepository {
    override suspend fun getPokemons(offset: Int): Result<ArrayList<PokemonDetail>> {
        return try {
            val pokemons = api.getPokemons(offset).pokemons
            val data = ArrayList<PokemonDetail>()

            pokemons.forEach {
                data.add(api.getPokemonInfo(it.getIdFromUrl()))
            }

            Result.Success(data)
        } catch (t: Throwable) {
            Result.Error(errorHandler.getError(t))
        }
    }

    override suspend fun getPokemonInfo(id: Int): Result<PokemonDetail> {
        return try {
            Result.Success(api.getPokemonInfo(id))
        } catch (t: Throwable) {
            Result.Error(errorHandler.getError(t))
        }
    }

}