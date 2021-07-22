package com.example.pokedex.viewactions

import android.view.View
import androidx.test.espresso.UiController
import androidx.test.espresso.ViewAction
import androidx.test.espresso.matcher.ViewMatchers.isAssignableFrom
import com.google.android.material.appbar.AppBarLayout
import org.hamcrest.Matcher

object CollapseAppBarLayout {
    fun collapseAppBarLayout(): ViewAction {
        return object : ViewAction {
            override fun getConstraints(): Matcher<View> {
                return isAssignableFrom(AppBarLayout::class.java)
            }

            override fun getDescription() = "Collapse App Bar Layout"

            override fun perform(uiController: UiController, view: View) {
                val appBarLayout = view as AppBarLayout
                appBarLayout.setExpanded(false)
                uiController.loopMainThreadUntilIdle()
            }
        }
    }
}
