package com.example.windowscompactexample.testing

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.windowscompactexample.components.AdibUiChip

@Composable
fun ChipGroupDemo() {
    var selected by remember { mutableStateOf<String?>(null) }
    val options = listOf("Option 1", "Option 2", "Option 3","Option 4","Option 5")

    Column {
        Text("Selected: ${selected ?: "None"}" , modifier = Modifier.padding(horizontal = 20.dp))
        AdibUiChip(
            modifier = Modifier.padding(20.dp),
            options = options,
            selectedOption = selected,
            onOptionSelected = { selected = it }
        )
    }
}