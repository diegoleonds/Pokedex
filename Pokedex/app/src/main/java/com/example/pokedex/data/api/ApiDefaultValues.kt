package com.example.pokedex.data.api

object ApiDefaultValues {
    val imgUrl = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/"
    val offset = 0

    fun getImgUrlById(id: Int) = "$imgUrl/$id.png"
}