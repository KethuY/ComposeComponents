package com.example.windowscompactexample.pro

import androidx.compose.foundation.Image
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import com.example.windowscompactexample.pro.uiModel.AdibImageProperties

/**
 *
 *  @Author: Muhammad Ahad Asim
 *  @Date: 29/05/2025
 *
 * A composable function that displays an image component in the Adib UI Kit.
 *
 * @param adibImageUiDataModel The data model containing properties for the image component.
 *
 * The function uses the properties defined in the `AdibImageUiDataModel` to render the image
 */
@Composable
fun AdibImage(
    adibImageUiDataModel: AdibImageProperties
) {
    with(adibImageUiDataModel) {
        Image(
            modifier = modifier,
            painter = painterResource(id = drawable),
            contentDescription = description,
            contentScale = contentScale,
            alpha = alpha,
            colorFilter = colorFilter
        )
    }
}