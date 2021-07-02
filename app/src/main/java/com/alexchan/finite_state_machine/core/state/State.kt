package com.alexchan.finite_state_machine.core.state

sealed class State<out R> {
    object Initial : State<Nothing>()
    object Loading : State<Nothing>()
    class Loaded<out T>(val data: T) : State<T>()
    class LoadingFailed(val error: Throwable?) : State<Nothing>()
    object RetryingLoad : State<Nothing>()
}

sealed class Event<out R> {
    object StartInitialLoad : Event<Nothing>()
    class LoadSuccess<out T>(val data: T) : Event<T>()
    class LoadFailure(val error: Throwable?) : Event<Nothing>()
    object RetryInitialLoad : Event<Nothing>()
}
