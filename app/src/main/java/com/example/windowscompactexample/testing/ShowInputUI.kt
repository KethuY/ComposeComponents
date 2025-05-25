package com.example.windowscompactexample.testing

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.windowscompactexample.R
import com.example.windowscompactexample.components.AdibBaseSmallInput
import com.example.windowscompactexample.components.UiAccountSelection
import com.example.windowscompactexample.components.BaseBigInput
import com.example.windowscompactexample.components.UiActionTextWithLeadingIcon
import com.example.windowscompactexample.components.UiSelectAccount
import com.example.windowscompactexample.ui.theme.ColorBrandPrimaryThree
import com.example.windowscompactexample.ui.theme.ColorInputsBackground

/**
 * @Author: Yerramma Kethu
 * @Date: 23/05/2025
 */


@Composable
fun ShowInputUI() {
    Column(modifier = Modifier.verticalScroll(rememberScrollState())) {
        var inputText1 by remember { mutableStateOf("") }
        var inputText2 by remember { mutableStateOf("") }
        var inputText3 by remember { mutableStateOf("") }
        var inputText4 by remember { mutableStateOf("") }
        var inputText5 by remember { mutableStateOf("") }
        BaseBigInput(
            modifier = Modifier.padding(20.dp),
            backgroundColor = ColorInputsBackground,
            shape = RoundedCornerShape(20.dp),
            title = "From: Current account fgdjghjdf hfgdhgdfjhgh • 0711",
            subTitle = "Balance: AED 230,000.10",
            inputPrefix = "AED",
            description = "Amount I need:",
            trailingIcon = R.drawable.ic_chevron_down,
            inputText = inputText1,
            leadingIcon = R.drawable.ic_adib,
            actualLimit = 230000.10,
            topErrorMessage = "You don't have the sufficient funds",
            onTextChange = {
                inputText1 = it
                println("Kethu Amount it")
            }
        )

        BaseBigInput(
            modifier = Modifier.padding(20.dp),
            backgroundColor = ColorInputsBackground,
            shape = RoundedCornerShape(20.dp),
            title = "From: Current account fgdjghjdf hfgdhgdfjhgh • 0711",
            subTitle = "Balance: AED 0.00",
            inputPrefix = "AED",
            description = "Amount I need:",
            trailingIcon = R.drawable.ic_chevron_down,
            inputText = inputText2,
            leadingIcon = R.drawable.ic_adib,
            actualLimit = 0.00,
            topErrorMessage = "You don't have the sufficient funds",
            onTextChange = {
                inputText2 = it
            }
        )

        BaseBigInput(
            modifier = Modifier.padding(20.dp),
            backgroundColor = ColorInputsBackground,
            shape = RoundedCornerShape(20.dp),
            title = "From: Current account fgdjghjdf hfgdhgdfjhgh • 0711",
            subTitle = "Balance: AED 230000.10",
            inputPrefix = "AED",
            description = "Amount I need:",
            trailingIcon = R.drawable.ic_chevron_down,
            inputText = inputText3,
            leadingIcon = R.drawable.ic_adib,
            actualLimit = 230000.10,
            bottomErrorMessage = "Min Amount is 100 and Max amount is 100000",
            minLimit = 100.00,
            maxLimit = 100000.00,
            onTextChange = {
                inputText3 = it
            }
        )

        AdibBaseSmallInput(
            modifier = Modifier.padding(20.dp),
            backgroundColor = ColorBrandPrimaryThree,
            shape = RoundedCornerShape(20.dp),
            title = "From: Current account • 0711",
            inputPrefix = "AED",
            inputText = inputText4,
            leadingIcon = R.drawable.ic_adib,
            onTextChange = {
                inputText4 = it
            }
        )

        UiAccountSelection(
            modifier = Modifier.padding(20.dp),
            title = null,
            subTitle = "From: Current account fgdjghjdf hfgdhgdfjhgh • 0711",
            leadingIcon = R.drawable.ic_adib,
            isLeadingIconVisible = true,
            trailingIcon = R.drawable.ic_chevron_down,
            isTrailingIconVisible = true
        )

        UiAccountSelection(
            modifier = Modifier.padding(20.dp),
            title = "From: Current account fgdjghjdf hfgdhgdfjhgh • 0711",
            subTitle = "Balance: AED 230,000.10",
            leadingIcon = R.drawable.ic_adib,
            isLeadingIconVisible = true,
            trailingIcon = R.drawable.ic_chevron_down,
            isTrailingIconVisible = true
        )

        UiAccountSelection(
            modifier = Modifier.padding(20.dp),
            title = "From: Current account fgdjghjdf hfgdhgdfjhgh • 0711",
            subTitle = "Balance: AED 230,000.10",
            leadingIcon = R.drawable.ic_adib,
            isLeadingIconVisible = true,
            trailingIcon = R.drawable.ic_chevron_down,
            isTrailingIconVisible = false
        )

        UiAccountSelection(
            modifier = Modifier.padding(20.dp),
            title = "From: Current account fgdjghjdf hfgdhgdfjhgh • 0711",
            subTitle = "Balance: AED 230,000.10",
            leadingIcon = R.drawable.ic_adib,
            isLeadingIconVisible = false,
            trailingIcon = R.drawable.ic_chevron_down,
            isTrailingIconVisible = false
        )

        UiAccountSelection(
            modifier = Modifier.padding(20.dp),
            title = "From: Current account fgdjghjdf hfgdhgdfjhgh • 0711",
            subTitle = "Balance: AED 230,000.10",
            leadingIcon = R.drawable.ic_adib,
            isLeadingIconVisible = false,
            trailingIcon = R.drawable.ic_chevron_down,
            isTrailingIconVisible = false
        )

        UiAccountSelection(
            modifier = Modifier
                .padding(20.dp),
            title = "From: Current account fgdjghjdf hfgdhgdfjhgh • 0711",
            subTitle = "Balance: AED 230,000.10",
            leadingIcon = R.drawable.ic_adib,
            isLeadingIconVisible = false,
            trailingIcon = R.drawable.ic_chevron_down,
            isTrailingIconVisible = true
        )

        UiAccountSelection(
            modifier = Modifier
                .padding(20.dp),
            title = "From: Current account • 0711",
            subTitle = null,
            leadingIcon = null,
            isLeadingIconVisible = false,
            trailingIcon = null,
            isTrailingIconVisible = false
        )

        UiSelectAccount(modifier = Modifier.padding(20.dp))

        UiActionTextWithLeadingIcon(
            modifier = Modifier.fillMaxWidth(),
            leadingIcon = R.drawable.ic_plus_round_solid,
            title = "Open an account",
            horizontalAlignment = Arrangement.Center
        )
    }
}
