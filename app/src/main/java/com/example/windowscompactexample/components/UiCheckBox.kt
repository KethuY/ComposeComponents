package com.example.windowscompactexample.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import com.example.windowscompactexample.ui.theme.Style16BodyRegular
import com.example.windowscompactexample.ui.theme.StyleBodyMedium

@Composable
fun UiCheckBox(
    modifier: Modifier = Modifier,
    checked: Boolean = false,
    title: String? = null,
    subTitle: String,
    titleStyle: TextStyle = StyleBodyMedium,
    subTitleStyle: TextStyle = Style16BodyRegular,
    onCheckedChange: ((Boolean, String) -> Unit)
) {
    BaseCheckBox(
        modifier = modifier,
        checked = checked,
        title = title,
        subTitle = subTitle,
        titleStyle = titleStyle,
        subTitleStyle = subTitleStyle,
        onCheckedChange = onCheckedChange
    )
}