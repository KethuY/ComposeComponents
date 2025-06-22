package com.example.windowscompactexample.components.input.amount

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.dp
import com.example.windowscompactexample.components.input.models.AmountInputUIDataModel
import com.example.windowscompactexample.components.input.models.AmountUICallBackListener
import com.example.windowscompactexample.components.pro.uiModel.AdibImageProperties
import com.example.windowscompactexample.components.pro.uiModel.AdibTextProperties
import com.example.windowscompactexample.ui.theme.ColorSegmentMassTwo
import com.example.windowscompactexample.ui.theme.Dimens.AdibSizeFontMobileHfour
import com.example.windowscompactexample.ui.theme.Dimens.AdibSizeSpacingMedium

/**
 * @Author: Yerramma Kethu
 * @Date: 20/06/2025
 */
@Composable
fun UiSmallInput(
    accountTitle: AdibTextProperties,
    inputDataModel: AmountInputUIDataModel, // todo need to use others input field properties
    modifier: Modifier = Modifier,
    shape: Shape = RoundedCornerShape(AdibSizeFontMobileHfour.dp),
    backgroundColor: Color = ColorSegmentMassTwo,
    paddingValues: PaddingValues = PaddingValues(
        horizontal = AdibSizeFontMobileHfour.dp,
        vertical = AdibSizeSpacingMedium.dp
    ),
    amountIcon: AdibImageProperties,
    amountError: AdibTextProperties? = null,
    callBacks: AmountUICallBackListener
) {
    BaseSmallInput(
        modifier = modifier
            .background(color = backgroundColor, shape = shape)
            .padding(paddingValues),
        accountTitle = accountTitle,
        amountError = amountError,
        amountIcon = amountIcon,
        inputDataModel = inputDataModel,
        callBacks = callBacks
    )
}