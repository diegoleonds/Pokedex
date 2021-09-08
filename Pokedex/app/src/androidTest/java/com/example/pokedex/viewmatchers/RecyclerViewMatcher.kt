package com.example.pokedex.viewmatchers

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.matcher.BoundedMatcher
import org.hamcrest.BaseMatcher
import org.hamcrest.Description
import org.hamcrest.Matcher

fun recyclerViewAtPositionOnView(
    position: Int,
    itemMatcher: Matcher<View>,
    targetViewId: Int
): BaseMatcher<View> {
    return object : BoundedMatcher<View, RecyclerView>(RecyclerView::class.java) {
        override fun matchesSafely(recyclerView: RecyclerView): Boolean {
            val viewHolder = recyclerView.findViewHolderForAdapterPosition(position)
            val targetView: View = viewHolder!!.itemView.findViewById(targetViewId)
            return itemMatcher.matches(targetView)
        }
        override fun describeTo(description: Description) {
            description.appendText("has view id $itemMatcher at position $position")
        }
    }
}