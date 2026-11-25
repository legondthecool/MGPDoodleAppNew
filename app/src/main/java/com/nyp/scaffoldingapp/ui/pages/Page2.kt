package com.nyp.scaffoldingapp.ui.pages

import android.app.Activity
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.runtime.*
import androidx.compose.material3.*
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.platform.LocalContext

/**
 * Pages 1 to 3 are placeholders. They exist so the navigation bar has
 * somewhere to go.
 *
 * Replace them. The rubric asks for four sections, each doing a DIFFERENT
 * job — four labels is one section, four times.
 */

@Composable
fun Page2(modifier: Modifier = Modifier) = Settings(modifier)


@Composable
private fun Settings(modifier: Modifier = Modifier) {
    Column(
        modifier.fillMaxSize().padding(40.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Settings",style = MaterialTheme.typography.headlineMedium)
//        Text(
//            "",
//            style = MaterialTheme.typography.bodyLarge,
//            color = MaterialTheme.colorScheme.onSurfaceVariant
//        )
    }
    Column(
        modifier.padding(40.dp),
        verticalArrangement = Arrangement.SpaceEvenly,
        horizontalAlignment = Alignment.Start
    ){
        Spacer(Modifier.height(100.dp))
        BrightnessSlider()
    }
}
@Composable
fun BrightnessSlider() {
    val brightnessActivity = LocalContext.current as? Activity
    var brightness by remember { mutableFloatStateOf(0.5f) }
    Column {
        Text(text = "Brightness: ${(brightness*100).toInt()}%")
        Slider(
            value = brightness,
            onValueChange = { newValue ->
                brightness = newValue
                brightnessActivity?.window?.let{
                    window->
                    val layoutWindow = window.attributes
                    layoutWindow.screenBrightness = newValue
                    window.attributes = layoutWindow
                }
            }
        )
    }
}