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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.windowscompactexample.ui.theme.ColorInteraction
import com.example.windowscompactexample.ui.theme.StyleBodyMedium

@Composable
fun UiActionTextWithLeadingIcon(
    modifier: Modifier = Modifier,
    leadingIcon: Int,
    leadingIconTint: Color = ColorInteraction,
    horizontalSpacing: Dp = 8.dp,
    title: String,
    titleColor: Color = ColorInteraction,
    horizontalAlignment: Arrangement.Horizontal = Arrangement.Start
) {
    Row(
        modifier = modifier.padding(vertical = 16.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = horizontalAlignment
    ) {
        Icon(
            imageVector = ImageVector.vectorResource(leadingIcon),
            contentDescription = null,
            tint = leadingIconTint
        )
        Text(
            modifier = Modifier.padding(start = horizontalSpacing),
            text = title,
            fontWeight = FontWeight.SemiBold,
            style = StyleBodyMedium,
            color = titleColor
        )
    }
}