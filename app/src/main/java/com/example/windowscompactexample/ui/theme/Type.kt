package com.example.windowscompactexample.ui.theme

import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.windowscompactexample.R

val Style16BodyRegular = TextStyle(
    fontFamily = FontFamily(Font(R.font.roboto_regular)),
    fontWeight = FontWeight.Normal,
    fontSize = Dimens.SizeFontMobileBody.sp,
    color = ColorTextBase
)

val StyleCaptionRegular = TextStyle(
    fontFamily = FontFamily(Font(R.font.roboto_regular)),
    fontWeight = FontWeight.Normal,
    fontSize = Dimens.SizeFontMobileCaption.sp,
    color = ColorTextBase
)

val StyleBodyMedium = TextStyle(
    fontFamily = FontFamily(Font(R.font.roboto_medium)),
    fontWeight = FontWeight.Medium,
    fontSize = Dimens.SizeFontMobileBody.sp,
    color = ColorTextBase
)

val Style24H3Medium = TextStyle(
    fontFamily = FontFamily(Font(R.font.roboto_medium)),
    fontWeight = FontWeight.Medium,
    fontSize = 24.sp,
    color = ColorTextBase,
)