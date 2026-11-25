package com.nyp.scaffoldingapp.ui.user

import com.nyp.scaffoldingapp.data.User

/** Every state the screen can be in. Nothing else is possible. */
sealed interface UiState {
    object Loading : UiState
    data class Ready(val user: User) : UiState
    data class Failed(val message: String) : UiState
}
