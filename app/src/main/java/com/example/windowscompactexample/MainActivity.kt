package com.example.windowscompactexample

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import java.text.NumberFormat
import java.util.Locale

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Scaffold(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(vertical = 60.dp, horizontal = 20.dp)
            ) { innerPadding ->
                Column(modifier = Modifier.verticalScroll(rememberScrollState())) {
                    ExamplePOSScreen()
                }
            }
        }
    }
}

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

        /*    val cents = digitsOnly.toLongOrNull() ?: 0L
            onAmountChange(cents)*/
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
