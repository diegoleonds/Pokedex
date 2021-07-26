package com.example.pokedex.ui.fragment

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.pokedex.R
import com.example.pokedex.data.error.Result
import com.example.pokedex.ui.adapter.Click
import com.example.pokedex.ui.adapter.MarginItemDecoration
import com.example.pokedex.ui.adapter.PokemonAdapter
import com.example.pokedex.ui.model.Pokemon
import com.example.pokedex.ui.util.ImgLoader
import com.example.pokedex.ui.viewmodel.PokedexViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

import org.koin.android.ext.android.inject

import org.koin.androidx.viewmodel.ext.android.viewModel

class PokedexFragment : Fragment(R.layout.pokedex_fragment) {

    private val viewModel: PokedexViewModel by viewModel()
    val CoroutineScope: CoroutineScope by inject()

    private lateinit var recyclerView: RecyclerView
    private lateinit var pokemonAdapter: PokemonAdapter
    private lateinit var backBtn: ImageView

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        inflateViews(view)
        customizeRecyclerView(view)
        setBackBtnClick(view)
        observeViewModel(view)
        getData()
    }

    private fun inflateViews(view: View) {
        recyclerView = view.findViewById(R.id.PokedexRv)
        backBtn = view.findViewById(R.id.PokedexBackBtn)
    }

    private fun customizeRecyclerView(view: View) {
        recyclerView.layoutManager = GridLayoutManager(context, 2)
        pokemonAdapter = PokemonAdapter(
            ImgLoader(
                glide = Glide.with(view.context)
            ),
            object : Click<Pokemon> {
                override fun simpleClick(pokemon: Pokemon) {
                    val action = PokedexFragmentDirections.
                        actionPokedexFragmentToPokemonInfoFragment(pokemon)
                    Navigation.findNavController(view).navigate(action)
                }
            }
        )

        recyclerView.adapter = pokemonAdapter
        recyclerView.addItemDecoration(
            MarginItemDecoration(
                verticalMargin = resources.getDimensionPixelSize(R.dimen.pokemon_grid_list_margin),
                lastRowBottomMargin = resources.getDimensionPixelSize(
                    R.dimen.pokemon_grid_list_final_item_bottom_margin
                )
            )
        )
    }

    private fun observeViewModel(view: View) {
        viewModel.pokemons.observe(viewLifecycleOwner, Observer {
            it?.let {
                when (it) {
                    is Result.Success -> pokemonAdapter.updateData(it.data)
                    is Result.Error -> Toast.makeText(
                        context, getString(it.error.messageResource),
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        })
    }

    private fun getData() {
        CoroutineScope.launch { viewModel.getPokemons() }
        //CoroutineScope(Dispatchers.IO).launch { viewModel.getPokemons() }
    }

    private fun setBackBtnClick(view: View) {
        backBtn.setOnClickListener {
            Navigation.findNavController(view)
                .navigate(R.id.action_pokedexFragment_to_mainFragment)
        }
    }
}