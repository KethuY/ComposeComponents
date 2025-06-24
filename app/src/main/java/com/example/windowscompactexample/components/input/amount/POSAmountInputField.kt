package com.example.windowscompactexample.components.input.amount

import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.sp
import java.text.NumberFormat
import java.util.Locale

@Composable
fun POSAmountInputField(
    amountInCents: Long,
    onAmountChange: (Long) -> Unit,
    modifier: Modifier = Modifier,
    currencyLocale: Locale = Locale.US,
    textStyle: TextStyle = TextStyle.Default.copy(fontSize = 24.sp)
) {
    var rawInput by remember { mutableStateOf("") }

    // Format display using NumberFormat
    val formattedText = remember(rawInput) {
        val cents = rawInput.toLongOrNull() ?: 0L
        formatCents(cents, currencyLocale)
    }

    val textFieldValue = remember(formattedText) {
        TextFieldValue(
            text = formattedText,
            selection = TextRange(formattedText.length)
        )
    }

    LaunchedEffect(textFieldValue.text) {
        onAmountChange.invoke(textFieldValue.text.toLongOrNull()?:0L)
    }

    BasicTextField(
        value = textFieldValue,
        onValueChange = { userInput ->
            // Keep only digits
            val digitsOnly = userInput.text.filter(Char::isDigit)
            rawInput = digitsOnly
            
        },
        modifier = modifier,
        keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
        textStyle = textStyle
    )
}


fun formatCents(cents: Long, locale: Locale): String {
    val formatter = NumberFormat.getNumberInstance(locale).apply {
        maximumFractionDigits = 2
        minimumFractionDigits = 2
        isGroupingUsed = true
    }
    return formatter.format(cents / 100.0)
}
