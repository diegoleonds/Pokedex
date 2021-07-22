package com.example.pokedex.viewmatchers

import android.view.View
import com.google.android.material.appbar.CollapsingToolbarLayout
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.TypeSafeMatcher

fun withTitle(title: String): Matcher<View> {
    return object : TypeSafeMatcher<View>() {
        override fun matchesSafely(view: View): Boolean {
            val clpToolbar = view as CollapsingToolbarLayout
            return clpToolbar.title.toString() == title
        }

        override fun describeTo(description: Description) {
            description.appendText("CollapsingToolbarLayout title")
        }
    }
}