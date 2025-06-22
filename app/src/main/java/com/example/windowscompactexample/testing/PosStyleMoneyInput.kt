package com.example.windowscompactexample.testing

import androidx.compose.foundation.text.BasicTextField
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.*
import androidx.compose.ui.text.input.*
import androidx.compose.ui.unit.sp
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.ui.unit.dp

@Composable
fun PosStyleMoneyInput(
    modifier: Modifier = Modifier,
    currencySymbol: String="AED ",
    maxDigits: Int = 9, // max 99,999,999.99
    onValueChange: (String) -> Unit = {}
) {
    var amountWithoutSpecialChars by remember { mutableStateOf("") }

    println("Kethu  amountWithoutSpecialChars $amountWithoutSpecialChars")
    // Limit digits if needed
    fun safeRawDigits(new: String) = new.take(maxDigits.coerceAtMost(15)) // safeguard

    // Format raw input to money string
    val formattedAmount = remember(amountWithoutSpecialChars) {
        val inputAmount = amountWithoutSpecialChars.filter { it.isDigit() }.let(::safeRawDigits)
        val padded = inputAmount.padStart(3, '0')
        val beforeDecimal = padded.dropLast(2)
        val afterDecimal = padded.takeLast(2)
        val amount = beforeDecimal.toIntOrNull()?.toString()?.reversed()
            ?.chunked(3)?.joinToString(",")?.reversed() ?: "0"
        println("Kethu digits: $inputAmount  padded: $padded  dollars: $beforeDecimal  cents: $afterDecimal  amount: $amount")
        "$currencySymbol $amount.$afterDecimal"
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
            println("Kethu lastChar: $lastChar  isDelete: $isDelete")

            amountWithoutSpecialChars = when {
                !isDelete && lastChar?.isDigit() == true -> safeRawDigits(amountWithoutSpecialChars + lastChar)
                isDelete && amountWithoutSpecialChars.isNotEmpty() -> amountWithoutSpecialChars.dropLast(1)
                else -> amountWithoutSpecialChars
            }

            println("Kethu rawDigits: $amountWithoutSpecialChars")
            println("Kethu formattedAmount: $formattedAmount")
            onValueChange(formattedAmount)
        },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number, imeAction = ImeAction.Done),
        textStyle = TextStyle(
            fontSize = 24.sp,
            color = Color.Black
        ),
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp)
    )
}