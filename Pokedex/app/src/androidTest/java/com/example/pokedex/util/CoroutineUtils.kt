package com.example.pokedex.util

import androidx.test.espresso.ViewInteraction
import com.example.pokedex.extensions.wait
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.runBlocking

fun afterCoroutine(coroutineScope: CoroutineScope, function: () -> Unit) = runBlocking {
    coroutineScope.wait()
    function()
}
