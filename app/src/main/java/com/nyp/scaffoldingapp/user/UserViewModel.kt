package com.nyp.scaffoldingapp.ui.user

import android.util.Log
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
                Log.d("DEBUG", "COROUTINE STARTED")
                val user = UserApi.fetchUser()
                Log.d("DEBUG", "FETCH COMPLETE")
                _state.value = UiState.Ready(user)

                Log.d("DEBUG", "SUCCESS")
            } catch (e: Exception) {
                _state.value = UiState.Failed(
                    "Unable to load user data. Please check your connection and try again."
                )

                Log.d("DEBUG", "FAILURE", e)
            }
        }
    }
}
