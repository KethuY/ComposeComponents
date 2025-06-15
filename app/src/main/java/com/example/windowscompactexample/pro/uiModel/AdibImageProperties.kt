package com.example.windowscompactexample.pro.uiModel

import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import com.adib.mobile.uikit.base.AdibBaseView

/**
 *  @Author: Muhammad Ahad Asim
 *  @Date: 29/05/2025
 * Interface representing the properties of an image component in the Adib UI Kit.
 *
 * @property drawable Resource ID of the drawable to be displayed.
 * @property contentScale Defines how the image content should be scaled within its bounds.
 * @property alpha Opacity level of the image, ranging from 0.0 (completely transparent) to 1.0 (completely opaque).
 * @property colorFilter Optional color filter to apply to the image.
 */
interface AdibImageProperties : AdibBaseView {
    val drawable: Int
    val contentScale: ContentScale
    val alpha: Float
    val colorFilter: ColorFilter?
}