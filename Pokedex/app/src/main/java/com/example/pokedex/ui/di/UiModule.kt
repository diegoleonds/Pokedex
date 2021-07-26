package com.example.pokedex.ui.di

import com.example.pokedex.ui.viewmodel.MainViewModel
import com.example.pokedex.ui.viewmodel.PokedexViewModel
import com.example.pokedex.ui.viewmodel.PokemonInfoViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val uiModule = module {
    viewModel { MainViewModel() }
    viewModel { PokedexViewModel(get()) }
    viewModel { PokemonInfoViewModel() }
    single { CoroutineScope(Dispatchers.IO)}
}