package com.example.windowscompactexample.components.pro.uiModel

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.text2.input.InputTransformation
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextOverflow
import com.adib.mobile.uikit.base.AdibBaseView

/**
 *  @Author: Muhammad Ahad Asim
 *  @Date: 29/05/2025
 * Interface representing the properties of a text component in the Adib UI Kit.
 *
 * @property text The text content to be displayed.
 * @property textStyle The style of the text, including font size, weight, etc.
 * @property maxLines The maximum number of lines for the text.
 * @property overflow How the text should behave when it overflows its bounds.
 * @property softWrap Whether the text should wrap when it overflows.
 */
interface AdibTextProperties : AdibBaseView {
    val text: String
    val textStyle: TextStyle
    val maxLines: Int
    val overflow: TextOverflow
    val softWrap: Boolean
}

interface AdibAmountInputTextProperties : AdibBaseView {
    val inputText: String
    val textStyle: TextStyle
    val prefix: String
    val placeholder: String
    val availableAmount: Double?
    @OptIn(ExperimentalFoundationApi::class)
    val inputTransformation: InputTransformation?
}