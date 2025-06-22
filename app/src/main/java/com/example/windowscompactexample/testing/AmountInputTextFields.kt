package com.example.windowscompactexample.testing

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.windowscompactexample.R
import com.example.windowscompactexample.components.account.AdibAccountSelectionDefaults.accountIconDefaults
import com.example.windowscompactexample.components.account.AdibAccountSelectionDefaults.accountTitleDefaults
import com.example.windowscompactexample.components.account.AdibAccountSelectionDefaults.balanceTitleDefaults
import com.example.windowscompactexample.components.account.AdibAccountSelectionDefaults.selectionIconDefaults
import com.example.windowscompactexample.components.account.UiAccountSelection
import com.example.windowscompactexample.components.input.amount.UIAccountSelectionWithInput
import com.example.windowscompactexample.components.input.amount.UiAccountSelectionWithInputDivider
import com.example.windowscompactexample.components.input.amount.UiAmountUIDefaults
import com.example.windowscompactexample.components.input.amount.UiAmountUIDefaults.amountIcon
import com.example.windowscompactexample.components.input.amount.UiAmountUIDefaults.smallInputAccountTitle
import com.example.windowscompactexample.components.input.amount.UiSmallInput
import com.example.windowscompactexample.components.input.models.AmountUICallBackListener
import com.example.windowscompactexample.components.pro.uiDatamodels.AdibTextUiDataModel
import com.example.windowscompactexample.ui.theme.Adib14CaptionRegular

/**
 * @Author: Yerramma Kethu
 * @Date: 22/06/2025
 */
@Composable
fun AmountInputTextFields() {
    Column(modifier = Modifier.verticalScroll(rememberScrollState())) {
        var rawAmount by remember { mutableStateOf("") }

        UiAccountSelection(
            modifier = Modifier.padding(top = 16.dp),
            accountTitle = accountTitleDefaults("Saving account Abudhabi islamic bank * 5014"),
            balanceTitle = balanceTitleDefaults("AED 2,666.00"),
            selectionIcon = selectionIconDefaults(R.drawable.ic_drop_down),
            accountIcon = accountIconDefaults(R.drawable.ic_logo),
        )

        UiSmallInput(
            modifier = Modifier.padding(top = 16.dp),
            accountTitle = smallInputAccountTitle("From: Mohammed Saleh • 1234"),
            amountIcon = amountIcon(R.drawable.ic_logo),
            inputDataModel = UiAmountUIDefaults.smallAmountInputProperties(
                rawAmount,
                "AED"
            ),
            callBacks = AmountUICallBackListener()
        )

        UIAccountSelectionWithInput(
            modifier = Modifier.padding(top = 16.dp),
            accountTitle = accountTitleDefaults("From: Mohammed Saleh • 1234"),
            balanceTitle = balanceTitleDefaults("Balance: 230,000.00 AED"),
            inputAmount = UiAmountUIDefaults.amountInputProperties(
                rawAmount,
                "AED",
                230000.00
            ),
            selectionIcon = selectionIconDefaults(
                R.drawable.ic_drop_down,
            ),
            amountIcon = amountIcon(
                R.drawable.ic_logo,
                contentDesc = "Amount Icon"
            ),
            callBacks = AmountUICallBackListener()
        )

        UiAccountSelectionWithInputDivider(
            modifier = Modifier.padding(top = 16.dp),
            inputAmount = UiAmountUIDefaults.amountInputProperties(
                rawAmount,
                "AED",
                1000.00
            ),
            accountTitle = accountTitleDefaults("From: Mohammed Saleh • 1234"),
            balanceTitle = balanceTitleDefaults("Balance: 1000 AED"),
            selectionIcon = selectionIconDefaults(
                R.drawable.ic_drop_down,
            ),
            amountLabel = AdibTextUiDataModel(
                modifier = Modifier.padding(top = 16.dp),
                text = "Amount I need:",
                textStyle = Adib14CaptionRegular
            ),
            amountIcon = amountIcon(R.drawable.ic_logo),
            callBacks = AmountUICallBackListener()
        )

        UiAccountSelectionWithInputDivider(
            modifier = Modifier.padding(top = 16.dp),
            inputAmount = UiAmountUIDefaults.amountInputProperties(
                rawAmount,
                "AED",
                0.00
            ),
            accountTitle = UiAmountUIDefaults.bigInputAccountTitle("From: Mohammed Saleh • 1234"),
            balanceTitle = UiAmountUIDefaults.balanceTitle("Balance: 0.00 AED"),
            balanceError = UiAmountUIDefaults.balanceError("Insufficient balance"),
            selectionIcon = selectionIconDefaults(
                R.drawable.ic_drop_down,
            ),
            amountLabel = UiAmountUIDefaults.amountLabel("Amount I need:"),
            amountIcon = amountIcon(R.drawable.ic_logo),
            amountError = UiAmountUIDefaults.amountError("Internal server error"),
            callBacks = AmountUICallBackListener()
        )
    }
}