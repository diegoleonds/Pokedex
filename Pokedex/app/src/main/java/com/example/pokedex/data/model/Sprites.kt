package com.example.pokedex.data.model

import com.google.gson.annotations.SerializedName

data class Sprites(
    @SerializedName("other") val img: ImgWrapper
) {
}

data class ImgWrapper(
    @SerializedName("official-artwork") val wrapper: ImgUrlWrapper
) {
}

data class ImgUrlWrapper(
    @SerializedName("front_default") val imgUrl: String
) {

}