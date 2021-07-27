package com.example.pokedex.util

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.testing.FragmentScenario
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.lifecycle.Lifecycle
import androidx.navigation.Navigation
import androidx.navigation.testing.TestNavHostController
import com.example.pokedex.R
import kotlinx.coroutines.runBlocking

const val themeId = R.style.Theme_Pokedex

inline fun <reified F : Fragment> launchFragment(
    fragmentArgs: Bundle? = null,
    initialState: Lifecycle.State = Lifecycle.State.RESUMED,
    themeResId: Int = themeId
) =
    launchFragmentInContainer<F>(
        themeResId = themeResId,
        fragmentArgs = fragmentArgs,
        initialState = initialState
    )

inline fun <reified F : Fragment> setNavController(
    navController: TestNavHostController,
    fragment: FragmentScenario<F>,
    navId: Int = R.navigation.my_nav
) {
    fragment.onFragment { fragment ->
        // Set the graph on the TestNavHostController
        navController.setGraph(navId)

        // Make the NavController available via the findNavController() APIs
        Navigation.setViewNavController(fragment.requireView(), navController)
    }
}