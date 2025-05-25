package com.example.windowscompactexample.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.windowscompactexample.ui.theme.ColorInputsBackground

/**
 * @Author: Yerramma Kethu
 * @Date: 24/05/2025
 */
@Composable
fun UiAccountSelection(
    modifier: Modifier = Modifier,
    title: String?,
    subTitle: String?,
    leadingIcon: Int?,
    isLeadingIconVisible: Boolean = false,
    trailingIcon: Int?,
    isTrailingIconVisible: Boolean = false
) {
    UiPrefilledAccount(
        modifier = modifier
            .background(ColorInputsBackground, RoundedCornerShape(20.dp))
            .padding(horizontal = 20.dp, vertical = 16.dp),
        title = title,
        subTitle = subTitle,
        leadingIcon = leadingIcon,
        isLeadingIconVisible = isLeadingIconVisible,
        trailingIcon = trailingIcon,
        isTrailingIconVisible = isTrailingIconVisible
    )
}