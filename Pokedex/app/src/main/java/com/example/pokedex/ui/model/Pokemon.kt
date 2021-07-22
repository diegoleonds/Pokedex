package com.example.pokedex.ui.model

import android.os.Parcel
import android.os.Parcelable

data class Pokemon(
    val id: Int,
    val name: String,
    val mainType: PokemonType,
    val otherTypes: ArrayList<String>,
    val imgUrl: String
): Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString() ?: "",
        PokemonType.values()[parcel.readInt()],
        parcel.createStringArrayList() ?: ArrayList(),
        parcel.readString() ?: ""
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(id)
        parcel.writeString(name)
        parcel.writeInt(mainType.ordinal)
        parcel.writeStringList(otherTypes)
        parcel.writeString(imgUrl)
    }

    override fun describeContents(): Int = 0

    companion object CREATOR : Parcelable.Creator<Pokemon> {
        override fun createFromParcel(parcel: Parcel): Pokemon = Pokemon(parcel)

        override fun newArray(size: Int): Array<Pokemon?> = arrayOfNulls(size)
    }
}