package com.example.windowscompactexample.testing

import androidx.compose.foundation.layout.Column
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.example.windowscompactexample.components.IconTextTabs

@Composable
     fun ShowTabs() {
        val tabs = listOf(
            Icons.Default.Favorite to "Cards",
            Icons.Default.AccountBox to "Finance",
            Icons.Default.AccountCircle to "Takaful"
        )
        Column {
            var selectedTab by remember { mutableIntStateOf(0) }

            IconTextTabs(
                tabs = tabs,
                selectedIndex = selectedTab,
                onTabSelected = { selectedTab = it }
            )
        }
    }