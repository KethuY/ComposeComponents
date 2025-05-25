package com.example.windowscompactexample.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.windowscompactexample.ui.theme.Dimens.SizeSpacingMedium
import com.example.windowscompactexample.ui.theme.Dimens.SizeSpacingXlarge

@Composable
fun BaseAuthPin(
    modifier: Modifier = Modifier,
    pinLength: Int,
    inputSize: Dp,
    shape: Shape,
    description: AnnotatedString,
    filledColor: Color,
    unfilledColor: Color,
    errorColor: Color,
    actionText: String,
    actionTextStyle: TextStyle,
    actionTextColor: Color,
    otpVerificationState: OtpVerificationState,
    onActionTextClicked: () -> Unit,
    onTextChange: (String) -> Unit,
    onPinComplete: (String) -> Unit
) {
    Column(modifier = modifier) {
      UiOtpDotInput(
            inputSize = inputSize,
            otpLength = pinLength,
            filledColor = filledColor,
            unfilledColor = unfilledColor,
            errorColor = errorColor,
            shape = shape,
            otpVerificationState = otpVerificationState,
            onTextChange = onTextChange,
            onOtpEntered = onPinComplete
        )
        Text(
            modifier = Modifier.padding(top = SizeSpacingXlarge.dp),
            text = description,
        )
        Text(
            modifier = Modifier.padding( top = SizeSpacingXlarge.dp)
                .clickable(
                    interactionSource = remember { MutableInteractionSource() },
                    indication = null,
                    onClick = onActionTextClicked
                ),
            text = actionText,
            style = actionTextStyle,
            color = actionTextColor
        )
    }
}