package com.example.windowscompactexample.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.windowscompactexample.ui.theme.ColorBorder
import com.example.windowscompactexample.ui.theme.ColorContainer
import com.example.windowscompactexample.ui.theme.ColorInteraction
import com.example.windowscompactexample.ui.theme.ColorTextBase

@Composable
fun UiMultiSelectionChip(
    modifier: Modifier = Modifier,
    options: List<String>,
    selectedOptions: List<String>? = null,
    leadingIcon: ImageVector? = null,
    leadingIconTint: Color = Color.Unspecified,
    selectedContainerColor: Color = ColorContainer,
    containerColor: Color = Color.Unspecified,
    labelColor: Color = ColorTextBase,
    selectedLabelColor: Color = ColorInteraction,
    borderColor: Color = ColorBorder,
    borderRadius: Dp = 1.dp,
    isBorderRequired: Boolean = false,
    isHorizontalScroll: Boolean = false,
    onOptionsSelected: ((List<String>) -> Unit)? = null,
) {

    BaseChip(
        modifier = modifier,
        options = options,
        leadingIcon = leadingIcon,
        leadingIconTint = leadingIconTint,
        isHorizontalScroll = isHorizontalScroll,
        isMultiSelectRequired = true,
        isBorderRequired = isBorderRequired,
        selectedContainerColor = selectedContainerColor,
        labelColor = labelColor,
        selectedLabelColor = selectedLabelColor,
        borderColor = borderColor,
        borderRadius = borderRadius,
        containerColor = containerColor,
        selectedOptions = selectedOptions,
        onOptionsSelected = onOptionsSelected,
    )
}