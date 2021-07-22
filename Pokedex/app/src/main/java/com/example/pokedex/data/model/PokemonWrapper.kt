package com.example.pokedex.data.model

import com.google.gson.annotations.SerializedName

data class PokemonWrapper(
    @SerializedName("results") val pokemons: ArrayList<Pokemon>
) {
}