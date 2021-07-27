package com.example.pokedex.extensions

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.joinAll

suspend fun CoroutineScope.wait() {
    val job = this.coroutineContext[Job]
    val jobs = job?.children?.toMutableList()
    jobs?.removeAll { !it.isActive }
    jobs?.joinAll()
}