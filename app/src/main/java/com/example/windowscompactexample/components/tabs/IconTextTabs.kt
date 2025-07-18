package com.example.windowscompactexample.components.tabs

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.material3.Icon
import androidx.compose.material3.TabPosition
import androidx.compose.material3.TabRow
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun IconTextTabs(
    tabs: List<Pair<ImageVector, String>>,
    selectedIndex: Int,
    onTabSelected: (Int) -> Unit,
    modifier: Modifier = Modifier,
    indicatorColor: Color = Color(0xFF1976D2), // Blue indicator
    selectedColor: Color = Color(0xFF1976D2),
    unselectedColor: Color = Color(0xFF6D6D6D),
    isRippleEnable: Boolean = true
) {
    val indicator = @Composable { tabPositions: List<TabPosition> ->
        if (selectedIndex < tabPositions.size) {
            Box(
                Modifier
                    .tabIndicatorOffset(tabPositions[selectedIndex])
                    .height(2.dp)
                    .fillMaxWidth()
                    .background(indicatorColor)
            )
        }
    }

    TabRow(
        selectedTabIndex = selectedIndex,
        modifier = modifier.padding(top = 100.dp),
        indicator = indicator,
        containerColor = Color.White,
        divider = {}
    ) {
        tabs.forEachIndexed { index, (icon, title) ->
            val isSelected = index == selectedIndex
            val interactionSource = remember { MutableInteractionSource() }

            Column(
                modifier = Modifier
                    .clickable(
                        interactionSource = interactionSource,
                        indication = if (isRippleEnable) rememberRipple(
                            radius = 60.dp,
                            color = Color(0xFF006DD1).copy(alpha = 0.12f)
                        ) else null,
                        onClick = { onTabSelected(index) }
                    )
                    .padding(12.dp)
                ,
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Icon(
                    imageVector = icon,
                    contentDescription = title,
                    tint = if (isSelected) selectedColor else unselectedColor,
                    modifier = Modifier.wrapContentSize()
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = title,
                    color = if (isSelected) selectedColor else unselectedColor,
                    fontSize = 16.sp,
                    fontWeight = if (isSelected) FontWeight.SemiBold else FontWeight.Normal,
                    textAlign = TextAlign.Center
                )
            }
        }
    }
}
