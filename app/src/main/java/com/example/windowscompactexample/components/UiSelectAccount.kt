package com.example.windowscompactexample.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.windowscompactexample.R
import com.example.windowscompactexample.ui.theme.ColorInputsBackground
import com.example.windowscompactexample.ui.theme.ColorTextBase

/**
 * @Author: Yerramma Kethu
 * @Date: 22/05/2025
 */
@Composable
fun UiSelectAccount(
    modifier: Modifier = Modifier,
    backgroundColor: Color = ColorInputsBackground,
    shape: RoundedCornerShape = RoundedCornerShape(20.dp),
    title: String = "Select an account",
    leadingIcon: Int = R.drawable.ic_plus_round_solid,
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .background(color = backgroundColor, shape = shape)
            .padding(horizontal = 20.dp, vertical = 8.dp)
    ) {

        UiActionTextWithLeadingIcon(
            leadingIcon = leadingIcon,
            title = title,
            horizontalSpacing = 12.dp,
            titleColor = ColorTextBase
        )
    }
}
