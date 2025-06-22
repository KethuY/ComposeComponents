package com.example.windowscompactexample.components.input.models

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.text2.input.InputTransformation
import androidx.compose.runtime.Stable
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import com.example.windowscompactexample.ui.theme.Adib24H3Medium

/**
 * @Author: Yerramma Kethu
 * @Date: 22/06/2025
 * temporarlu created need to reuse the others or refacr this
 */
@Stable
@OptIn(ExperimentalFoundationApi::class)
data class AmountInputUIDataModel(
    val inputAmount: String,
    val inputCurrency: String,
    val inputTextStyle: TextStyle = Adib24H3Medium.copy(fontWeight = FontWeight.SemiBold),
    val inputPlaceholder: String = "0.00",
    val inputTransformation: InputTransformation? = null,
    val availableAmount: Double?
)