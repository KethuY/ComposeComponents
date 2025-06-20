package com.example.windowscompactexample.components.accountdetailscard

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.windowscompactexample.ui.theme.ColorBannersInfoBg

@Composable
fun Details(
    accountType: String = "Savings Account",
    accountNumber: String = "19023456",
    isActive: Boolean = true,
    balance: String = "AED 200,445.24",
    modifier: Modifier = Modifier
) {
    var resizedTextStyle by remember {
        mutableStateOf(
            TextStyle(
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF0A1B3E)
            )
        )
    }

    val maxFontSize= 28.sp
    val maxFontSize1= 16.sp

    var leadingTextFontSize by remember { mutableStateOf(maxFontSize) }
    var trailingTextFontSize by remember { mutableStateOf(maxFontSize1) }


    Box(
        modifier = modifier
            .fillMaxWidth()
            .background(color = ColorBannersInfoBg, shape = RoundedCornerShape(24.dp))

    ) {
        Column {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    // Account Icon
                    Image(
                        imageVector = Icons.Default.AccountCircle, // Replace with your logo
                        contentDescription = null,
                        modifier = Modifier
                            .align(Alignment.Top)
                            .size(24.dp)
                    )

                    Spacer(modifier = Modifier.width(12.dp))

                    Column(modifier=Modifier.weight(1f)) {
                        Text(
                            text = accountType,
                            style = MaterialTheme.typography.titleMedium.copy(
                                fontWeight = FontWeight.Bold,
                                color = Color(0xFF0A1B3E)
                            )
                        )
                        Text(
                            text = "Acc. No: $accountNumber",
                            style = MaterialTheme.typography.bodySmall.copy(
                                color = Color.Gray
                            )
                        )
                    }
                }

                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(
                        text = if (isActive) "Active" else "Inactive",
                        style = MaterialTheme.typography.bodyMedium.copy(
                            color = Color(0xFF0A1B3E)
                        )
                    )
                    Spacer(modifier = Modifier.width(6.dp))
                    Box(
                        modifier = Modifier
                            .size(12.dp)
                            .background(
                                color = if (isActive) Color(0xFF4CAF50) else Color.Red,
                                shape = CircleShape
                            )
                    )
                }
            }

            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            ) {
                // Balance
                Text(
                    modifier = Modifier
                        .weight(1f)
                        .padding(end = 12.dp)

                    ,
                    style = resizedTextStyle,
                   text =  buildAnnotatedString {
                        val parts = balance.split(".")
                        withStyle(SpanStyle(fontSize = leadingTextFontSize, fontWeight = FontWeight.Bold)) {
                            append(parts[0])
                        }
                        append(".")
                        withStyle(SpanStyle(fontSize = trailingTextFontSize, fontWeight = FontWeight.Bold)) {
                            append(parts.getOrNull(1) ?: "00")
                        }
                    },
                    color = Color(0xFF0A1B3E),
                    softWrap = false,
                    maxLines = 1,
                    overflow = TextOverflow.Clip,
                    onTextLayout = {
                        result->
                        if (result.hasVisualOverflow) {
                            // Handle overflow
                           // showUpdateTextSize = true
                            leadingTextFontSize *= 0.9f
                            trailingTextFontSize *= 0.9f

                        }
                    }
                )

                // Share Icon
                Icon(
                    imageVector = Icons.Default.Share,
                    contentDescription = "Share",
                    tint = Color(0xFF1E88E5),
                    modifier = Modifier.size(24.dp).clickable(
                        onClick = { /* Handle share action */ },
                        indication = null,
                        interactionSource = remember { MutableInteractionSource() }
                    )
                )
            }
        }
    }
}