package com.example.windowscompactexample

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.windowscompactexample.components.Details
import com.example.windowscompactexample.components.OtpVerificationState
import com.example.windowscompactexample.components.UiAuthPin
import com.example.windowscompactexample.testing.ShowTabs
import com.example.windowscompactexample.ui.theme.ColorSemanticErrorTwo
import com.example.windowscompactexample.ui.theme.ColorTextSubdued
import com.example.windowscompactexample.ui.theme.Dimens.SizeFontTwo
import com.example.windowscompactexample.ui.theme.StyleCaptionRegular

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {

                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Column {
                        ShowTabs()
                        Spacer(modifier = Modifier.height(32.dp))
                        Details()
                        Spacer(modifier = Modifier.height(32.dp))
                        ShowAuthPin()
                    }

                }
            }
        }
}

@Composable
private fun ShowAuthPin() {
    var otp: String by remember { mutableStateOf("") }
    var verificationState by remember { mutableStateOf(OtpVerificationState.DEFAULT) }
    val descBoldTextStyle = SpanStyle(
        color = ColorTextSubdued,
        fontSize = SizeFontTwo.sp,
        fontWeight = FontWeight.SemiBold
    )

    val normalText = SpanStyle(
        color = ColorTextSubdued,
        fontSize = StyleCaptionRegular.fontSize,
        fontWeight = StyleCaptionRegular.fontWeight
    )
    val defaultAnnString = buildAnnotatedString {
        withStyle(style = normalText) {
            append("Enter the One Time Password (OTP) sent on your mobile number ending with ")
        }
        withStyle(style = descBoldTextStyle) {
            append("1234.")
        }
    }

    val errorAnnString = buildAnnotatedString {
        withStyle(style = normalText.copy(ColorSemanticErrorTwo)) {
            append("Something went wrong. Please try again or request a new OTP.")
        }
    }

    var description by remember { mutableStateOf(defaultAnnString) }

    Spacer(modifier = Modifier.height(32.dp))
    UiAuthPin(
        modifier = Modifier.padding(20.dp),
        description = description,
        otpVerificationState = verificationState,
        actionText = "Resend OTP",
        onTextChange = {
            if (verificationState == OtpVerificationState.ERROR) {
                description = defaultAnnString
                verificationState = OtpVerificationState.DEFAULT
            } else {
                verificationState = OtpVerificationState.TYPING
            }
        },
        onActionTextClicked = {
            verificationState = OtpVerificationState.ERROR
            description = errorAnnString
        },
        onPinComplete = {
            otp = it
        },
    )
}