package com.example.windowscompactexample.components.chip

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.Icon
import androidx.compose.material3.InputChip
import androidx.compose.material3.InputChipDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.windowscompactexample.ui.theme.Dimens.SizeFontWebCaption
import com.example.windowscompactexample.ui.theme.Dimens.SizeSpacingMedium

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun BaseChip(
    modifier: Modifier = Modifier,
    options: List<String>,
    leadingIcon: ImageVector? = null,
    leadingIconTint: Color = Color.Unspecified,
    selectedOption: String? = null,
    selectedOptions: List<String>? = null,
    selectedContainerColor: Color,
    containerColor: Color,
    labelColor: Color,
    selectedLabelColor: Color,
    borderColor: Color,
    borderRadius: Dp,
    isHorizontalScroll: Boolean = false,
    isBorderRequired: Boolean = false,
    isMultiSelectRequired: Boolean = false,
    onOptionsSelected: ((List<String>) -> Unit)? = null,
    onOptionSelected: ((String) -> Unit)? = null
) {

    val chipContainer: @Composable (@Composable () -> Unit) -> Unit = if (isHorizontalScroll) {
        { content ->
            Row(
                modifier = modifier
                    .horizontalScroll(rememberScrollState()),
                horizontalArrangement = Arrangement.spacedBy(SizeSpacingMedium.dp)
            ) {
                content()
            }
        }
    } else {
        { content ->
            FlowRow(
                horizontalArrangement = Arrangement.spacedBy(SizeSpacingMedium.dp),
                verticalArrangement = Arrangement.spacedBy(SizeSpacingMedium.dp)
            ) {
                content()
            }
        }
    }
    chipContainer {
        options.forEach { option ->
            val isSelected =
                selectedOption == option || (isMultiSelectRequired && selectedOptions?.contains(option) == true)
            val selectedBorderColor = if (isBorderRequired && !isSelected) borderColor else Color.Transparent


            InputChip(
                colors = InputChipDefaults.inputChipColors(
                    containerColor = containerColor,
                    labelColor = labelColor,
                    selectedLabelColor = selectedLabelColor,
                    selectedContainerColor = selectedContainerColor,
                ),
                border = BorderStroke(borderRadius, selectedBorderColor),
                selected = isSelected,
                onClick = {
                    if (isMultiSelectRequired) {
                        val updated = selectedOptions.orEmpty().toMutableSet()
                        if (updated.contains(option)) {
                            updated.remove(option)
                        } else {
                            updated.add(option)
                        }
                        onOptionsSelected?.invoke(updated.toList())
                    } else {
                        onOptionSelected?.invoke(option)
                    }

                },
                label = {
                    Text(
                        text = option,
                        modifier = Modifier.padding(vertical = SizeFontWebCaption.dp),
                        textAlign = TextAlign.Center,
                        fontWeight = if (isSelected) FontWeight.SemiBold else FontWeight.Normal
                    )
                },
                trailingIcon = {
                    if (isSelected && leadingIcon!=null) {
                        Icon(
                            imageVector = leadingIcon,
                            contentDescription = null,
                            tint = leadingIconTint
                        )
                    }
                }
            )
        }
    }
}