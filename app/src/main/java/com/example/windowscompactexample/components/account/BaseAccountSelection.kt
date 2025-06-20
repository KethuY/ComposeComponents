package com.example.windowscompactexample.components.account

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.windowscompactexample.components.pro.AdibImage
import com.example.windowscompactexample.components.pro.AdibText
import com.example.windowscompactexample.components.pro.uiDatamodels.AdibImageUiDataModel
import com.example.windowscompactexample.components.pro.uiModel.AdibImageProperties
import com.example.windowscompactexample.components.pro.uiModel.AdibTextProperties

/**
 * @Author: Yerramma Kethu
 * @Date: 22/05/2025
 */
@Composable
internal fun BaseAccountSelection(
    modifier: Modifier = Modifier,
    accountDisplayName: AdibTextProperties,
    balanceDisplayName: AdibTextProperties?,
    accountIcon: AdibImageProperties? = null,
    selectionIcon: AdibImageProperties? = null,
    isSelectionIconVisible: Boolean = false,
    onAccountClick: (() -> Unit)? = null
) {
   val  interactionSource = remember { MutableInteractionSource() }

    Row(modifier = modifier.clickable(
        interactionSource = interactionSource,
        indication = null
    ) {
        if (isSelectionIconVisible) {
            println("Kethu ${accountDisplayName.text} is selected")
            onAccountClick?.invoke()
        }
    }) {
        if (accountIcon != null && accountIcon is AdibImageUiDataModel) {
            val updatedIconData =
                accountIcon.copy(modifier = accountIcon.modifier.align(Alignment.CenterVertically))
            AdibImage(updatedIconData)
        }
        AccountTitleWithBalance(
            modifier = Modifier.weight(1f),
            accountDisplayName,
            balanceDisplayName
        )
        if (selectionIcon != null && isSelectionIconVisible) {
            AdibImage(selectionIcon)
        }
    }
}

@Composable
internal fun AccountTitleWithBalance(
    modifier: Modifier = Modifier,
    title: AdibTextProperties,
    subTitle: AdibTextProperties?
) {
    Column(modifier = modifier) {
        AdibText(title)
        if (subTitle != null) {
            AdibText(subTitle)
        }
    }
}
