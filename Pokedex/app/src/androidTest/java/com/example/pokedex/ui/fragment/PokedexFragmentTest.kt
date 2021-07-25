package com.example.pokedex.ui.fragment

import android.view.View
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches

import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import com.example.pokedex.R
import com.example.pokedex.ui.model.PokemonType
import com.example.pokedex.ui.util.NewCoroutineScope
import com.example.pokedex.util.launchFragment
import com.example.pokedex.viewmatchers.recyclerViewAtPositionOnView
import org.hamcrest.Matcher
import org.junit.Test
import org.junit.runner.RunWith
import org.koin.android.ext.android.inject


@LargeTest
@RunWith(AndroidJUnit4::class)
class PokedexFragmentTest {

    lateinit var CoroutineScopeReference: NewCoroutineScope

    @Test
    fun checkRecyclerViewFirstItem() {
        val pokedexFragment = launchFragment<PokedexFragment>()

        pokedexFragment.onFragment{
            CoroutineScopeReference = it.CoroutineScope
        }

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

    fun withId(id: Int): Matcher<View> {
        while (CoroutineScopeReference.isAnyJobRunning){ Thread.sleep(20)}
        return androidx.test.espresso.matcher.ViewMatchers.withId(id)
    }
}

