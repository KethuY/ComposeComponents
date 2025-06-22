package com.example.windowscompactexample.components.input.amount

import androidx.annotation.DrawableRes
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.windowscompactexample.components.account.AdibAccountSelectionDefaults.DEFAULT_ACCOUNT_ICON_CONTENT_DESC
import com.example.windowscompactexample.components.account.AdibAccountSelectionDefaults.accountTitleDefaults
import com.example.windowscompactexample.components.account.AdibAccountSelectionDefaults.selectionIconDefaults
import com.example.windowscompactexample.components.input.models.AmountInputUIDataModel
import com.example.windowscompactexample.components.pro.uiDatamodels.AdibImageUiDataModel
import com.example.windowscompactexample.components.pro.uiDatamodels.AdibTextUiDataModel
import com.example.windowscompactexample.ui.theme.Adib14CaptionRegular
import com.example.windowscompactexample.ui.theme.Adib16BodyMedium
import com.example.windowscompactexample.ui.theme.ColorSemanticErrorTwo
import com.example.windowscompactexample.ui.theme.Dimens.AdibSizeSpacingEighteen
import com.example.windowscompactexample.ui.theme.Dimens.AdibSizeSpacingMedium
import com.example.windowscompactexample.ui.theme.Dimens.AdibSizeSpacingSmall

object UiAmountUIDefaults {
    /*Account section defaults*/
    fun smallInputAccountTitle(title: String) =
        accountTitleDefaults(title, textStyle = Adib14CaptionRegular)

    fun bigInputAccountTitle(title: String) =
        accountTitleDefaults(title)

    fun balanceTitle(title: String) =
        accountTitleDefaults(title)

    fun selectionIcon(
        @DrawableRes accountIcon: Int,
        tintColor: Color = Color.Unspecified,
        contentDesc: String = DEFAULT_ACCOUNT_ICON_CONTENT_DESC
    ) = selectionIconDefaults(accountIcon, tintColor, contentDesc)

    /*Amount section defaults*/

    fun balanceError(title: String) = AdibTextUiDataModel(
        modifier = Modifier.padding(top = AdibSizeSpacingSmall.dp),
        text = title,
        textStyle = Adib14CaptionRegular.copy(ColorSemanticErrorTwo)
    )

    fun amountLabel(title: String) = AdibTextUiDataModel(
        text = title,
        textStyle = Adib14CaptionRegular,
        modifier = Modifier.padding(top = AdibSizeSpacingMedium.dp)
    )

    fun amountIcon(
        @DrawableRes accountIcon: Int,
        tintColor: Color = Color.Unspecified,
        contentDesc: String = DEFAULT_ACCOUNT_ICON_CONTENT_DESC
    ) = AdibImageUiDataModel(
        drawable = accountIcon,
        modifier = Modifier
            .padding(end = AdibSizeSpacingSmall.dp)
            .size(AdibSizeSpacingEighteen.dp),
        description = contentDesc,
        colorFilter = iconColorFilter(tintColor)
    )

    @Suppress("OPT_IN_USAGE_FUTURE_ERROR")
    fun amountInputProperties(
        input: String,
        currencySymbol: String,
        availableAmount: Double?
    ) = AmountInputUIDataModel(
        inputCurrency = currencySymbol,
        inputAmount = input,
        availableAmount = availableAmount
    )

    @Suppress("OPT_IN_USAGE_FUTURE_ERROR")
    fun smallAmountInputProperties(
        input: String,
        currencySymbol: String,
    ) = AmountInputUIDataModel(
        inputCurrency = currencySymbol,
        inputAmount = input,
        inputTextStyle = Adib16BodyMedium.copy(fontWeight = FontWeight.SemiBold),
        availableAmount = null
    )

    fun amountError(title: String) = AdibTextUiDataModel(
        modifier = Modifier.padding(top = AdibSizeSpacingSmall.dp),
        text = title,
        textStyle = Adib14CaptionRegular.copy(color = ColorSemanticErrorTwo)
    )

    private fun iconColorFilter(tintColor: Color) = if (tintColor != Color.Unspecified) {
        ColorFilter.tint(tintColor)
    } else null
}