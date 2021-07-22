package com.example.pokedex.ui.fragment

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.navigation.Navigation
import com.example.pokedex.R
import com.example.pokedex.ui.util.ColorUtils
import com.example.pokedex.ui.viewmodel.MainViewModel
import com.google.android.material.card.MaterialCardView

class MainFragment : Fragment(R.layout.main_fragment) {
    private lateinit var pokedexView: MaterialCardView
    private lateinit var movesView: MaterialCardView
    private lateinit var abilitiesView: MaterialCardView
    private lateinit var itemsView: MaterialCardView
    private lateinit var locationsView: MaterialCardView
    private lateinit var typeChartsView: MaterialCardView

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        inflateViews(view)
        populateIncludedCardViews()
        setIncludedCardViewClicks(view)
    }

    private fun inflateViews(view: View) {
        pokedexView = view.findViewById(R.id.PokemonCardView)
        movesView = view.findViewById(R.id.MovesCardView)
        abilitiesView = view.findViewById(R.id.AbilitiesCardView)
        itemsView = view.findViewById(R.id.ItemsCardView)
        locationsView = view.findViewById(R.id.LocationsCardView)
        typeChartsView = view.findViewById(R.id.TypeChartsCardView)
    }

    private fun populateIncludedCardViews() {
        setIncludedCardViewData(pokedexView, CardViewOption.POKEDEX)
        setIncludedCardViewData(movesView, CardViewOption.MOVES)
        setIncludedCardViewData(abilitiesView, CardViewOption.ABILITIES)
        setIncludedCardViewData(itemsView, CardViewOption.ITEMS)
        setIncludedCardViewData(locationsView, CardViewOption.LOCATIONS)
        setIncludedCardViewData(typeChartsView, CardViewOption.TYPE_CHARTS)
    }

    private fun setIncludedCardViewData(includedView: MaterialCardView, option: CardViewOption) {
        includedView.findViewById<TextView>(R.id.CardViewText).text = getString(option.title)
        ColorUtils.setCardViewBgColor(
            cardView = includedView,
            colorResource = option.bgColor
        )
    }

    private fun setIncludedCardViewClicks(view: View) {
        pokedexView.setOnClickListener {
            Navigation.findNavController(view)
                .navigate(R.id.action_mainFragment_to_pokedexFragment)
        }
    }
}

private enum class CardViewOption(val title: Int, val bgColor: Int) {
    POKEDEX(R.string.pokedex_cv_title, R.color.grass_type),
    MOVES(R.string.moves_cv_title, R.color.fire_type),
    ABILITIES(R.string.abilities_cv_title, R.color.water_type),
    ITEMS(R.string.items_cv_title, R.color.electric_type),
    LOCATIONS(R.string.locations_cv_title, R.color.poison_type),
    TYPE_CHARTS(R.string.type_charts_cv_title, R.color.bug_type)
}