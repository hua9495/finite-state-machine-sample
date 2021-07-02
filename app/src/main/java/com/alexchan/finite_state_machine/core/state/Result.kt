package com.alexchan.finite_state_machine.core.state

// Result wrapper designed specifically for Kotlin coroutines.
sealed class Result<out T> {
    data class Success<out T>(val value: T) : Result<T>()
    data class Failure(val error: Throwable) : Result<Nothing>()
}
