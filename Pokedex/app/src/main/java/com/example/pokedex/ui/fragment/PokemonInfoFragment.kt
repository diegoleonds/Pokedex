package com.example.pokedex.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.bumptech.glide.Glide
import com.example.pokedex.R
import com.example.pokedex.ui.model.Pokemon
import com.example.pokedex.ui.util.ColorUtils
import com.example.pokedex.ui.util.ImgLoader
import com.google.android.material.appbar.CollapsingToolbarLayout

class PokemonInfoFragment : Fragment(R.layout.pokemon_info_fragment) {
    lateinit var pokemon: Pokemon

    private lateinit var backBtn: ImageView
    private lateinit var pokemonImg: ImageView
    private lateinit var toolbarLayout: CollapsingToolbarLayout
    private lateinit var rootLayout: CoordinatorLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let {
            pokemon = PokemonInfoFragmentArgs.fromBundle(it).pokemon
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        inflateViews(view)
        populateViews(view)
        setBackBtnClick(view)
    }

    private fun inflateViews(view: View) {
        backBtn = view.findViewById(R.id.PokemonInfoBackBtn)
        pokemonImg = view.findViewById(R.id.PokemonInfoImgView)
        toolbarLayout = view.findViewById(R.id.PokemonInfoClpsToolbar)
        rootLayout = view.findViewById(R.id.PokemonInfoRootLayout)
    }

    private fun populateViews(view: View) {
        rootLayout.setBackgroundColor(
            ColorUtils.getColor(
                colorResource = pokemon.mainType.color,
                context = view.context
            )
        )
        toolbarLayout.setContentScrimColor(
            ColorUtils.getColor(
                colorResource = pokemon.mainType.color,
                context = view.context
            )
        )
        toolbarLayout.setBackgroundColor(
            ColorUtils.getColor(
                colorResource = pokemon.mainType.color,
                context = view.context
            )
        )
        toolbarLayout.title = pokemon.name
        val imgLoader = ImgLoader(
            glide = Glide.with(view.context)
        )
        imgLoader.loadImageById(
            imgView = pokemonImg,
            id = pokemon.id
        )
    }

     private fun setBackBtnClick(view: View) {
         backBtn.setOnClickListener {
             Navigation.findNavController(view).popBackStack()
         }
    }
}