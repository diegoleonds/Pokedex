package com.example.pokedex.domain.usecase

import com.example.pokedex.data.model.*
import com.example.pokedex.domain.repository.PokemonRepository
import com.example.pokedex.data.error.Result
import com.example.pokedex.data.error.http.HttpErrorMessage
import com.example.pokedex.domain.transform.PokemonTransform
import com.example.pokedex.ui.model.Pokemon
import com.example.pokedex.ui.model.PokemonType
import io.mockk.coEvery
import io.mockk.every
import io.mockk.mockk
import io.mockk.spyk
import kotlinx.coroutines.runBlocking
import org.junit.Assert.*
import org.junit.Test

class GetPokemonsUseCaseTest {
    val transform = spyk<PokemonTransform>()
    val repository = mockk<PokemonRepository>()
    val useCase = GetPokemonsUseCase(transform, repository)

    val typeWrapper = TypeWrapper(
        type = Type(
            name = "grass"
        )
    )
    val imgWrapper = ImgWrapper(
        wrapper = ImgUrlWrapper(
            imgUrl = "url.com"
        )
    )
    val statsWrapper = StatWrapper(
        baseStat = 30,
        effort = 0,
        stat = Stat(
            name = "Speed"
        )
    )
    val pokemonModel = PokemonDetail(
        id = 1,
        name = "Bulbasaur",
        sprites = Sprites(
            img = imgWrapper
        ),
        types = ArrayList(listOf(
            typeWrapper
        )),
        stats = ArrayList(listOf(
            statsWrapper
        ))
    )
    val pokemonView = Pokemon(
        id = 1,
        name = "Bulbasaur",
        mainType = PokemonType.GRASS,
        otherTypes = ArrayList(),
        imgUrl = imgWrapper.wrapper.imgUrl
    )

    val pokemonsModel = ArrayList<PokemonDetail>(
        listOf(
            pokemonModel
        ))

    val pokemonsView = ArrayList<Pokemon>(listOf(
        pokemonView
    ))

    @Test
    fun shouldGetPokemonsWhenRequestIsSuccessfull() = runBlocking {
        coEvery { repository.getPokemons(any()) } returns Result.Success(pokemonsModel)
        every { transform.transformModelPokemonIntoViewPokemon(pokemonModel) } returns pokemonView
        val result = useCase.getPokemons()

        when (result) {
            is Result.Success -> assertEquals(pokemonsView, result.data)
            else -> fail()
        }
    }

    @Test
    fun shouldReturnRepositoryErrorResultWhenRequestFails() = runBlocking {
        coEvery { repository.getPokemons(any()) } returns Result.Error(HttpErrorMessage.AccessDenied)
        val result = useCase.getPokemons()

        when (result) {
            is Result.Error -> assertEquals(result.error, HttpErrorMessage.AccessDenied)
            else -> fail()
        }
    }
}