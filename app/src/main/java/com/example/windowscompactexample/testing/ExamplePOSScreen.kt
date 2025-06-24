package com.example.windowscompactexample.testing

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.example.windowscompactexample.POSAmountInputField

@Composable
fun ExamplePOSScreen() {
    var amount by remember { mutableLongStateOf(0L) }

    POSAmountInputField(
        amountInCents = amount,
        onAmountChange = { newCents ->
            amount = newCents

            println()
        }
    )
}
