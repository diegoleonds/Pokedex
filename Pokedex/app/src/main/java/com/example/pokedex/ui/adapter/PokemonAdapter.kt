package com.example.pokedex.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.pokedex.R
import com.example.pokedex.ui.model.Pokemon
import com.example.pokedex.ui.util.ColorUtils
import com.example.pokedex.ui.util.ImgLoader
import com.google.android.material.card.MaterialCardView

class PokemonAdapter(
    val imgLoader: ImgLoader,
    val click: Click<Pokemon>
): RecyclerView.Adapter<PokemonAdapter.PokemonViewHolder>() {

    val pokemons = ArrayList<Pokemon>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PokemonViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.pokemon_item,
            parent, false)

        return PokemonViewHolder(view)
    }

    override fun onBindViewHolder(holder: PokemonViewHolder, position: Int) {
        val pokemon = pokemons[position]

        holder.name.text = pokemon.name
        holder.view.context?.let {
            holder.type.text = it.getText(pokemon.mainType.descriptionResource)
            ColorUtils.setCardViewBgColor(
                cardView = holder.cardView,
                colorResource = pokemon.mainType.color
            )
            imgLoader.loadImageById(
                imgView = holder.img,
                id = pokemon.id
            )
        }
        holder.cardView.setOnClickListener { click.simpleClick(pokemon) }
    }

    override fun getItemCount() = pokemons.size

    fun updateData(data: ArrayList<Pokemon>) {
        pokemons.addAll(data)
        notifyDataSetChanged()
    }

    class PokemonViewHolder(val view: View): RecyclerView.ViewHolder(view) {
        val cardView: MaterialCardView
        val name: TextView
        val type: TextView
        val img: ImageView

        init {
            cardView = view.findViewById(R.id.PokemonItemCardView)
            name = view.findViewById(R.id.PokemonItemName)
            type = view.findViewById(R.id.PokemonItemType)
            img = view.findViewById(R.id.PokemonItemImg)
        }
    }
}