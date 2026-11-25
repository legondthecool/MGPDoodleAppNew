package com.nyp.scaffoldingapp.ui.pages

import android.R.attr.tint
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.unit.dp
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.*
import androidx.compose.ui.graphics.Color
@Composable
fun Page3(modifier: Modifier = Modifier) = Placeholder2("Canvas selector", modifier)

fun CanvaFunction(ID : Int, onClick: () -> Unit) {

}

@Composable

private fun Placeholder2(label: String, modifier: Modifier = Modifier) {

    Column(
        modifier.fillMaxSize().padding(24.dp, 10.dp),
    ) {

        Spacer(modifier.height(80.dp));
        Text(label, style = MaterialTheme.typography.headlineMedium)
        modifier.fillMaxSize().padding(24.dp, 12.dp)
        Button(
            onClick = { },
            modifier = Modifier
                .size(100.dp),
            shape = RectangleShape,
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.Gray.copy(alpha = 0.5f)
            )

        ) {
            Icon(
                imageVector = Icons.Default.Add,
                contentDescription = " ",
                modifier = Modifier.size(32.dp)
            )
        }
    }

}