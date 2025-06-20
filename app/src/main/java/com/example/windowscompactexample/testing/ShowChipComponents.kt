package com.example.windowscompactexample.testing

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Done
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.windowscompactexample.R
import com.example.windowscompactexample.components.Details
import com.example.windowscompactexample.components.UiChip
import com.example.windowscompactexample.components.UiMultiSelectionChip
import com.example.windowscompactexample.ui.theme.ColorContainer
import com.example.windowscompactexample.ui.theme.ColorInteraction
import com.example.windowscompactexample.ui.theme.ColorSemanticSuccessTwo
import com.example.windowscompactexample.ui.theme.ColorTextBase
import com.example.windowscompactexample.ui.theme.StyleBodyMedium
import kotlinx.coroutines.launch

/**
 * @Author: Yerramma Kethu
 * @Date: 20/06/2025
 */

@Composable
fun ChipComponents() {
    val options = listOf("Option 1", "Option 2", "Option 3", "Option 4", "Option 5")
    val options1 = listOf("100", "200", "300", "400", "500")

    Text(
        text = stringResource(R.string.app_name),
        style = StyleBodyMedium,
        modifier = Modifier.padding(vertical = 16.dp)
    )
    Text(
        text = "Over Flow Chip with Single Selection",
        style = StyleBodyMedium,
        modifier = Modifier.padding(vertical = 16.dp)
    )
    OverFlowChip(options)
    Text(
        text = "Single line Chip with Single Selection",
        style = StyleBodyMedium,
        modifier = Modifier.padding(vertical = 16.dp)
    )
    SingleLineChip(options)
    Text(
        text = "Single line Chip with Single Selection and no border",
        style = StyleBodyMedium,
        modifier = Modifier.padding(vertical = 16.dp)
    )
    SingleLineChipWithoutBorder(options)
    Text(
        text = "Single line Chip with Single Selection and Icon, no border",
        style = StyleBodyMedium,
        modifier = Modifier.padding(vertical = 16.dp)
    )
    SingleLineChipWithTrailingIcon(options1, Icons.Default.Done)
    SingleLineChipWithTrailingIcon(options, Icons.Default.Close)
    Text(
        text = "Multi selection  Chip with Single Selection and Icon, no border",
        style = StyleBodyMedium,
        modifier = Modifier.padding(vertical = 16.dp)
    )
    MultiSelectionChip(options)
    Spacer(modifier = Modifier.height(32.dp))
    ShowTabs()
    Spacer(modifier = Modifier.height(32.dp))
    Details()
    Spacer(modifier = Modifier.height(32.dp))
    ShowAuthPin()
    Spacer(modifier = Modifier.height(32.dp))
}

@Composable
private fun MultiSelectionChip(options3: List<String>) {
    val selectedItems = remember { mutableStateListOf<String>() }

    UiMultiSelectionChip(
        modifier = Modifier.padding(top = 16.dp),
        isHorizontalScroll = true,
        isBorderRequired = false,
        selectedContainerColor = ColorInteraction,
        containerColor = ColorContainer,
        labelColor = ColorTextBase,
        selectedLabelColor = Color.White,
        leadingIcon = Icons.Default.Close,
        leadingIconTint = Color.White,
        options = options3,
        selectedOptions = selectedItems,
        onOptionsSelected = {
            selectedItems.clear()
            selectedItems.addAll(it)
            println("Kethu4 $selectedItems")
        }
    )
}


@Composable
private fun SingleLineChipWithTrailingIcon(options3: List<String>, done: ImageVector) {
    var selected by remember { mutableStateOf<String?>(null) }
    UiChip(
        modifier = Modifier.padding(top = 16.dp),
        isHorizontalScroll = true,
        isBorderRequired = false,
        selectedContainerColor = ColorInteraction,
        containerColor = ColorContainer,
        labelColor = ColorTextBase,
        selectedLabelColor = Color.White,
        leadingIcon = done,
        leadingIconTint = Color.White,
        options = options3,
        selectedOption = selected,
        onOptionSelected = {
            selected = it
            println("Kethu3 $it")
        },
    )
}

@Composable
private fun SingleLineChipWithoutBorder(options3: List<String>) {
    var selected by remember { mutableStateOf<String?>(null) }
    UiChip(
        modifier = Modifier.padding(top = 16.dp),
        isHorizontalScroll = true,
        isBorderRequired = false,
        selectedContainerColor = ColorInteraction,
        containerColor = ColorContainer,
        labelColor = ColorTextBase,
        selectedLabelColor = Color.White,
        options = options3,
        selectedOption = selected,
        onOptionSelected = {
            selected = it
            println("Kethu2 $it")
        },
    )
}

@Composable
private fun SingleLineChip(options3: List<String>) {
    var selected by remember { mutableStateOf<String?>(null) }
    UiChip(
        modifier = Modifier.padding(top = 16.dp),
        isHorizontalScroll = true,
        isBorderRequired = true,
        options = options3,
        selectedOption = selected,
        onOptionSelected = {
            selected = it
            println("Kethu1 $it")
        },
    )
}

@Composable
private fun OverFlowChip(options3: List<String>) {
    var selected by remember { mutableStateOf<String?>(null) }

    UiChip(
        isBorderRequired = true,
        options = options3,
        selectedOption = selected,
        onOptionSelected = {
            selected = it
            println("Kethu $it")
        },
    )
}


@Composable
fun CustomSnackbarScaffold() {
    val snackbarHostState = remember { SnackbarHostState() }
    val coroutineScope = rememberCoroutineScope()

    Scaffold(
        snackbarHost = {
            SnackbarHost(hostState = snackbarHostState) {
                CustomSnackBarContent()
            }
        },
        floatingActionButton = {
            ExtendedFloatingActionButton(
                onClick = {
                    coroutineScope.launch {
                        snackbarHostState.showSnackbar("", duration = SnackbarDuration.Short)
                    }
                }
            ) {
                Text("Show Snackbar")
            }
        }
    ) { padding ->
        Box(modifier = Modifier.padding(padding))
    }
}


@Composable
fun CustomSnackBarContent() {
    Surface(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth(),
        color = ColorSemanticSuccessTwo,
        shape = RoundedCornerShape(12.dp),
        tonalElevation = 6.dp
    ) {
        Row(
            modifier = Modifier
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "Beneficiary added successfully",
                color = Color.White,
                textAlign = TextAlign.Start,
                modifier = Modifier.weight(1f)
            )

            Icon(
                imageVector = Icons.Default.Info,
                contentDescription = null,
                tint = Color.White
            )
        }
    }
}