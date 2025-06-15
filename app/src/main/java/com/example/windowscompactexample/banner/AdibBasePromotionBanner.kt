package com.example.windowscompactexample.banner

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.example.windowscompactexample.ui.theme.Adib14CaptionRegular
import com.example.windowscompactexample.ui.theme.Adib20H4Medium
import com.example.windowscompactexample.ui.theme.Dimens.AdibSizeFontWebCaption

/**
 * Created by: Navas
 * Email: navas.kolathikkal@adib.com
 * Date: 12/05/2025
 * Copyright: ADIB (2025-2026)
 *
 * </p>
 *
 * @param title Heading of the banner
 * @param description : Call action text
 * @param bannerIcon : Banner icon in drawable
 * @param cardIconDesc : Content description of image
 * @param bannerColor : Color for banner which hold both title and image
 * @param bannerTextLines : Number of max lines supported for banner title
 * @param callActionLines : Number of max lines supported for call action
 * @param titleModifier : Modifier for title of the banner
 * @param imageModifier : Modifier for image of the banner
 * @param clickAction : Click action
 */
@Composable
internal fun AdibBasePromotionBanner(
    title: String,
    description: String,
    @DrawableRes bannerIcon: Int,
    cardIconDesc: String?,
    bannerColor: Color,
    bannerTextLines: Int,
    callActionLines: Int,
    modifier: Modifier,
    titleModifier: Modifier,
    imageModifier: Modifier,
    clickAction: (() -> Unit)? = null
) {
    Box(modifier = modifier) {
        Column {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(
                        color = bannerColor,
                        shape = RoundedCornerShape(AdibSizeFontWebCaption.dp)
                    )
            ) {
                Text(
                    text = title,
                    textAlign = TextAlign.Start,
                    modifier = titleModifier
                        .weight(1f)
                        .align(Alignment.CenterVertically),
                    style = Adib20H4Medium,
                    maxLines = bannerTextLines,
                    overflow = TextOverflow.Ellipsis,
                    onTextLayout = { textResult->
                        if (textResult.hasVisualOverflow){

                        }

                    }
                )

                Image(
                    painter = painterResource(id = bannerIcon),
                    contentDescription = cardIconDesc,
                    contentScale = ContentScale.Inside,
                    modifier = imageModifier.align(Alignment.CenterVertically),
                )
            }

            if (description.isNotBlank()) {
                Text(
                    style = Adib14CaptionRegular,
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(
                            RoundedCornerShape(
                                bottomStart = AdibSizeFontWebCaption.dp,
                                bottomEnd = AdibSizeFontWebCaption.dp
                            )
                        )
                        .clickable {
                            clickAction?.invoke()
                        }
                        .padding(AdibSizeFontWebCaption.dp),
                    text = description,
                    textAlign = TextAlign.Center,
                    maxLines = callActionLines,
                    overflow = TextOverflow.Ellipsis
                )
            }
        }
    }
}
