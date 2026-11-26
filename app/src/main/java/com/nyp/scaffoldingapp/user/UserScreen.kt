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
import androidx.compose.foundation.layout.Spacer
import androidx.compose.ui.graphics.Color

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
    Column(
        modifier = Modifier.padding(16.dp)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth(0.7f)
                .height(24.dp)
                .background(Color.LightGray)
        )

        Spacer(modifier = Modifier.height(8.dp))

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(16.dp)
                .background(Color.LightGray)
        )

        Spacer(modifier = Modifier.height(8.dp))

        Box(
            modifier = Modifier
                .fillMaxWidth(0.5f)
                .height(16.dp)
                .background(Color.LightGray)
        )
    }
}

@Composable
private fun UserContent(user: User) {
    Column(
        modifier = Modifier.padding(16.dp)
    ) {
        Text(
            text = user.name,
            style = MaterialTheme.typography.headlineSmall
        )

        Text(
            text = user.email,
            style = MaterialTheme.typography.bodyLarge
        )

        Text(
            text = user.city,
            style = MaterialTheme.typography.bodyMedium
        )
    }
}
@Composable
private fun ErrorMessage(
    message: String,
    onRetry: () -> Unit
) {
    Column(
        modifier = Modifier.padding(16.dp)
    ) {
        Text(
            text = message,
            color = MaterialTheme.colorScheme.error
        )

        Spacer(modifier = Modifier.height(8.dp))

        Button(
            onClick = onRetry
        ) {
            Text("Retry")
        }
    }
}