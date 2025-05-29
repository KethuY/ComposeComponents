package com.example.windowscompactexample.testing

import androidx.compose.foundation.text.BasicTextField
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.*
import androidx.compose.ui.text.input.*
import androidx.compose.ui.unit.sp
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.ui.Alignment
import androidx.compose.ui.unit.dp

@Composable
fun PosStyleMoneyInput(
    modifier: Modifier = Modifier,
    currencySymbol: String = "AED ",
    maxDigits: Int = 9, // max 99,999,999.99
    onValueChange: (String) -> Unit = {}
) {
    var rawDigits by remember { mutableStateOf("") }

    // Limit digits if needed
    fun safeRawDigits(new: String) = new.take(maxDigits.coerceAtMost(15)) // safeguard

    // Format raw input to money string
    val formattedAmount = remember(rawDigits) {
        val digits = rawDigits.filter { it.isDigit() }.let(::safeRawDigits)
        val padded = digits.padStart(3, '0')
        val dollars = padded.dropLast(2)
        val cents = padded.takeLast(2)

        val amount = dollars.toIntOrNull()?.toString()?.reversed()
            ?.chunked(3)?.joinToString(",")?.reversed() ?: "0"

        "$currencySymbol$amount.$cents"
    }

    // Cursor always at the end
    val textFieldValue = remember(formattedAmount) {
        TextFieldValue(
            text = formattedAmount,
            selection = TextRange(formattedAmount.length)
        )
    }

    BasicTextField(
        value = textFieldValue,
        onValueChange = { newValue ->
            val lastChar = newValue.text.lastOrNull()
            val isDelete = newValue.text.length < formattedAmount.length

            rawDigits = when {
                !isDelete && lastChar?.isDigit() == true -> safeRawDigits(rawDigits + lastChar)
                isDelete && rawDigits.isNotEmpty() -> rawDigits.dropLast(1)
                else -> rawDigits
            }

            onValueChange(formattedAmount)
        },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
        textStyle = TextStyle(
            fontSize = 24.sp,
            color = Color.Black
        ),
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp)
    )
}



/*
fun PosStyleMoneyInput(
    modifier: Modifier = Modifier,
    onValueChange: (String) -> Unit = {}
) {
    var rawDigits by remember { mutableStateOf("") }

    // Format the raw digits into currency format like 0.00
    val formattedValue = remember(rawDigits) {
        val cleaned = rawDigits.filter { it.isDigit() }
        val padded = cleaned.padStart(3, '0')
        val dollars = padded.dropLast(2)
        val cents = padded.takeLast(2)
        "${dollars.toInt()}.${cents}"
    }


    BasicTextField(
        value = formattedValue,
        onValueChange = { newText ->
            val newDigit = newText.lastOrNull()?.takeIf { it.isDigit() }
            val oldLength = formattedValue.length
            val newLength = newText.length

            rawDigits = when {
                newLength > oldLength && newDigit != null -> rawDigits + newDigit
                newLength < oldLength && rawDigits.isNotEmpty() -> rawDigits.dropLast(1)
                else -> rawDigits
            }

            onValueChange(formattedValue)
        },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
        textStyle = TextStyle(
            fontSize = 24.sp,
            color = Color.Black,
            textAlign = TextAlign.End,
            fontWeight = FontWeight.Medium
        ),
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp)
    )
}
*/