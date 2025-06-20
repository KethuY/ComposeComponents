package com.example.windowscompactexample.testing

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.unit.dp
import com.example.windowscompactexample.R
import com.example.windowscompactexample.components.account.AdibAccountSelectionDefaults
import com.example.windowscompactexample.components.account.UiAccountSelection
import com.example.windowscompactexample.ui.theme.AdibColorSegmentMassTwo

/**
 * @Author: Yerramma Kethu
 * @Date: 20/06/2025
 */
@Composable
fun ShowAccountSelection() {
    UiAccountSelection(
        modifier = Modifier.padding(top = 16.dp),
        backgroundColor = Color.Unspecified,
        shape = RectangleShape,
        paddingValues = PaddingValues(0.dp),
        accountTitle = AdibAccountSelectionDefaults.accountTitleDefaults("Saving account * 5014"),
        balanceTitle = AdibAccountSelectionDefaults.balanceTitleDefaults("AED 2,666.00"),
        selectionIcon = AdibAccountSelectionDefaults.selectionIconDefaults(R.drawable.ic_drop_down),
        accountIcon = AdibAccountSelectionDefaults.accountIconDefaults(R.drawable.ic_logo),
        isSelectionIconVisible = true
    )

    UiAccountSelection(
        modifier = Modifier.padding(top = 16.dp),
        backgroundColor = Color.Unspecified,
        shape = RectangleShape,
        accountTitle = AdibAccountSelectionDefaults.accountTitleDefaults("Saving account * 5014"),
        balanceTitle = AdibAccountSelectionDefaults.balanceTitleDefaults("AED 2,666.00"),
        selectionIcon = AdibAccountSelectionDefaults.selectionIconDefaults(R.drawable.ic_drop_down),
        accountIcon = AdibAccountSelectionDefaults.accountIconDefaults(R.drawable.ic_logo),
        isSelectionIconVisible = true
    )
    UiAccountSelection(
        modifier = Modifier.padding(top = 16.dp),
        accountTitle = AdibAccountSelectionDefaults.accountTitleDefaults("Saving account * 5014"),
        balanceTitle = AdibAccountSelectionDefaults.balanceTitleDefaults("AED 2,666.00"),
        selectionIcon = AdibAccountSelectionDefaults.selectionIconDefaults(R.drawable.ic_drop_down),
        isSelectionIconVisible = true
    )

    UiAccountSelection(
        modifier = Modifier.padding(top = 16.dp),
        accountTitle = AdibAccountSelectionDefaults.accountTitleDefaults("Saving account Abudhabi islamic bank * 5014"),
        balanceTitle = AdibAccountSelectionDefaults.balanceTitleDefaults("AED 2,666.00"),
        selectionIcon = AdibAccountSelectionDefaults.selectionIconDefaults(R.drawable.ic_drop_down),
        isSelectionIconVisible = true
    )

    UiAccountSelection(
        modifier = Modifier.padding(top = 16.dp),
        accountTitle = AdibAccountSelectionDefaults.accountTitleDefaults("Saving account Abudhabi islamic bank * 5014"),
        balanceTitle = AdibAccountSelectionDefaults.balanceTitleDefaults("AED 2,666.00"),
        selectionIcon = AdibAccountSelectionDefaults.selectionIconDefaults(R.drawable.ic_drop_down),
        accountIcon = AdibAccountSelectionDefaults.accountIconDefaults(R.drawable.ic_logo),
        isSelectionIconVisible = true
    )

    UiAccountSelection(
        modifier = Modifier.padding(top = 16.dp),
        accountTitle = AdibAccountSelectionDefaults.accountTitleDefaults("Saving account Abudhabi islamic bank * 5014"),
        balanceTitle = AdibAccountSelectionDefaults.balanceTitleDefaults("AED 2,666.00"),
        accountIcon = AdibAccountSelectionDefaults.accountIconDefaults(R.drawable.ic_logo),
    )

    UiAccountSelection(
        modifier = Modifier.padding(top = 16.dp),
        accountTitle = AdibAccountSelectionDefaults.accountTitleDefaults("To: Mohammed Ahmed • 1234 "),
        balanceTitle = null
    )

    UiAccountSelection(
        modifier = Modifier.padding(top = 16.dp),
        backgroundColor = AdibColorSegmentMassTwo,
        accountTitle = AdibAccountSelectionDefaults.accountTitleDefaults("Saving account Abudhabi islamic bank * 5014"),
        balanceTitle = AdibAccountSelectionDefaults.balanceTitleDefaults("AED 2,666.00"),
        selectionIcon = AdibAccountSelectionDefaults.selectionIconDefaults(R.drawable.ic_drop_down),
        accountIcon = AdibAccountSelectionDefaults.accountIconDefaults(R.drawable.ic_logo),
        isSelectionIconVisible = true
    )
}