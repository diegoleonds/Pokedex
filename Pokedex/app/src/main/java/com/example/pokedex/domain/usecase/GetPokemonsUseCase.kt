package com.example.pokedex.domain.usecase

import com.example.pokedex.data.api.ApiDefaultValues
import com.example.pokedex.domain.repository.PokemonRepository
import com.example.pokedex.domain.transform.PokemonTransform
import com.example.pokedex.data.error.Result

import com.example.pokedex.data.model.PokemonDetail as ModelPokemon
import com.example.pokedex.ui.model.Pokemon as ViewPokemon

class GetPokemonsUseCase(
    val transform: PokemonTransform,
    val repository: PokemonRepository
) {
    suspend fun getPokemons(offset: Int = ApiDefaultValues.offset): Result<ArrayList<ViewPokemon>> {
        val result = repository.getPokemons(offset)

        return when (result) {
            is Result.Success -> {
                val pokemons = ArrayList<ViewPokemon>()
                result.data.forEach {
                    pokemons.add(transform.transformModelPokemonIntoViewPokemon(it))
                }
                Result.Success(pokemons)
            }
            is Result.Error -> Result.Error(result.error)
        }
    }
}