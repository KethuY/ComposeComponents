package com.example.windowscompactexample.components.pro

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.example.windowscompactexample.components.pro.uiModel.AdibTextProperties

/**
 *  @Author: Muhammad Ahad Asim
 *  @Date: 29/05/2025
 *
 * A composable function that displays a text component in the Adib UI Kit.
 *
 * @param adibTextUiDataModel The data model containing properties for the text component.
 *
 * The function uses the properties defined in the `AdibTextUiDataModel` to render the text
 */
@Composable
fun AdibText(
    adibTextUiDataModel: AdibTextProperties
) = with(adibTextUiDataModel) {
    Text(
        modifier = modifier,
        text = text,
        style = textStyle,
        overflow = overflow,
        maxLines = maxLines,
        softWrap = softWrap
    )
}