package com.nyp.scaffoldingapp.ui.user

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.nyp.scaffoldingapp.data.User

@Composable
fun UserScreen(
    modifier: Modifier = Modifier,
    viewModel: UserViewModel = viewModel()          // the same ViewModel survives rotation
) {
    // Subscribe: whenever the ViewModel sets a new state, this screen recomposes.
    val state by viewModel.state.collectAsState()

    Box(modifier.fillMaxSize().padding(24.dp)) {
        // "val current = state" is needed: Kotlin cannot smart-cast a delegated property.
        when (val current = state) {
            is UiState.Loading -> Skeleton()
            is UiState.Ready -> UserContent(current.user)
            is UiState.Failed -> ErrorMessage(current.message, onRetry = viewModel::load)
        }
    }
}

@Composable
private fun Skeleton() {
    // TODO 10: show grey blocks in the shape of the content, not a spinner.
    //          See the "skeleton" slide: Box + height + fillMaxWidth + background.
    Text("Loading…")
}

@Composable
private fun UserContent(user: User) {
    // TODO 11: show the user's name, email and city, using the theme's text styles.
    Text(user.name)
}

@Composable
private fun ErrorMessage(message: String, onRetry: () -> Unit) {
    // TODO 12: show the message, and a Retry button that calls onRetry.
    Text(message)
}
