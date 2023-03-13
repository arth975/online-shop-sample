package ru.spoonbill.droid.utils

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

fun LifecycleOwner.addRepeatedJob(
    state: Lifecycle.State,
    block: suspend CoroutineScope.() -> Unit
): Job = lifecycleScope.launch {
    repeatOnLifecycle(state, block)
}