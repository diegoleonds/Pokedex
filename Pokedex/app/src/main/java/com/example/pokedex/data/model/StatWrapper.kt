package com.example.pokedex.data.model

import com.google.gson.annotations.SerializedName

data class StatWrapper(
    @SerializedName("base_stat") val baseStat: Int,
    val effort: Int,
    val stat: Stat
) {
}

data class Stat(
    val name: String
) {

}