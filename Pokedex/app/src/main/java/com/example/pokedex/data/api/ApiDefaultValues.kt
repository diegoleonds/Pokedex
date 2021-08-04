package com.example.pokedex.data.api

object ApiDefaultValues {
    const val imgUrl = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/"
    const val offset = 0

    fun getImgUrlById(id: Int) = "$imgUrl/$id.png"
}