package com.example.pokedex.ui.util

import kotlinx.coroutines.*
import java.util.*
import kotlin.coroutines.CoroutineContext

class NewCourotineDispatcher(val scope: CoroutineDispatcher) : CoroutineDispatcher() {

    override fun dispatch(context: CoroutineContext, block: Runnable) {
        context[Job]?.let { addNewJob(it) }
        scope.dispatch(context, block)
    }

    val jobs = Collections.newSetFromMap(WeakHashMap<Job, Boolean>())

    var completionEvent: (() -> Unit)? = null

    fun addNewJob(job: Job): Boolean {
        job.invokeOnCompletion {
            completionEvent?.invoke()
        }
        return jobs.add(job)
    }

    @InternalCoroutinesApi
    override fun dispatchYield(context: CoroutineContext, block: Runnable) {
        context[Job]?.let { addNewJob(it) }
        scope.dispatchYield(context, block)
    }

    @ExperimentalCoroutinesApi
    override fun isDispatchNeeded(context: CoroutineContext): Boolean {
        context[Job]?.let { addNewJob(it) }
        return scope.isDispatchNeeded(context)
    }
}