package com.example.windowscompactexample.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.material3.InputChip
import androidx.compose.material3.InputChipDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.windowscompactexample.ui.theme.Dimens.SizeFontWebCaption
import com.example.windowscompactexample.ui.theme.Dimens.SizeSpacingMedium

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun AdibBaseChip(
    modifier: Modifier = Modifier,
    options: List<String>,
    selectedOption: String?,
    selectedContainerColor: Color,
    labelColor: Color,
    selectedLabelColor: Color,
    borderColor: Color,
    borderRadius: Dp,
    onOptionSelected: (String) -> Unit
) {
    FlowRow(
        modifier = modifier.selectableGroup(),
        horizontalArrangement = Arrangement.spacedBy(SizeSpacingMedium.dp),
        verticalArrangement = Arrangement.spacedBy(SizeSpacingMedium.dp)
    ) {
        options.forEach { option ->
            val isSelected = selectedOption == option
            val selectedBorderColor = if (isSelected) Color.Transparent else borderColor
            InputChip(
                colors = InputChipDefaults.inputChipColors(
                    labelColor = labelColor,
                    selectedLabelColor = selectedLabelColor,
                    selectedContainerColor = selectedContainerColor,
                ),
                border = BorderStroke(borderRadius, selectedBorderColor),
                selected = isSelected,
                onClick = { onOptionSelected(option) },
                label = {
                    Text(option, modifier = Modifier.padding(SizeFontWebCaption.dp), textAlign = TextAlign.Center)
                }
            )
        }
    }
}