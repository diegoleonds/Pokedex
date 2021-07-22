package com.example.pokedex.data.model

data class Pokemon(
    val name: String,
    val url: String
) {
    fun getIdFromUrl(): Int {
        val splitedString = url.split("/")

        return splitedString[splitedString.size - 2].toInt()
    }
}