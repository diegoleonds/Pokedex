package com.example.pokedex.ui.util

import android.content.Context
import android.os.Build
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.core.content.ContextCompat
import com.google.android.material.card.MaterialCardView

object ColorUtils {
    fun setCardViewBgColor(cardView: MaterialCardView, colorResource: Int) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            cardView.background.setTint(
                getColor(colorResource, cardView.context)
            )
        } else {
            cardView.setBackgroundColor(
                getColor(colorResource, cardView.context)
            )
        }
    }

    fun getColor(colorResource: Int, context: Context): Int {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            ContextCompat.getColor(
                context,
                colorResource
            )
        } else {
            ContextCompat.getColor(context, colorResource)
        }
    }
}