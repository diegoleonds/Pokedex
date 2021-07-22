package com.example.pokedex.domain.transform

import com.example.pokedex.ui.model.PokemonType
import java.util.*
import kotlin.collections.ArrayList
import com.example.pokedex.data.model.PokemonDetail as PokemonModel
import com.example.pokedex.ui.model.Pokemon as ViewPokemon

class PokemonTransform {

    fun transformModelPokemonIntoViewPokemon(modelPokemon: PokemonModel): ViewPokemon {
        val otherTypes = ArrayList<String>()
        val type = PokemonType.fromDescription(modelPokemon.types[0].type.name)
        modelPokemon.types.removeAt(0)
        modelPokemon.types.forEach {
            otherTypes.add(it.type.name)
        }
        val viewPokemon = ViewPokemon(
            id = modelPokemon.id,
            name = modelPokemon.name,
            mainType = type,
            otherTypes = otherTypes,
            imgUrl = modelPokemon.sprites.img.wrapper.imgUrl
        )
        return viewPokemon
    }

    fun capitalizeFirstLetter(string: String) {

    }
}