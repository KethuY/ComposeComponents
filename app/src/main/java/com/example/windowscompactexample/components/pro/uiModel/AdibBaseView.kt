package com.adib.mobile.uikit.base


import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

/**
 *  @Author: Muhammad Ahad Asim
 *  @Date: 29/05/2025
 * Interface representing a view component in the Adib UI Kit.
 *
 * @property modifier Modifier to be applied to the view.
 * @property backgroundColor Background color of the view.
 * @property description Description of the view, useful for accessibility purposes.
 */
interface AdibBaseView {
    val modifier: Modifier
    val backgroundColor: Color
    val description: String
}