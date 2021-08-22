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
) : ViewModel() {

    sealed class ViewState {
        data class InitData(val alreadyInit: Boolean = false): ViewState()
    }

    val state = MutableLiveData<ViewState>()
    private val _pokemons = MutableLiveData<Result<ArrayList<Pokemon>>>()

    fun resolveState(state: ViewState) {
        when (state) {
            is ViewState.InitData -> loadPokemons()
        }
    }

    val pokemons: LiveData<Result<ArrayList<Pokemon>>>
        get() = _pokemons

    fun loadPokemons(offset: Int = ApiDefaultValues.offset) {
        viewModelScope.launch {
            _pokemons.postValue(getPokemonsUseCase.getPokemons(offset))
        }
    }
}