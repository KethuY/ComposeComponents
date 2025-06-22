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
import com.example.windowscompactexample.ui.theme.ColorInputsBackground
import com.example.windowscompactexample.ui.theme.Dimens.AdibSizeFontMobileHfour
import com.example.windowscompactexample.ui.theme.Dimens.AdibSizeFontWebCaption
import com.example.windowscompactexample.ui.theme.Dimens.AdibSizeSpacingLarge
import com.example.windowscompactexample.ui.theme.Dimens.AdibSizeSpacingMedium

/**
 * @Author: Yerramma Kethu
 * @Date: 21/06/2025
 */
@Composable
fun UiAccountSelectionWithInputDivider(
    accountTitle: AdibTextProperties,
    balanceTitle: AdibTextProperties,
    inputAmount: AmountInputUIDataModel,// todo need to use others input field properties
    amountIcon: AdibImageProperties?,
    modifier: Modifier = Modifier,
    background: Color = ColorInputsBackground,
    shape: Shape = RoundedCornerShape(AdibSizeFontMobileHfour.dp),
    paddingValues: PaddingValues = PaddingValues(
        start = AdibSizeFontMobileHfour.dp, end =
        AdibSizeSpacingMedium.dp,
        top = AdibSizeFontWebCaption.dp,
        bottom = AdibSizeSpacingLarge.dp
    ),
    selectionIcon: AdibImageProperties? = null,
    balanceError: AdibTextProperties? = null,
    amountError: AdibTextProperties? = null,
    amountLabel: AdibTextProperties? = null,
    callBacks: AmountUICallBackListener
) {
    BaseBigInput(
        modifier = modifier
            .background(color = background, shape = shape)
            .padding(paddingValues),
        accountTitle = accountTitle,
        balanceTitle = balanceTitle,
        balanceError = balanceError,
        selectionIcon = selectionIcon,
        amountIcon = amountIcon,
        amountError = amountError,
        amountLabel = amountLabel,
        inputDataModel = inputAmount,
        callBacks = callBacks
    )
}