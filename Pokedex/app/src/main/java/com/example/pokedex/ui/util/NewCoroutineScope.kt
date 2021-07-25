package com.example.pokedex.ui.util

import kotlinx.coroutines.*
import java.util.*
import kotlin.coroutines.CoroutineContext

class NewCoroutineScope(override val coroutineContext: NewCourotineDispatcher) : CoroutineScope {

    var _coroutineContext = coroutineContext

    val isAnyJobRunning: Boolean
        get() {
            coroutineContext.jobs.removeAll { !it.isActive }
            return _coroutineContext.jobs.isNotEmpty()
        }
}