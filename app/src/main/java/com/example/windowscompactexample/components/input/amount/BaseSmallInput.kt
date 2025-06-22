package com.example.windowscompactexample.components.input.amount

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.windowscompactexample.components.account.BaseAccountSelection
import com.example.windowscompactexample.components.input.models.AmountInputUIDataModel
import com.example.windowscompactexample.components.input.models.AmountUICallBackListener
import com.example.windowscompactexample.components.pro.AdibText
import com.example.windowscompactexample.components.pro.uiModel.AdibImageProperties
import com.example.windowscompactexample.components.pro.uiModel.AdibTextProperties
import com.example.windowscompactexample.ui.theme.Dimens.AdibSizeSpacingXsmall

/**
 * @Author: Yerramma Kethu
 * @Date: 21/05/2025
 */
@Composable
fun BaseSmallInput(
    modifier: Modifier = Modifier,
    accountTitle: AdibTextProperties,
    inputDataModel: AmountInputUIDataModel, // todo need to use others input field properties
    amountIcon: AdibImageProperties,
    amountError: AdibTextProperties? = null,
    callBacks: AmountUICallBackListener
) {
    Column(modifier = modifier) {
        BaseAccountSelection(
            accountDisplayName = accountTitle,
            balanceDisplayName = null
        )
        AmountInputWithCountryIcon(
            modifier = Modifier.padding(top = AdibSizeSpacingXsmall.dp),
            inputDataModel = inputDataModel,
            amountIcon = amountIcon,
            callBacks = callBacks
        )
        if (amountError != null) {
            AdibText(amountError)
        }
    }
}