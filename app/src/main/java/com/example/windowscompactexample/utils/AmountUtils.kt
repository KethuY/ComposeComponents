package com.example.windowscompactexample.utils

import java.text.NumberFormat
import java.util.Locale


/**
 * @Author: Yerramma Kethu
 * @Date: 24/05/2025
 */

object AmountUtils {
    const val MAX_NO_OF_DIGITS = 15
    private const val CONST_TWO = 2
    private const val MAX_DECIMAL_DIGITS = CONST_TWO
    private const val CONST_ZERO = 0
    private const val CONST_ONE = 1
    private const val MIN_DECIMAL_DIGITS = CONST_ZERO
    const val CHAR_DOT = '.'
    private const val CHAR_COMMA = ","
    private const val CHAT_EMPTY = ""
    const val AMOUNT_DEFAULT_PLACEHOLDER = "0.00"
    private const val SUFFIX_DOT_ZEROS = ".00"
    const val DEFAULT_MAX_AMOUNT = 1e14


    fun String.plainInput(): String {
        val sb = StringBuilder()
        var dotSeen = false
        for (c in this) {
            when {
                c.isDigit() -> sb.append(c)
                c == CHAR_DOT && !dotSeen -> {
                    sb.append(c)
                    dotSeen = true
                }
            }
        }
        return sb.toString().replace(CHAR_COMMA, CHAT_EMPTY)
    }

    fun getActualAmount(
        amount: String
    ) = amount.plainInput().toDoubleOrNull() ?: 0.00

    fun getFormattedAmount(
        noOfDigits: Int = MAX_NO_OF_DIGITS,
        maxDecimalDigits: Int = MAX_DECIMAL_DIGITS,
        minDecimalDigits: Int = MIN_DECIMAL_DIGITS,
        amount: String
    ): String {
        val plainText = amount.plainInput()
        if (plainText.isBlank()) {
            return plainText
        }
        val (whole, decimalPart) = plainText.split(CHAR_DOT, limit = CONST_TWO).let {
            it.getOrElse(CONST_ZERO) { CHAT_EMPTY } to it.getOrNull(CONST_ONE).orEmpty()
        }
        val hasTrailingDot = plainText.endsWith(CHAR_DOT)

        val formatter =
            getNumberFormater(
                noOfDigits = noOfDigits,
                maxDecimalDigits = maxDecimalDigits,
                minDecimalDigits = minDecimalDigits
            )
        val formattedWhole = formatter.format(whole.toBigIntegerOrNull() ?: CONST_ZERO)
        return when {
            hasTrailingDot -> "$formattedWhole."
            decimalPart.isNotEmpty() -> "$formattedWhole.${decimalPart.take(maxDecimalDigits)}"
            else -> formattedWhole
        }
    }

    fun getFormatedAmountOnDone(
        amount: String
    ) = when {
        amount.isBlank() -> AMOUNT_DEFAULT_PLACEHOLDER
        !amount.contains(CHAR_DOT) -> "$amount$SUFFIX_DOT_ZEROS"
        amount.substringAfter(CHAR_DOT).length == CONST_ONE -> "$amount$MIN_DECIMAL_DIGITS"
        else -> amount
    }

    private fun getNumberFormater(
        maxDecimalDigits: Int,
        minDecimalDigits: Int,
        noOfDigits: Int
    ) = NumberFormat.getNumberInstance(Locale.US).apply {
        maximumIntegerDigits = noOfDigits
        minimumFractionDigits = minDecimalDigits
        maximumFractionDigits = maxDecimalDigits
        isGroupingUsed = true
    }
}