package com.example.windowscompactexample.components

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.windowscompactexample.ui.theme.Adib16BodyMedium
import com.example.windowscompactexample.ui.theme.AdibColorButtonPrimaryBackground
import com.example.windowscompactexample.ui.theme.AdibColorButtonPrimaryDisabled
import com.example.windowscompactexample.ui.theme.AdibColorButtonPrimaryTapped
import com.example.windowscompactexample.ui.theme.AdibColorButtonPrimaryText
import com.example.windowscompactexample.ui.theme.Dimens

@Composable
internal fun AdibBaseButton(
    text: String,
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    state: AdibButtonState = AdibButtonState.DEFAULT,
    textColor: Color = AdibColorButtonPrimaryText,
    backgroundColor: Color = AdibColorButtonPrimaryBackground,
    tappedBackgroundColor: Color = AdibColorButtonPrimaryTapped,
    disabledBackgroundColor: Color = AdibColorButtonPrimaryDisabled,
    height: Dp = Dimens.AdibSizeButtonHeightPrimary.dp,
    horizontalPadding: Dp = Dimens.AdibSizeSpacingMedium.dp,
    textStyle: TextStyle = Adib16BodyMedium,
    leadingIcon: Int? = null,
    shape: Shape = RoundedCornerShape(Dimens.AdibSizeSpacingMedium.dp)
) {
    val interactionSource = remember { MutableInteractionSource() }
    val isPressed by interactionSource.collectIsPressedAsState()

    // Handle state-based color transitions
    val animatedColor by animateColorAsState(
        targetValue = when {
            state == AdibButtonState.DISABLED -> disabledBackgroundColor
            isPressed -> tappedBackgroundColor
            else -> backgroundColor
        }, label = ""
    )
    Button(
        onClick = onClick,
        modifier = modifier
            .fillMaxWidth()
            .height(height)
            .padding(horizontal = horizontalPadding),
        enabled = state != AdibButtonState.DISABLED && state != AdibButtonState.LOADING,
        shape = shape,
        colors = ButtonDefaults.buttonColors(
            containerColor = animatedColor,
            disabledContainerColor = disabledBackgroundColor,
            contentColor = textColor
        ),
        interactionSource = interactionSource
    ) {

        UiActionTextWithLeadingIcon(
            modifier = Modifier.fillMaxWidth(),
            leadingIcon = leadingIcon,
            title = text,
            titleStyle = textStyle,
            horizontalAlignment = Arrangement.Center,
        )
    }
}
