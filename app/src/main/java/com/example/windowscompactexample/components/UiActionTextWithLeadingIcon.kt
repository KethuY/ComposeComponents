package com.example.windowscompactexample.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.windowscompactexample.ui.theme.AdibColorTextWhite
import com.example.windowscompactexample.ui.theme.ColorInteraction
import com.example.windowscompactexample.ui.theme.StyleBodyMedium

@Composable
fun UiActionTextWithLeadingIcon(
    modifier: Modifier = Modifier,
    leadingIcon: Int? = null,
    leadingIconTint: Color = ColorInteraction,
    horizontalSpacing: Dp = 8.dp,
    title: String,
    titleColor: Color = ColorInteraction,
    titleStyle: TextStyle = StyleBodyMedium,
    horizontalAlignment: Arrangement.Horizontal = Arrangement.Start
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = horizontalAlignment
    ) {
        if (leadingIcon != null) {
            Icon(
                modifier = Modifier.padding(end = horizontalSpacing),
                imageVector = ImageVector.vectorResource(leadingIcon),
                contentDescription = null,
                tint = leadingIconTint
            )
        }
        Text(
            text = title,
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.SemiBold,
            style = titleStyle,
            color = titleColor
        )
    }
}