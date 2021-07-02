package com.alexchan.finite_state_machine.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alexchan.finite_state_machine.core.state.Event
import com.alexchan.finite_state_machine.core.state.Result
import com.alexchan.finite_state_machine.core.state.State
import com.alexchan.finite_state_machine.model.Photo
import com.alexchan.finite_state_machine.util.services
import kotlinx.coroutines.launch

class HomeViewModel : ViewModel() {

    var state = MutableLiveData<State<List<Photo>>>(State.Initial)

    private fun loadData() = viewModelScope.launch {
        when (val result = services.photoService.fetchPhotos(1)) {
            is Result.Success -> {
                transition(Event.LoadSuccess(result.value))
            }

            is Result.Failure -> {
                transition(Event.LoadFailure(result.error))
            }
        }
    }

    fun startInitialLoad() {
        transition(Event.StartInitialLoad)
    }

    fun retryInitialLoad() {
        transition(Event.RetryInitialLoad)
    }

    private fun transition(event: Event<List<Photo>>) {
        when {

            state.value is State.Initial && event is Event.StartInitialLoad -> {
                state.value = State.Loading
                loadData()
            }

            state.value is State.Loading && event is Event.LoadSuccess -> {
                state.value = State.Loaded(event.data)
            }

            state.value is State.Loading && event is Event.LoadFailure -> {
                state.value = State.LoadingFailed(event.error)
            }

            state.value is State.LoadingFailed && event is Event.RetryInitialLoad -> {
                state.value = State.RetryingLoad
                loadData()
            }

            state.value is State.RetryingLoad && event is Event.LoadSuccess -> {
                state.value = State.Loaded(event.data)
            }

            state.value is State.RetryingLoad && event is Event.LoadFailure -> {
                state.value = State.LoadingFailed(event.error)
            }

            else -> {
                throw (Throwable("invalid event $event for ${state.value}"))
            }
        }
    }
}