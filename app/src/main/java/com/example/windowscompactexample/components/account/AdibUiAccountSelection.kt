package com.example.windowscompactexample.components.account

import androidx.annotation.DrawableRes
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.windowscompactexample.components.pro.uiDatamodels.AdibImageUiDataModel
import com.example.windowscompactexample.components.pro.uiDatamodels.AdibTextUiDataModel
import com.example.windowscompactexample.components.pro.uiModel.AdibImageProperties
import com.example.windowscompactexample.components.pro.uiModel.AdibTextProperties
import com.example.windowscompactexample.ui.theme.Adib14CaptionRegular
import com.example.windowscompactexample.ui.theme.AdibColorInputsBackground
import com.example.windowscompactexample.ui.theme.Dimens.AdibSizeFontWebHfour
import com.example.windowscompactexample.ui.theme.Dimens.AdibSizeSpacingLarge
import com.example.windowscompactexample.ui.theme.Dimens.AdibSizeSpacingMedium
import com.example.windowscompactexample.ui.theme.Dimens.AdibSizeSpacingSmall
import com.example.windowscompactexample.ui.theme.Dimens.AdibSizeSpacingXsmall
import com.example.windowscompactexample.ui.theme.StyleBodyMedium

/**
 * @Author: Yerramma Kethu
 * @Date: 24/05/2025
 */
@Composable
fun UiAccountSelection(
    accountTitle: AdibTextProperties,
    balanceTitle: AdibTextProperties?,
    modifier: Modifier = Modifier,
    backgroundColor: Color = AdibColorInputsBackground,
    shape: Shape = RoundedCornerShape(AdibSizeFontWebHfour.dp),
    paddingValues: PaddingValues = PaddingValues(
        horizontal = AdibSizeFontWebHfour.dp,
        vertical = AdibSizeSpacingMedium.dp
    ),
    accountIcon: AdibImageProperties? = null,
    selectionIcon: AdibImageProperties? = null,
    isSelectionIconVisible: Boolean = false,
    onClick: (() -> Unit)? = null
) {
    BaseAccountSelection(
        modifier = modifier
            .background(backgroundColor, shape)
            .padding(paddingValues),
        accountDisplayName = accountTitle,
        balanceDisplayName = balanceTitle,
        accountIcon = accountIcon,
        selectionIcon = selectionIcon,
        isSelectionIconVisible = isSelectionIconVisible,
        onAccountClick = onClick
    )
}

object AdibAccountSelectionDefaults {
    private const val DEFAULT_ACCOUNT_ICON_CONTENT_DESC = "Account Icon"
    private const val DEFAULT_SELECTION_ICON_CONTENT_DESC = "Selection Icon"

    fun accountTitleDefaults(
        accountTitle: String,
        textStyle: TextStyle = StyleBodyMedium.copy(fontWeight = FontWeight.SemiBold)
    ) = textDefaults( text = accountTitle, textStyle = textStyle)

    fun balanceTitleDefaults(
        balanceTitle: String,
        textStyle: TextStyle = Adib14CaptionRegular
    ) = textDefaults(
        text = balanceTitle,
        textStyle = textStyle,
        modifier = Modifier.padding(top = AdibSizeSpacingXsmall.dp)
    )

    fun accountIconDefaults(
        @DrawableRes accountIcon: Int,
        tintColor: Color = Color.Unspecified,
        contentDesc: String = DEFAULT_ACCOUNT_ICON_CONTENT_DESC
    ) = iconDefaults(
        icon = accountIcon,
        modifier = Modifier
            .padding(end = AdibSizeSpacingSmall.dp)
            .size(AdibSizeSpacingLarge.dp),
        contentDesc = contentDesc,
        tintColor = tintColor
    )

    fun selectionIconDefaults(
        @DrawableRes selectionIcon: Int,
        tintColor: Color = Color.Unspecified,
        contentDesc: String = DEFAULT_SELECTION_ICON_CONTENT_DESC
    ) = iconDefaults(
        icon = selectionIcon,
        modifier = Modifier
            .padding(start = AdibSizeSpacingSmall.dp)
            .size(AdibSizeSpacingLarge.dp),
        contentDesc = contentDesc,
        tintColor = tintColor
    )

    private fun iconDefaults(
        @DrawableRes icon: Int,
        modifier: Modifier,
        tintColor: Color,
        contentDesc: String
    ) = AdibImageUiDataModel(
        drawable = icon,
        modifier = modifier,
        description = contentDesc,
        colorFilter = iconColorFilter(tintColor)
    )

    private fun iconColorFilter(tintColor: Color) = if (tintColor != Color.Unspecified) {
        ColorFilter.tint(tintColor)
    } else null

    private fun textDefaults(
        text: String,
        modifier: Modifier = Modifier,
        textStyle: TextStyle
    ) = AdibTextUiDataModel(modifier = modifier, text = text, textStyle = textStyle)
}