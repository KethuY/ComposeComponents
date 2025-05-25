package com.example.windowscompactexample.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.windowscompactexample.ui.theme.ColorTextSubdued
import com.example.windowscompactexample.ui.theme.StyleBodyMedium
import com.example.windowscompactexample.ui.theme.StyleCaptionRegular

/**
 * @Author: Yerramma Kethu
 * @Date: 22/05/2025
 */
@Composable
internal fun UiPrefilledAccount(
    modifier: Modifier = Modifier,
    title: String? = null,
    subTitle: String? = null,
    leadingIcon: Int? = null,
    isLeadingIconVisible: Boolean = false,
    trailingIcon: Int? = null,
    isTrailingIconVisible: Boolean = false
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        verticalAlignment = Alignment.Top,
        horizontalArrangement = Arrangement.Start
    ) {
        if (leadingIcon != null && isLeadingIconVisible) {
            Image(
                modifier = Modifier
                    .align(Alignment.CenterVertically)
                    .padding(end = 12.dp),
                imageVector = ImageVector.vectorResource(leadingIcon),
                contentDescription = null
            )
        }

        Column(
            modifier = Modifier.weight(1f)
        ) {

            val padding = if (subTitle != null) Modifier.padding(bottom = 8.dp) else Modifier
            if (title != null) {
                Text(
                    text = title,
                    style = StyleBodyMedium,
                    fontWeight = FontWeight.SemiBold,
                    modifier = padding
                )
            }
            if (subTitle != null) {
                Text(
                    color = ColorTextSubdued,
                    text = subTitle,
                    style = StyleCaptionRegular
                )
            }
        }
        if (trailingIcon != null && isTrailingIconVisible) {
            Icon(
                imageVector = ImageVector.vectorResource(trailingIcon),
                contentDescription = null
            )
        }
    }
}