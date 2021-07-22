package com.example.pokedex.data.model

import org.junit.Assert.*
import org.junit.Test

class PokemonTest {
    @Test
    fun shouldGetIdFromUrl() {
        val expectedId = 100
        val pokemon = Pokemon(
            name = "Bulbasaur",
            url = "https://pokeapi.co/api/v2/pokemon/$expectedId/"
        )
        assertEquals(expectedId, pokemon.getIdFromUrl())
    }
}