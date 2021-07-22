package com.example.pokedex.viewmatchers

import android.content.res.Resources
import android.graphics.drawable.ColorDrawable
import android.util.Log
import android.view.View
import androidx.core.content.res.ResourcesCompat
import com.example.pokedex.ui.util.ColorUtils
import com.google.android.material.card.MaterialCardView
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.TypeSafeMatcher

//    fun withBackgroundColor(expectedResourceId: Int): Matcher<View> {
//        return object : TypeSafeMatcher<View>() {
//            override fun matchesSafely(view: View): Boolean {
//                val cardView = view as MaterialCardView
//                val expectedColor = ColorUtils.getColor(expectedResourceId, cardView.context)
//                cardView.background.colorFilter
//            }
//
//            override fun describeTo(description: Description) {
//                description.appendText("Cardview should have a backgroundcolor = $expectedResourceId")
//            }
//        }
//    }
