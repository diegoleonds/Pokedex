package com.example.pokedex.navigation

import androidx.navigation.Navigation
import androidx.navigation.Navigation.setViewNavController
import androidx.navigation.testing.TestNavHostController
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import com.example.pokedex.R
import com.example.pokedex.ui.fragment.MainFragment
import com.example.pokedex.ui.fragment.PokedexFragment
import com.example.pokedex.ui.model.PokemonType
import com.example.pokedex.util.launchFragment
import com.example.pokedex.util.setNavController
import com.example.pokedex.viewmatchers.recyclerViewAtPositionOnView
import junit.framework.TestCase.assertEquals
import org.junit.Test
import org.junit.runner.RunWith

@LargeTest
@RunWith(AndroidJUnit4::class)
class MyNavTest {
    // Create a TestNavHostController
    val navController = TestNavHostController(
        ApplicationProvider.getApplicationContext()
    )

    @Test
    fun shouldNavigateToPokedexWhenClickOnPokemonCardView() {
        // Create a graphical FragmentScenario for the TitleScreen
        val mainScenario = launchFragment<MainFragment>()
        setNavController(
            navController,
            mainScenario
        )
        // Verify that performing a click changes the NavController’s state
        onView(withId(R.id.PokemonCardView)).perform(click())
        assertEquals(R.id.pokedexFragment, navController.currentDestination?.id)
    }

//    @Test
//    fun shouldNavigateToMainFragmentWhenClickOnBackBtn() {
//        // Create a graphical FragmentScenario for the TitleScreen
//        val pokedexScenario = launchFragment<PokedexFragment>()
//        pokedexScenario.onFragment { fragment ->
//            // Set the graph on the TestNavHostController
//            navController.setGraph(R.navigation.my_nav)
//
//            // Make the NavController available via the findNavController() APIs
//            setViewNavController(fragment.requireView(), navController)
//        }
//        // Verify that performing a click changes the NavController’s state
//        onView(withId(R.id.PokedexBackBtn)).perform(click())
//        assertEquals(R.id.mainFragment, navController.currentDestination?.id)
//    }
//
//    @Test
//    fun shouldNavigateToPokemonInfoFragmentWhenClickOnPokemonCardItem() {
//        onView(withId(R.id.PokedexRv)).perform(actionOnItemAtPosition(0, click()));
//    }
}