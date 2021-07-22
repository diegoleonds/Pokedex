package com.example.pokedex.data.model

data class PokemonDetail(
    val id: Int,
    val name: String,
    val sprites: Sprites,
    val types: ArrayList<TypeWrapper>,
    val stats: ArrayList<StatWrapper>
) {
}
