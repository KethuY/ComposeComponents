package com.example.windowscompactexample.components

import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.windowscompactexample.ui.theme.StyleBodyMedium
import com.example.windowscompactexample.ui.theme.ColorInputsBackground
import com.example.windowscompactexample.ui.theme.ColorInteraction
import com.example.windowscompactexample.ui.theme.ColorSemanticErrorTwo
import com.example.windowscompactexample.ui.theme.Dimens.SizeFontWebCaption

@Composable
fun UiAuthPin(
    modifier: Modifier = Modifier,
    inputLength: Int = 6,
    inputShape: Shape = CircleShape,
    inputSize: Dp = SizeFontWebCaption.dp,
    description: AnnotatedString,
    filledColor: Color = ColorInteraction,
    unfilledColor: Color = ColorInputsBackground,
    errorColor: Color = ColorSemanticErrorTwo,
    actionText: String,
    otpVerificationState: OtpVerificationState,
    actionTextStyle: TextStyle = StyleBodyMedium,
    actionTextColor: Color = ColorInteraction,
    onTextChange: (String) -> Unit,
    onActionTextClicked: () -> Unit,
    onPinComplete: (String) -> Unit
) {
    BaseAuthPin(
        modifier = modifier,
        pinLength = inputLength,
        inputSize = inputSize,
        description = description,
        actionText = actionText,
        actionTextStyle = actionTextStyle,
        actionTextColor = actionTextColor,
        filledColor = filledColor,
        unfilledColor = unfilledColor,
        shape = inputShape,
        errorColor = errorColor,
        otpVerificationState = otpVerificationState,
        onActionTextClicked = onActionTextClicked,
        onTextChange = onTextChange,
        onPinComplete = onPinComplete
    )
}