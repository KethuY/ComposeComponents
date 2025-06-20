package com.example.windowscompactexample.components.pro.uiDatamodels

import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextOverflow
import com.example.windowscompactexample.components.pro.uiModel.AdibTextProperties

/**
 *
 *  @Author: Muhammad Ahad Asim
 *  @Date: 29/05/2025
 * Data model representing the properties of a text component in the Adib UI Kit.
 *
 * Represents the properties of a text component in the Adib UI Kit.
 *
 * This data model defines various attributes for configuring text components,
 * including styling, layout, and behavior.
 *
 * @property modifier Modifier for customizing the layout and behavior of the text component.
 * @property text The text content to be displayed. Defaults to an empty string.
 * @property textStyle The style applied to the text. Defaults to `Adib16BodyMedium`.
 * @property maxLines The maximum number of lines for the text. Defaults to 1.
 * @property overflow Defines how the text should behave when it overflows its bounds.
 * @property softWrap Determines whether the text should wrap when it overflows. Defaults to `true`.
 * @property backgroundColor The background color of the text component. Defaults to `AdibColorBackground`.
 * @property description A description of this component, useful for accessibility purposes.
 *
 */
data class AdibTextUiDataModel(
    override val modifier: Modifier = Modifier,
    override val text: String,
    override val textStyle: TextStyle,
    override val maxLines: Int = Int.MAX_VALUE,
    override val overflow: TextOverflow = TextOverflow.Clip,
    override val softWrap: Boolean = true,
    override val backgroundColor: Color = Color.Unspecified,
    override val description: String =""
) : AdibTextProperties