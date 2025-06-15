package com.example.windowscompactexample.pro.uiDatamodels

import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import com.example.windowscompactexample.pro.uiModel.AdibImageProperties

/**
 *  @Author: Muhammad Ahad Asim
 *  @Date: 29/05/2025
 * Data model representing the properties of an image component in the Adib UI Kit.
 *
 * @property modifier Modifier to be applied to the image component.
 * @property drawable Resource ID of the drawable to be displayed.
 * @property contentScale Defines the scaling of the image within its bounds.
 * @property alpha Opacity level of the image.
 * @property colorFilter Optional color filter to apply to the image.
 * @property backgroundColor Background color of the image component.
 * @property description A description of the image component, useful for accessibility purposes.
 */
data class AdibImageUiDataModel(
    override val modifier: Modifier = Modifier,
    override val drawable: Int,
    override val contentScale: ContentScale = ContentScale.Fit,
    override val alpha: Float = 1f,
    override val colorFilter: ColorFilter? = null,
    override val backgroundColor: Color = Color.Transparent,
    override val description: String = ""
) : AdibImageProperties
