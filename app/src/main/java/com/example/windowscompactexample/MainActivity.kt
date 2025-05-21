package com.example.windowscompactexample

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Done
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.windowscompactexample.components.UiChip
import com.example.windowscompactexample.components.UiMultiSelectionChip
import com.example.windowscompactexample.components.Details
import com.example.windowscompactexample.testing.ShowAuthPin
import com.example.windowscompactexample.testing.ShowTabs
import com.example.windowscompactexample.ui.theme.ColorContainer
import com.example.windowscompactexample.ui.theme.ColorInteraction
import com.example.windowscompactexample.ui.theme.ColorTextBase
import com.example.windowscompactexample.ui.theme.StyleBodyMedium

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Scaffold(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(vertical = 60.dp, horizontal = 20.dp)
            ) { innerPadding ->
                val options = listOf("Option 1", "Option 2", "Option 3", "Option 4", "Option 5")
                val options1 = listOf("100", "200", "300", "400", "500")
                Column(modifier = Modifier.verticalScroll(rememberScrollState())) {
                    Text(text = stringResource(R.string.app_name), style = StyleBodyMedium, modifier = Modifier.padding(vertical = 16.dp))
                    Text(text = "Over Flow Chip with Single Selection", style = StyleBodyMedium, modifier = Modifier.padding(vertical = 16.dp))
                    OverFlowChip(options)
                    Text(text = "Single line Chip with Single Selection", style = StyleBodyMedium,modifier = Modifier.padding(vertical = 16.dp))
                    SingleLineChip(options)
                    Text(text = "Single line Chip with Single Selection and no border", style = StyleBodyMedium,modifier = Modifier.padding(vertical = 16.dp))
                    SingleLineChipWithoutBorder(options)
                    Text(text = "Single line Chip with Single Selection and Icon, no border", style = StyleBodyMedium,modifier = Modifier.padding(vertical = 16.dp))
                    SingleLineChipWithTrailingIcon(options1, Icons.Default.Done)
                    SingleLineChipWithTrailingIcon(options, Icons.Default.Close)
                    Text(text = "Multi selection  Chip with Single Selection and Icon, no border", style = StyleBodyMedium,modifier = Modifier.padding(vertical = 16.dp))
                    MultiSelectionChip(options)
                    Spacer(modifier = Modifier.height(32.dp))
                    ShowTabs()
                    Spacer(modifier = Modifier.height(32.dp))
                    Details()
                    Spacer(modifier = Modifier.height(32.dp))
                    ShowAuthPin()
                    Spacer(modifier = Modifier.height(32.dp))
                }
            }
        }
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
}
