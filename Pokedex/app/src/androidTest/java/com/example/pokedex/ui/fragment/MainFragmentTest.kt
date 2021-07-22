package com.example.pokedex.ui.fragment

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import com.example.pokedex.R
import com.example.pokedex.util.launchFragment
import org.hamcrest.CoreMatchers.allOf
import org.junit.Test
import org.junit.runner.RunWith


@LargeTest
@RunWith(AndroidJUnit4::class)
class MainFragmentTest {
    @Test
    fun checkMenuCardViewItemsTitle() {
        val mainFragment = launchFragment<MainFragment>()

        onView(allOf(withId(R.id.CardViewText), withParent(withId(R.id.PokemonCardView))))
            .check(matches(withText(R.string.pokedex_cv_title)))

        onView(allOf(withId(R.id.CardViewText), withParent(withId(R.id.MovesCardView))))
            .check(matches(withText(R.string.moves_cv_title)))

        onView(allOf(withId(R.id.CardViewText), withParent(withId(R.id.AbilitiesCardView))))
            .check(matches(withText(R.string.abilities_cv_title)))

        onView(allOf(withId(R.id.CardViewText), withParent(withId(R.id.ItemsCardView))))
            .check(matches(withText(R.string.items_cv_title)))

        onView(allOf(withId(R.id.CardViewText), withParent(withId(R.id.LocationsCardView))))
            .check(matches(withText(R.string.locations_cv_title)))

        onView(allOf(withId(R.id.CardViewText), withParent(withId(R.id.TypeChartsCardView))))
            .check(matches(withText(R.string.type_charts_cv_title)))

    }

}