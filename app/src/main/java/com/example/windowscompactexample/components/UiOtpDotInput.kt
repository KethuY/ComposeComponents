package com.example.windowscompactexample.components

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.text.selection.LocalTextSelectionColors
import androidx.compose.foundation.text.selection.TextSelectionColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalTextToolbar
import androidx.compose.ui.platform.TextToolbar
import androidx.compose.ui.platform.TextToolbarStatus
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.Dp

private const val EMPTY_CHAR = ' '

@Composable
fun UiOtpDotInput(
    modifier: Modifier = Modifier,
    otpLength: Int,
    inputSize: Dp,
    filledColor: Color,
    unfilledColor: Color,
    errorColor: Color,
    shape: Shape = CircleShape,
    otpVerificationState: OtpVerificationState,
    onTextChange: (String) -> Unit,
    onOtpEntered: (String) -> Unit
) {
    val otp = remember { mutableStateListOf<Char>().apply { repeat(otpLength) { add(EMPTY_CHAR) } } }
    val focusRequester = remember { FocusRequester() }
    val otpString = otp.joinToString("").replace(" ", "")

    // to hide the cursor
    val customSelectionColors = TextSelectionColors(
        handleColor = Color.Transparent,
        backgroundColor = Color.Transparent
    )
    // to hide the clipboard menu
    val disabledToolbar = object : TextToolbar {
        override val status: TextToolbarStatus
            get() = TextToolbarStatus.Hidden

        override fun hide() {
            // No-op
        }

        override fun showMenu(
            rect: Rect,
            onCopyRequested: (() -> Unit)?,
            onPasteRequested: (() -> Unit)?,
            onCutRequested: (() -> Unit)?,
            onSelectAllRequested: (() -> Unit)?
        ) {
            // No-op
        }
    }

    Box(modifier = modifier.disableLongPress()) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()

                .clickable(interactionSource = remember {  MutableInteractionSource()}, indication = null) {
                focusRequester.requestFocus()
            }
        ) {
            for (i in 0 until otpLength) {
                val isFilled = otp.getOrNull(i)?.isDigit() == true
                val dotColor = when (otpVerificationState) {
                    OtpVerificationState.DEFAULT -> {
                        otp.clear()
                        unfilledColor
                    }
                    OtpVerificationState.TYPING -> if (isFilled) filledColor else unfilledColor
                    OtpVerificationState.ERROR -> errorColor
                }

                Box(modifier = Modifier
                    .size(inputSize)
                    .background(color = dotColor, shape = shape)
                )
            }
        }

        // Hidden input field
        CompositionLocalProvider(
            LocalTextSelectionColors provides customSelectionColors,
            LocalTextToolbar provides disabledToolbar
        ) {
            BasicTextField(
                modifier = Modifier
                    .disableLongPress()
                    .matchParentSize()
                    .focusRequester(focusRequester)
                    .alpha(0f) ,// invisible but active,,
                value = otpString,
                onValueChange = { value ->
                    onTextChange.invoke(value)
                    val trimmed = value.filter { it.isDigit() }.take(otpLength)
                    otp.clear()
                    otp.addAll(trimmed.padEnd(otpLength, EMPTY_CHAR).toList())
                    if (trimmed.length == otpLength) {
                        onOtpEntered(trimmed)
                    }
                },
                keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.NumberPassword, imeAction = ImeAction.Done),
                visualTransformation = VisualTransformation.None
            )
        }
    }
}

@SuppressLint("ReturnFromAwaitPointerEventScope")
fun Modifier.disableLongPress(): Modifier = this.pointerInput(Unit) {
    awaitPointerEventScope {
        // Consume long press by waiting and not reacting
        while (true) {
            val event = awaitPointerEvent()
            event.changes.forEach { it.consume() }
        }
    }
}