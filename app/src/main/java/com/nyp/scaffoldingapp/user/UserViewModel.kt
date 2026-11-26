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
    init {
        load()
    }

    fun load() {
        viewModelScope.launch {
            _state.value = UiState.Loading
            try {
                val user = UserApi.fetchUser()
                _state.value = UiState.Ready(user)

            } catch (e: Exception) {
                _state.value = UiState.Failed(
                    "Unable to load user data. Please check your connection and try again."
                )
            }
        }
    }
}
