package com.nyp.scaffoldingapp.ui.user

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nyp.scaffoldingapp.data.UserApi
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class UserViewModel : ViewModel() {

    // Private and mutable inside; public and read-only outside.
    private val _state = MutableStateFlow<UiState>(UiState.Loading)
    val state: StateFlow<UiState> = _state

    // TODO 5: add an init block that calls load(),
    //         so the first fetch starts as soon as the ViewModel is created.

    fun load() {
        // TODO 6: launch a coroutine in viewModelScope.
        // TODO 7: inside it, set _state.value to UiState.Loading.
        // TODO 8: try UserApi.fetchUser(), and set _state.value to UiState.Ready(user).
        // TODO 9: catch a failure, and set _state.value to UiState.Failed
        //         with a message a user can act on. Never show the raw exception.
    }
}
