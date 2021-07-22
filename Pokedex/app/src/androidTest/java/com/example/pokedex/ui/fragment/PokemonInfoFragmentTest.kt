package com.example.pokedex.ui.fragment

import android.os.Bundle
import androidx.fragment.app.testing.FragmentScenario
import androidx.fragment.app.testing.launchFragment
import androidx.navigation.testing.TestNavHostController
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.Espresso
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.action.ViewActions.scrollTo
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import com.example.pokedex.R
import com.example.pokedex.ui.model.Pokemon
import com.example.pokedex.ui.model.PokemonType
import com.example.pokedex.util.launchFragment
import com.example.pokedex.util.setNavController
import com.example.pokedex.viewactions.CollapseAppBarLayout
import com.example.pokedex.viewactions.CollapseAppBarLayout.collapseAppBarLayout
import com.example.pokedex.viewmatchers.withTitle
import org.hamcrest.Matchers.not
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@LargeTest
@RunWith(AndroidJUnit4::class)
class PokemonInfoFragmentTest {
    val pokemon = Pokemon(
        id = 1,
        name = "Bulbasaur",
        mainType = PokemonType.GRASS,
        otherTypes = ArrayList(),
        imgUrl = "img.com"
    )
    val bundle = Bundle()

    lateinit var fragmentScenario: FragmentScenario<PokemonInfoFragment>

    @Before
    fun init() {
        bundle.putParcelable("pokemon", pokemon)
        fragmentScenario = launchFragment(
            fragmentArgs = bundle
        )
    }

    @Test
    fun shouldSetPokemonNameAsTitle() {
        onView(withId(R.id.PokemonInfoClpsToolbar)).check(matches(withTitle(pokemon.name)))
    }

    @Test
    fun shouldMantainTheTitleWhenAppBarCollapse() {
        onView(withId(R.id.PokemonInfoAppBarLayout)).perform(collapseAppBarLayout())
        onView(withId(R.id.PokemonInfoClpsToolbar)).check(matches(withTitle(pokemon.name)))
    }
}