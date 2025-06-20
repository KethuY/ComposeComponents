package com.example.windowscompactexample.components.banner

import android.annotation.SuppressLint
import androidx.annotation.DrawableRes
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.windowscompactexample.ui.theme.AdibColorSegmentSurface
import com.example.windowscompactexample.ui.theme.AdibColorSurfaceBlueOne
import com.example.windowscompactexample.ui.theme.Dimens.AdibSizeFontMobileHfour
import com.example.windowscompactexample.ui.theme.Dimens.AdibSizeFontWebCaption

/**
 * Created by: Navas
 * Email: navas.kolathikkal@adib.com
 * Date: 12/05/2025
 * Copyright: ADIB (2025-2026)
 * A composable function that displays a promotion banner with a title, description, and an optional icon.
 * The banner supports customization of colors, text lines, and click actions.
 *
 * @param title The heading text of the banner.
 * @param description The call-to-action or additional text for the banner.
 * @param bannerIcon The resource ID of the banner icon.
 * @param boxColor The background color of the outer box. Defaults to `AdibColorSurfaceBlueOne`.
 * @param bannerColor The background color of the banner. Defaults to `AdibColorSegmentSurface`.
 * @param bannerTextLines The maximum number of lines for the banner title. Defaults to `CONST_THREE`.
 * @param callActionLines The maximum number of lines for the call-to-action text. Defaults to `CONST_ONE`.
 * @param cardIconDesc The content description for the banner icon. Defaults to `null`.
 * @param modifier The modifier for the outer box, allowing customization of its appearance and behavior.
 * @param titleModifier The modifier for the title text, allowing customization of its padding and layout.
 * @param imageModifier The modifier for the banner icon, allowing customization of its padding and layout.
 * @param clickAction An optional lambda function to handle click events on the banner.
 */

@Composable
fun AdibUiPromotionBanner(
    title: String,
    description: String,
    @DrawableRes bannerIcon: Int,
    boxColor: Color = AdibColorSurfaceBlueOne,
    bannerColor: Color = AdibColorSegmentSurface,
    bannerTextLines: Int = 3,
    callActionLines: Int = 1,
    cardIconDesc: String? = null,
    @SuppressLint("ModifierParameter") modifier: Modifier = Modifier
        .background(color = boxColor, shape = RoundedCornerShape(AdibSizeFontWebCaption.dp))
        .clip(RoundedCornerShape(AdibSizeFontWebCaption.dp))
        .wrapContentSize(),
    titleModifier: Modifier = Modifier.padding(
        AdibSizeFontMobileHfour.dp
    ),
    imageModifier: Modifier = Modifier
        .padding(end = AdibSizeFontWebCaption.dp)
        .wrapContentSize(),
    clickAction: (() -> Unit)? = null
) {
    BasePromotionBanner(
        title = title,
        description = description,
        bannerIcon = bannerIcon,
        bannerColor = bannerColor,
        bannerTextLines = bannerTextLines,
        callActionLines = callActionLines,
        clickAction = clickAction,
        titleModifier = titleModifier,
        imageModifier = imageModifier,
        modifier = modifier,
        cardIconDesc = cardIconDesc
    )
}
