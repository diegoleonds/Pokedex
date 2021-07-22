package com.example.pokedex.ui.model

import com.example.pokedex.R

enum class PokemonType(
    val descriptionResource: Int,
    val color: Int,
    val type: String
) {
    GRASS(R.string.grass_type, R.color.grass_type, "grass"),
    FIRE(R.string.fire_type, R.color.fire_type, "fire"),
    WATER(R.string.water_type, R.color.water_type, "water"),
    BUG(R.string.bug_type, R.color.bug_type, "bug"),
    ELETRIC(R.string.eletric_type, R.color.electric_type, "electric"),
    DEFAULT(R.string.default_type, R.color.default_type, "default");

    companion object {
        private val map = PokemonType.values().associateBy(PokemonType::type)
        fun fromDescription(type: String) = map[type] ?: PokemonType.DEFAULT
    }
}