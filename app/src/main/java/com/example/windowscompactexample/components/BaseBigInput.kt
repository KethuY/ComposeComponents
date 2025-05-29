package com.example.windowscompactexample.components

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.text2.BasicTextField2
import androidx.compose.foundation.text2.input.InputTransformation
import androidx.compose.foundation.text2.input.TextFieldBuffer
import androidx.compose.foundation.text2.input.TextFieldCharSequence
import androidx.compose.foundation.text2.input.TextFieldLineLimits
import androidx.compose.foundation.text2.input.maxLengthInChars
import androidx.compose.foundation.text2.input.placeCursorAtEnd
import androidx.compose.foundation.text2.input.rememberTextFieldState
import androidx.compose.foundation.text2.input.then
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.example.windowscompactexample.ui.theme.ColorBorder
import com.example.windowscompactexample.ui.theme.ColorInteraction
import com.example.windowscompactexample.ui.theme.ColorSemanticErrorTwo
import com.example.windowscompactexample.ui.theme.Style24H3Medium
import com.example.windowscompactexample.ui.theme.StyleCaptionRegular
import com.example.windowscompactexample.utils.AmountUtils
import com.example.windowscompactexample.utils.AmountUtils.properDecimalInput
import com.example.windowscompactexample.utils.AmountUtils.removeSpecialCharFromAmount
import com.example.windowscompactexample.utils.Utils.KeyboardVisibilityObserver

/**
 * @Author: Yerramma Kethu
 * @Date: 21/05/2025
 */
@Composable
fun BaseBigInput(
    modifier: Modifier = Modifier,
    backgroundColor: Color,
    shape: Shape,
    title: String,
    trailingIcon: Int,
    subTitle: String,
    inputPrefix: String,
    inputText: String,
    description: String? = null,
    leadingIcon: Int? = null,
    actualLimit: Double? = null,
    minLimit: Double? = null,
    maxLimit: Double? = null,
    topErrorMessage: String? = null,
    bottomErrorMessage: String? = null,
    isDivider: Boolean = false,
    onTextChange: (String) -> Unit
) {
    var isErrorEnabled by rememberSaveable { mutableStateOf(false) }

    Column(
        modifier = modifier
            .background(color = backgroundColor, shape = shape)
            .padding(start = 20.dp, end = 16.dp, top = 12.dp, bottom = 24.dp)
    ) {

        UiPrefilledAccount(
            title = title,
            subTitle = subTitle,
            trailingIcon = trailingIcon,
            isTrailingIconVisible = true
        )

        if (isErrorEnabled && topErrorMessage != null) {
            Text(
                text = topErrorMessage,
                style = StyleCaptionRegular,
                color = ColorSemanticErrorTwo,
                modifier = Modifier.padding(top = 8.dp)
            )
        }

        if (isDivider) {
            HorizontalDivider(
                color = ColorBorder,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 10.dp)
            )
        }
        if (description != null) {
            Text(
                text = description,
                style = StyleCaptionRegular,
                modifier = Modifier.padding(top = 16.dp, bottom = 4.dp)
            )
        }
        UiInputTextField(
            leadingIcon = leadingIcon,
            inputPrefix = inputPrefix,
            inputText = inputText,
            actualLimit = actualLimit,
            maxLimit = maxLimit,
            minLimit = minLimit,
            inputError = bottomErrorMessage,
            onTextChange = onTextChange,
            onMaxAmountReached = {
                isErrorEnabled = it
                println("Kethu  onMaxAmountReached isErrorEnabled: $isErrorEnabled")
            }
        )
    }
}

@Composable
@OptIn(ExperimentalFoundationApi::class)
internal fun UiInputTextField(
    modifier: Modifier = Modifier,
    leadingIcon: Int?,
    inputPrefix: String,
    inputText: String,
    inputTextStyle: TextStyle = Style24H3Medium,
    inputError: String? = null,
    actualLimit: Double? = null,
    minLimit: Double? = null,
    maxLimit: Double? = null,
    onTextChange: (String) -> Unit,
    onMaxAmountReached: ((Boolean) -> Unit)? = null
) {
    val focusManager = LocalFocusManager.current
    val rawValue = remember { mutableStateOf(inputText) }
    val textFieldState = rememberTextFieldState(initialText = inputText)
    val interactionSource = remember { MutableInteractionSource() }
    val focusRequester = remember { FocusRequester() }
    val maxLengthInChars = AmountUtils.getInputLength(actualLimit)
    var isErrorEnabled by rememberSaveable { mutableStateOf(false) }
    val inputTransformation = AmountInputTransformation(
        actualLimit = AmountUtils.getMaxLimit(actualLimit),
        maxLimit = maxLimit,
        minLimit = minLimit ?: 1.00,
        onMaxAmountReached = onMaxAmountReached,
        onLimitCrossedError = {
            isErrorEnabled = it
        })


    KeyboardVisibilityObserver { visible ->
        println("🧭 Kethu Keyboard is ${if (visible) "OPEN" else "CLOSED"}")
        if (!visible) {
            focusManager.clearFocus()
        }
    }

    // Format and filter input
    LaunchedEffect(textFieldState.text) {
        val input = textFieldState.text.toString()
            .replace(",", "")

        // Allow only digits and one dot
        val cleaned = input.filterIndexed { index, c ->
            c.isDigit() || (c == '.' && input.indexOf('.') == index)
        }


        println("✅ Kethu LaunchedEffect: cleaned $cleaned")

        onTextChange(cleaned)
    }

    Column(modifier = modifier) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            if (leadingIcon != null) {
                Image(
                    imageVector = ImageVector.vectorResource(leadingIcon),
                    contentDescription = null
                )
            }
            Text(
                text = inputPrefix,
                modifier = Modifier.padding(start = 8.dp),
                style = inputTextStyle,
                fontWeight = FontWeight.SemiBold
            )

            Row (
                modifier = Modifier
                    .fillMaxWidth()
                    .focusRequester(focusRequester)
                    .clickable(
                        indication = null,
                        interactionSource = interactionSource
                    ) {
                        println("🟢 Kethu Field tapped - requesting focus")
                        focusRequester.requestFocus()
                    }
                    .padding(start = 4.dp)

            ) {
                if (textFieldState.text.isEmpty()) {
                    Text(
                        text = "0.00",
                        style = inputTextStyle,
                    )
                }
                BasicTextField2(
                    lineLimits = TextFieldLineLimits.SingleLine,
                    state = textFieldState,
                    textStyle = inputTextStyle,
                    keyboardOptions = KeyboardOptions.Default.copy(
                        keyboardType = KeyboardType.Decimal,
                        imeAction = ImeAction.Done
                    ),
                    cursorBrush = SolidColor(ColorInteraction.copy(alpha = 0.2f)),
                    keyboardActions = KeyboardActions(
                        onDone = {
                            textFieldState.edit {
                                val formattedAmount =
                                    AmountUtils.formatDecimalPoint(textFieldState.text.toString())
                                println("✅ Kethu IME action Done pressed  formattedAmount: $formattedAmount")
                                replace(0, length, formattedAmount)
                            }
                            focusManager.clearFocus()
                        }
                    ),
                    inputTransformation = InputTransformation.maxLengthInChars(maxLengthInChars)
                        .then(inputTransformation),
                )
            }
        }

        if (inputError != null && isErrorEnabled) {
            Text(
                text = inputError,
                style = StyleCaptionRegular,
                color = ColorSemanticErrorTwo,
                modifier = Modifier.padding(top = 8.dp)
            )
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
data class AmountInputTransformation(
    val actualLimit: Double,
    val minLimit: Double? = null,
    val maxLimit: Double? = null,
    val onMaxAmountReached: ((Boolean) -> Unit)? = null,
    val onLimitCrossedError: ((Boolean) -> Unit)? = null
) :
    InputTransformation {
    private var isTopErrorEnabled by mutableStateOf(false)
    private var isBottomErrorEnabled by mutableStateOf(false)

    override fun transformInput(
        originalValue: TextFieldCharSequence,
        valueWithChanges: TextFieldBuffer
    ) {

        println("Kethu transformInput isErrorEnabled: $isTopErrorEnabled")

        val amount = valueWithChanges.toString().properDecimalInput()

        if (isLimitsAvailable()) {
            val isAmountWithInLimits = when {
                minLimit != null && maxLimit != null -> amount in minLimit..maxLimit
                minLimit != null -> amount >= minLimit
                maxLimit != null -> amount <= maxLimit
                else -> true // No limits set, always valid
            }

            onLimitCrossedError?.invoke(!isAmountWithInLimits)
            if (!isAmountWithInLimits) {
                if (!isBottomErrorEnabled) {
                    isBottomErrorEnabled = true
                    valueWithChanges.replace(
                        0,
                        valueWithChanges.length,
                        valueWithChanges.toString()
                    )
                }
                return
            }
        }

        isBottomErrorEnabled = false
        val isMaxAmountReached = amount > actualLimit
        onMaxAmountReached?.invoke(isMaxAmountReached)
        if (isMaxAmountReached) {
            if (!isTopErrorEnabled) {
                isTopErrorEnabled = true
                valueWithChanges.replace(0, valueWithChanges.length, valueWithChanges.toString())
            }
            return
        }
        isTopErrorEnabled = false
        val newValue = valueWithChanges.toString()
        val formatted = formatMoney(newValue) //AmountUtils.getFormattedAmount(valueWithChanges.toString())
        println("Kethu AmountInputTransformation newValue: $newValue formatted: $formatted")

        if (formatted != newValue) {
            valueWithChanges.replace(0, valueWithChanges.length, formatted)
        }
    }

    private fun isLimitsAvailable() = minLimit != null || maxLimit != null

    private fun formatMoney(newValue:String): String {
        val padded = newValue.padStart(3, '0') // Ensure at least 3 digits for cents
        val dollars = padded.dropLast(2)
        val cents = padded.takeLast(2)
        return "${dollars.toInt()}.${cents}"
    }

}