package com.example.pokedex.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pokedex.data.api.ApiDefaultValues
import com.example.pokedex.domain.usecase.GetPokemonsUseCase
import com.example.pokedex.ui.model.Pokemon
import com.example.pokedex.data.error.Result
import kotlinx.coroutines.launch

class PokedexViewModel(
    val getPokemonsUseCase: GetPokemonsUseCase
): ViewModel() {
    private val _pokemons = MutableLiveData<Result<ArrayList<Pokemon>>>()
    val pokemons: LiveData<Result<ArrayList<Pokemon>>>
        get() = _pokemons

    fun getPokemons(offset: Int = ApiDefaultValues.offset) {
        viewModelScope.launch {
            _pokemons.postValue(getPokemonsUseCase.getPokemons(offset))
        }
    }
}