package com.example.pokedex.ui.model

import org.junit.Assert.*
import org.junit.Test

class PokemonTypeTest {
    @Test
    fun shouldGetPokemonTypeByDescription() {
        assertEquals(PokemonType.GRASS, PokemonType.fromDescription("grass"))
        assertEquals(PokemonType.FIRE, PokemonType.fromDescription("fire"))
        assertEquals(PokemonType.WATER, PokemonType.fromDescription("water"))
        assertEquals(PokemonType.BUG, PokemonType.fromDescription("bug"))
        assertEquals(PokemonType.ELETRIC, PokemonType.fromDescription("electric"))
    }

    @Test
    fun shouldGetDefaultWhenDescriptionNotExists() {
        assertEquals(PokemonType.DEFAULT, PokemonType.fromDescription("askwnk"))
    }
}