package com.nyp.scaffoldingapp.ui.pages

import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
@Composable
fun Page3(modifier: Modifier = Modifier) = Placeholder2("Page 3", modifier)

fun CanvaFunction(ID : Int, onClick: () -> Unit) {

}
@Composable
private fun Placeholder2(label: String, modifier: Modifier = Modifier) {
    Column(
        modifier.fillMaxSize().padding(24.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(label, style = MaterialTheme.typography.headlineMedium)
        Spacer(Modifier.height(8.dp))

    }
}