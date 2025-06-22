package com.example.windowscompactexample.components.input.amount

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.text.selection.LocalTextSelectionColors
import androidx.compose.foundation.text.selection.TextSelectionColors
import androidx.compose.foundation.text2.BasicTextField2
import androidx.compose.foundation.text2.input.InputTransformation
import androidx.compose.foundation.text2.input.TextFieldBuffer
import androidx.compose.foundation.text2.input.TextFieldCharSequence
import androidx.compose.foundation.text2.input.TextFieldLineLimits
import androidx.compose.foundation.text2.input.rememberTextFieldState
import androidx.compose.foundation.text2.input.then
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.example.windowscompactexample.components.account.BaseAccountSelection
import com.example.windowscompactexample.components.input.models.AmountInputUIDataModel
import com.example.windowscompactexample.components.input.models.AmountUICallBackListener
import com.example.windowscompactexample.components.pro.AdibImage
import com.example.windowscompactexample.components.pro.AdibText
import com.example.windowscompactexample.components.pro.uiDatamodels.AdibTextUiDataModel
import com.example.windowscompactexample.components.pro.uiModel.AdibImageProperties
import com.example.windowscompactexample.components.pro.uiModel.AdibTextProperties
import com.example.windowscompactexample.ui.theme.ColorGradientsColorsOneStart
import com.example.windowscompactexample.ui.theme.ColorBorder
import com.example.windowscompactexample.ui.theme.Dimens.AdibSizeFontWebCaption
import com.example.windowscompactexample.ui.theme.Dimens.AdibSizeSpacingSmall
import com.example.windowscompactexample.ui.theme.Dimens.AdibSizeSpacingXsmall
import com.example.windowscompactexample.utils.AmountUtils
import com.example.windowscompactexample.utils.AmountUtils.AMOUNT_DEFAULT_PLACEHOLDER
import com.example.windowscompactexample.utils.AmountUtils.CHAR_DOT
import com.example.windowscompactexample.utils.AmountUtils.DEFAULT_MAX_AMOUNT
import com.example.windowscompactexample.utils.AmountUtils.MAX_NO_OF_DIGITS
import com.example.windowscompactexample.utils.AmountUtils.getFormatedAmountOnDone
import com.example.windowscompactexample.utils.AmountUtils.getFormattedAmount
import com.example.windowscompactexample.utils.AmountUtils.plainInput
import com.example.windowscompactexample.utils.Utils.KeyboardVisibilityObserver
import kotlinx.coroutines.selects.select

/**
 * @Author: Yerramma Kethu
 * @Date: 21/05/2025
 */
@Composable
fun BaseBigInput(
    accountTitle: AdibTextProperties,
    balanceTitle: AdibTextProperties,
    inputDataModel: AmountInputUIDataModel,   // todo need to use others input field properties
    amountIcon: AdibImageProperties?,
    modifier: Modifier = Modifier,
    countryIcon: AdibImageProperties? = null,
    selectionIcon: AdibImageProperties? = null,
    balanceError: AdibTextProperties? = null,
    amountLabel: AdibTextProperties? = null,
    amountError: AdibTextProperties? = null,
    divider: (@Composable () -> Unit)? = @Composable {
        HorizontalDivider(
            color = ColorBorder,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = AdibSizeFontWebCaption.dp)
        )
    },
    callBacks: AmountUICallBackListener? = null
) {
    Column(modifier = modifier) {
        BaseAccountSelection(
            accountDisplayName = accountTitle,
            balanceDisplayName = balanceTitle,
            accountIcon = countryIcon,
            selectionIcon = selectionIcon,
            onAccountSelection = callBacks?.onAccountSelection,
        )
        if (balanceError != null) {
            AdibText(balanceError)
        }
        if (divider != null) {
            divider()
        }
        if (amountLabel != null) {
            AdibText(amountLabel)
        }
        AmountInputWithCountryIcon(
            modifier = Modifier.padding(top = AdibSizeSpacingSmall.dp),
            amountIcon = amountIcon,
            inputDataModel = inputDataModel,
            callBacks = callBacks
        )
        if (amountError != null) {
            AdibText(amountError)
        }
    }
}

@Composable
internal fun AmountInputWithCountryIcon(
    inputDataModel: AmountInputUIDataModel,
    modifier: Modifier = Modifier,
    amountIcon: AdibImageProperties? = null,
    callBacks: AmountUICallBackListener? = null
) {
    val interactionSource = remember { MutableInteractionSource() }
    val focusRequester = remember { FocusRequester() }
    Row(
        modifier = modifier
            .focusRequester(focusRequester)
            .clickable(
                indication = null,
                interactionSource = interactionSource
            ) {
                focusRequester.requestFocus()
            },
        verticalAlignment = Alignment.CenterVertically
    ) {
        if (amountIcon != null) {
            AdibImage(amountIcon)
        }
        AdibText(
            AdibTextUiDataModel(
                text = inputDataModel.inputCurrency,
                textStyle = inputDataModel.inputTextStyle,
                modifier = Modifier.padding(end = AdibSizeSpacingXsmall.dp)
            )
        )
        AmountInputField(
            inputDataModel = inputDataModel,
            focusRequester = focusRequester,
            callBacks = callBacks
        )
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun AmountInputField(
    inputDataModel: AmountInputUIDataModel,
    modifier: Modifier = Modifier,
    focusRequester: FocusRequester,
    callBacks: AmountUICallBackListener? = null
) {
    val textFieldState = rememberTextFieldState(initialText = inputDataModel.inputAmount)
    var hasFocus by remember { mutableStateOf(false) }
    val focusManager = LocalFocusManager.current
    val cursorColor = if (hasFocus) ColorBorder else Color.Transparent
    KeyboardVisibilityObserver { visible ->
        if (!visible) {
            focusManager.clearFocus()
        }
    }
    val isInputEmpty =
        textFieldState.text.isBlank() || textFieldState.text.toString() == AMOUNT_DEFAULT_PLACEHOLDER
    LaunchedEffect(textFieldState.text) {
        if (hasFocus && isInputEmpty) {
            textFieldState.edit { select { TextRange(0) } }
        }
        callBacks?.onAmountChange?.invoke(textFieldState.text.toString().plainInput())
    }
    val focusModifier = Modifier
        .focusRequester(focusRequester)
        .onFocusChanged {
            hasFocus = it.isFocused
            callBacks?.onFocused?.invoke(hasFocus)
        }
    Box {
        if (isInputEmpty) {
            AdibText(
                AdibTextUiDataModel(
                    text = inputDataModel.inputPlaceholder,
                    textStyle = inputDataModel.inputTextStyle
                )
            )
        }
        // water bubble color on selection of amount
        val customSelectionColors = TextSelectionColors(
            handleColor = ColorGradientsColorsOneStart,
            backgroundColor = ColorGradientsColorsOneStart
        )
        CompositionLocalProvider(
            LocalTextSelectionColors provides customSelectionColors,
        ){
            // todo need keep it like this or need to configure from outside -  Need team discussion

            BasicTextField2(
                modifier = modifier
                    .fillMaxWidth()
                    .then(focusModifier),
                lineLimits = TextFieldLineLimits.SingleLine,
                state = textFieldState,
                textStyle = inputDataModel.inputTextStyle,
                keyboardOptions = KeyboardOptions.Default.copy(
                    keyboardType = KeyboardType.Number,
                    imeAction = ImeAction.Done
                ),
                cursorBrush = SolidColor(cursorColor),
                keyboardActions = KeyboardActions(
                    onDone = {
                        focusManager.clearFocus()
                        textFieldState.edit {
                            val formattedAmount =
                                getFormatedAmountOnDone(amount = textFieldState.text.toString())
                            replace(0, length, formattedAmount)
                        }
                        callBacks?.onDone?.invoke()
                    }
                ),
                inputTransformation = AmountInputTransformation(inputDataModel.availableAmount).then(inputDataModel.inputTransformation)
            )
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
data class AmountInputTransformation(val availableAmount: Double?) : InputTransformation {
    override fun transformInput(
        originalValue: TextFieldCharSequence,
        valueWithChanges: TextFieldBuffer
    ) {
        val enteredAmount = AmountUtils.getActualAmount(valueWithChanges.toString())
        val accountAmount = availableAmount ?: DEFAULT_MAX_AMOUNT
        if (enteredAmount > accountAmount) {
            valueWithChanges.replace(0, valueWithChanges.length, originalValue.toString())
            return
        }
        val maxNoDigitsBeforeDecimal =
            availableAmount?.toString()?.substringBefore(CHAR_DOT)?.length ?: MAX_NO_OF_DIGITS
        val oldCursor = valueWithChanges.selectionInChars.start
        val digitCountBeforeCursor =
            valueWithChanges.toString().take(oldCursor).count { it.isDigit() }
        val formatted = getFormattedAmount(
            noOfDigits = maxNoDigitsBeforeDecimal,
            amount = valueWithChanges.toString()
        )
        if (valueWithChanges.toString() != formatted) {
            valueWithChanges.replace(0, valueWithChanges.length, formatted)
            val newCursor = getCursorPosition(formatted, digitCountBeforeCursor)
            valueWithChanges.selectCharsIn(TextRange(newCursor))
        }
    }

    private fun getCursorPosition(formatted: String, digitIndex: Int): Int {
        var count = 0
        for ((i, c) in formatted.withIndex()) {
            if (c.isDigit()) count++
            if (count == digitIndex) return i + 1
        }
        return formatted.length
    }
}