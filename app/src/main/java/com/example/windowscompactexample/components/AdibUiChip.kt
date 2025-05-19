package com.example.windowscompactexample.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.windowscompactexample.ui.theme.ColorBorder
import com.example.windowscompactexample.ui.theme.ColorContainer
import com.example.windowscompactexample.ui.theme.ColorInteraction
import com.example.windowscompactexample.ui.theme.ColorTextBase

@Composable
fun AdibUiChip(
    modifier: Modifier,
    options: List<String>,
    selectedOption: String?,
    selectedContainerColor: Color = ColorContainer,
    labelColor: Color = ColorTextBase,
    selectedLabelColor: Color = ColorInteraction,
    borderColor: Color = ColorBorder,
    borderRadius: Dp = 1.dp,
    onOptionSelected: (String) -> Unit
) {

    AdibBaseChip(
        modifier = modifier,
        options = options,
        selectedOption = selectedOption,
        selectedContainerColor = selectedContainerColor,
        labelColor = labelColor,
        selectedLabelColor = selectedLabelColor,
        borderColor = borderColor,
        borderRadius = borderRadius,
        onOptionSelected = onOptionSelected
    )
}