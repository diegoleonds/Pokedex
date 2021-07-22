package com.example.pokedex.data.repository

import com.example.pokedex.data.api.PokemonApi
import com.example.pokedex.data.error.Result
import com.example.pokedex.data.error.http.HttpErrorHandler
import com.example.pokedex.data.error.http.HttpErrorMessage
import com.example.pokedex.data.model.*
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Assert.*
import org.junit.Test

class PokemonRepositoryImplTest {
    val api = mockk<PokemonApi>()
    val errorHandler = HttpErrorHandler()
    val repository = PokemonRepositoryImpl(
        api = api,
        errorHandler = errorHandler
    )

    @Test
    fun shouldGetPokemonsList() = runBlocking {
        val id = 10
        val pokemon = Pokemon (
            name = "Bulbasaur",
            url = "https://pokeapi.co/api/v2/pokemon/$id/"
        )
        val pokemons = ArrayList<Pokemon>()
        pokemons.add(pokemon)

        val pokemonDetail = PokemonDetail(
            id = id,
            name = pokemon.name,
            sprites = mockk<Sprites>(),
            types = ArrayList<TypeWrapper>(),
            stats = ArrayList<StatWrapper>()
        )
        val pokemonsDetail = ArrayList<PokemonDetail>()
        pokemonsDetail.add(pokemonDetail)

        coEvery { api.getPokemons(any()) } returns PokemonWrapper(pokemons)
        coEvery { api.getPokemonInfo(id) } returns pokemonDetail
        val repositoryResult = repository.getPokemons()

        when (repositoryResult) {
            is Result.Success -> assertEquals(pokemonsDetail, repositoryResult.data)
            else -> fail()
        }
    }

    @Test
    fun shouldFailWhenGetPokemonsList() = runBlocking {
        coEvery { api.getPokemons(any()) } throws Throwable()
        val result = repository.getPokemons()

        when (result) {
            is Result.Error -> assertTrue(result.error is HttpErrorMessage)
            else -> fail()
        }
    }

    @Test
    fun shouldGetPokemonInfo() = runBlocking {
        val pokemon = mockk<PokemonDetail>()
        coEvery { api.getPokemonInfo(any()) } returns pokemon
        val result = repository.getPokemonInfo(1)

        when (result) {
            is Result.Success -> assertEquals(pokemon, result.data)
            else -> fail()
        }
    }

    @Test
    fun shouldFailWhenGetPokemonInfo() = runBlocking {
        coEvery { api.getPokemonInfo(any()) } throws Throwable()
        val result = repository.getPokemonInfo(1)

        when (result) {
            is Result.Error -> assertTrue(result.error is HttpErrorMessage)
            else -> fail()
        }
    }
}