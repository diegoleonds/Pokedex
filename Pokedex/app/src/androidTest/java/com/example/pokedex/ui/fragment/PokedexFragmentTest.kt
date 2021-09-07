package com.example.pokedex.ui.fragment

import androidx.fragment.app.testing.FragmentScenario
import androidx.lifecycle.viewModelScope
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import com.example.pokedex.R
import com.example.pokedex.extensions.afterCoroutine
import com.example.pokedex.ui.model.PokemonType
import com.example.pokedex.util.launchFragment
import com.example.pokedex.viewmatchers.recyclerViewAtPositionOnView
import kotlinx.coroutines.CoroutineScope
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@LargeTest
@RunWith(AndroidJUnit4::class)
class PokedexFragmentTest {

    private lateinit var coroutineScopeReference: CoroutineScope
    private lateinit var fragmentScenario: FragmentScenario<PokedexFragment>

    @Before
    fun setup() {
        fragmentScenario = launchFragment()
        fragmentScenario.onFragment {
            coroutineScopeReference = it.viewModel.viewModelScope
        }
    }

    @Test
    fun checkRecyclerViewFirstItem() {
        afterCoroutine(coroutineScopeReference) {
            onView(withId(R.id.PokedexRv))
                .check(
                    matches(
                        recyclerViewAtPositionOnView(
                            0, withText("bulbasaur"), R.id.PokemonItemName
                        )
                    )
                )

            onView(withId(R.id.PokedexRv))
                .check(
                    matches(
                        recyclerViewAtPositionOnView(
                            0,
                            withText(PokemonType.GRASS.descriptionResource),
                            R.id.PokemonItemType
                        )
                    )
                )
        }
    }

}

