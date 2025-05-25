package com.example.windowscompactexample.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.windowscompactexample.ui.theme.StyleBodyMedium

/**
 * @Author: Yerramma Kethu
 * @Date: 21/05/2025
 */
@Composable
fun AdibBaseSmallInput(
    modifier: Modifier = Modifier,
    backgroundColor: Color,
    shape: Shape,
    title: String,
    trailingIcon: Int? = null,
    inputPrefix: String,
    inputText: String,
    inputError: String? = null,
    leadingIcon: Int? = null,
    actualLimit: Double? = null,
    minLimit: Double? = null,
    maxLimit: Double? = null,
    onTextChange: (String) -> Unit
) {
    Column(
        modifier = modifier
            .background(color = backgroundColor, shape = shape)
            .padding(horizontal = 20.dp, vertical = 16.dp)
    ) {

        UiPrefilledAccount(
            subTitle = title,
            trailingIcon = trailingIcon,
        )

        UiInputTextField(
            modifier = Modifier.padding(top = 8.dp),
            inputTextStyle = StyleBodyMedium.copy(fontWeight = FontWeight.SemiBold),
            leadingIcon = leadingIcon,
            inputPrefix = inputPrefix,
            inputText = inputText,
            actualLimit = actualLimit,
            maxLimit = maxLimit,
            minLimit = minLimit,
            inputError = inputError,
            onTextChange = onTextChange
        )
    }
}