package com.example.pokedex.ui.fragment

import androidx.test.espresso.Espresso
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.platform.app.InstrumentationRegistry.getInstrumentation
import com.example.pokedex.R
import com.example.pokedex.ui.model.PokemonType
import com.example.pokedex.util.launchFragment
import com.example.pokedex.util.setNavController
import com.example.pokedex.viewmatchers.recyclerViewAtPositionOnView
import org.junit.Test
import org.junit.runner.RunWith

@LargeTest
@RunWith(AndroidJUnit4::class)
class PokedexFragmentTest {

    @Test
    fun checkRecyclerViewFirstItem() {
        val pokedexFragment = launchFragment<PokedexFragment>()
        Thread.sleep(2000)
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