package com.example.pokedex.domain.transform

import com.example.pokedex.data.model.*
import com.example.pokedex.ui.model.PokemonType
import org.junit.Assert.*
import org.junit.Test
import com.example.pokedex.data.model.PokemonDetail as PokemonModel
import com.example.pokedex.ui.model.Pokemon as PokemonView

class PokemonTransformTest {
    val transform = PokemonTransform()
    val typeWrapper = TypeWrapper(
        type = Type(
            name = "grass"
        )
    )
    val imgWrapper = ImgWrapper(
        wrapper = ImgUrlWrapper(
            imgUrl = "url.com"
        )
    )
    val statsWrapper = StatWrapper(
        baseStat = 30,
        effort = 0,
        stat = Stat(
            name = "Speed"
        )
    )
    val pokemonModel = PokemonModel(
        id = 1,
        name = "Bulbasaur",
        sprites = Sprites(
            img = imgWrapper
        ),
        types = ArrayList(listOf(
            typeWrapper
        )),
        stats = ArrayList(listOf(
            statsWrapper
        ))
    )
    val pokemonView = PokemonView(
        id = 1,
        name = "Bulbasaur",
        mainType = PokemonType.GRASS,
        otherTypes = ArrayList(),
        imgUrl = imgWrapper.wrapper.imgUrl
    )

    @Test
    fun shouldTransformModelPokemonIntoPokemonView() {
            assertEquals(pokemonView, transform.transformModelPokemonIntoViewPokemon(
                modelPokemon = pokemonModel
            ))
    }

    @Test
    fun shouldNotPassWhenTransformModelPokemonIntoPokemonView() {
        assertEquals(pokemonView, transform.transformModelPokemonIntoViewPokemon(
            modelPokemon = pokemonModel
        ))
    }
}