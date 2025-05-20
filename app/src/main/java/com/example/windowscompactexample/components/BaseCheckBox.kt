package com.example.windowscompactexample.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults.colors
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import com.example.windowscompactexample.ui.theme.ColorInteraction
import com.example.windowscompactexample.ui.theme.ColorTextSubdued

@Composable
internal fun BaseCheckBox(
    modifier: Modifier = Modifier,
    checked: Boolean = false,
    title: String? = null,
    subTitle: String,
    titleStyle: TextStyle = LocalTextStyle.current,
    subTitleStyle: TextStyle = LocalTextStyle.current,
    onCheckedChange: ((Boolean,String) -> Unit)
) {
    val (checkedState, onStateChange) = remember { mutableStateOf(checked) }
    Row(
        modifier.clickable(
            onClick = {
                val isChecked = !checkedState
                onStateChange(isChecked)
                onCheckedChange.invoke(isChecked, subTitle)
            },
            role = Role.Checkbox,
            interactionSource = remember { MutableInteractionSource() },
            indication = null
        )
    ) {
        Checkbox(
            checked = checkedState,
            onCheckedChange = null,
            colors = colors(
                checkedColor = ColorInteraction,
                uncheckedColor = ColorTextSubdued,
                checkmarkColor = Color.White
            ),
        )

        Column(modifier = Modifier.padding(start = 12.dp)) {
            if (!title.isNullOrBlank()) {
                Text(
                    text = title,
                    style = titleStyle,
                )
            }
            Text(
                text = subTitle,
                style = subTitleStyle
            )
        }
    }
}