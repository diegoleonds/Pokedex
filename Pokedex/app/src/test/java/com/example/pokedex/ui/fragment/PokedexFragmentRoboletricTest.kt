package com.example.pokedex.ui.fragment

import android.view.View
import androidx.fragment.app.testing.FragmentScenario
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.lifecycle.viewModelScope
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.NoMatchingViewException
import androidx.test.espresso.ViewAssertion
import androidx.test.espresso.matcher.ViewMatchers.assertThat
import androidx.test.espresso.matcher.ViewMatchers.withId
import com.example.pokedex.R
import com.example.pokedex.data.error.Result
import com.example.pokedex.domain.usecase.GetPokemonsUseCase
import com.example.pokedex.ui.fragment.RecyclerViewItemCountAssertion.Companion.withItemCount
import com.example.pokedex.ui.model.Pokemon
import com.example.pokedex.ui.model.PokemonType
import com.example.pokedex.ui.viewmodel.PokedexViewModel
import io.mockk.coEvery
import io.mockk.every
import io.mockk.mockk
import io.mockk.spyk
import kotlinx.coroutines.runBlocking
import org.hamcrest.Matcher
import org.hamcrest.Matchers.`is`
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.loadKoinModules
import org.koin.core.module.Module
import org.koin.dsl.module
import org.robolectric.RobolectricTestRunner

@RunWith(RobolectricTestRunner::class)
class PokedexFragmentRoboletricTest {

    val getPokemonsUseCase = mockk<GetPokemonsUseCase>()
    val viewModel = PokedexViewModel(getPokemonsUseCase)
    val spy = spyk(PokedexViewModel(getPokemonsUseCase))

    lateinit var module: Module
    lateinit var fragment: FragmentScenario<PokedexFragment>

    @Before
    fun setup() {
        setupKoin()
    }

    private fun setupFragment() {
        fragment = launchFragmentInContainer (
            themeResId = R.style.Theme_Pokedex
        )
    }

    private fun setupKoin() {
        module = module {
            viewModel { viewModel }
        }
        loadKoinModules(module)
    }

    @Test
    fun shouldShowPokemonsOnRv() {
        runBlocking {
            val pokemon = Pokemon(
                id = 1,
                name = "Pikachu",
                mainType = PokemonType.ELETRIC,
                otherTypes = ArrayList(),
                imgUrl = "url"
            )
            val pokemons = ArrayList<Pokemon>()
            for (i in 1..20) {
                pokemons.add(pokemon.copy(id = i))
            }
            coEvery { getPokemonsUseCase.getPokemons(any()) } returns Result.Success(pokemons)
            setupFragment()

            onView(withId(R.id.PokedexRv)).check(withItemCount(pokemons.size))
        }
    }
}

class RecyclerViewItemCountAssertion private constructor(matcher: Matcher<Int>) :
    ViewAssertion {
    private val matcher: Matcher<Int> = matcher
    override fun check(view: View, noViewFoundException: NoMatchingViewException?) {
        if (noViewFoundException != null) {
            throw noViewFoundException
        }
        val recyclerView = view as RecyclerView
        val adapter = recyclerView.adapter
        assertThat(adapter!!.itemCount, matcher)
    }

    companion object {
        fun withItemCount(expectedCount: Int): RecyclerViewItemCountAssertion {
            return withItemCount(`is`(expectedCount))
        }

        fun withItemCount(matcher: Matcher<Int>): RecyclerViewItemCountAssertion {
            return RecyclerViewItemCountAssertion(matcher)
        }
    }

}