package com.example.windowscompactexample.utils

import java.math.BigDecimal
import java.text.DecimalFormat
import java.text.DecimalFormatSymbols
import java.util.Locale

/**
 * @Author: Yerramma Kethu
 * @Date: 24/05/2025
 */

object AmountUtils {
    private const val FORMAT_AMOUNT_WITH_NO_DECIMALS: String = "###,###,###"
    private const val FORMAT_AMOUNT_WITH_DECIMALS: String = "###,###,###.##"
    private const val DEFAULT_MAX_AMOUNT_LIMIT = 1e14

    fun getFormattedAmount(formattedAmount: String?): String {
        val amountParts = formattedAmount.orEmpty().split(".")
        val integerPart = amountParts.getOrNull(0).orEmpty()
        val decimalPart = amountParts.getOrNull(1).orEmpty()
        return when {
            amountParts.size == 1 -> {
                amountFormatter(integerPart)
            }

            decimalPart.length <= 2 -> {
                "${amountFormatter(integerPart)}.${decimalPart}"
            }

            else -> {
                "${amountFormatter(integerPart)}.${decimalPart.take(2)}"
            }
        }
    }

    private fun amountFormatter(
        amount: String?,
    ): String {
        val amountWithoutComma = amount.removeSpecialCharFromAmount()
        if (amountWithoutComma <= 0) {
            return ""
        }
        return amountFormatter(amountWithoutComma, FORMAT_AMOUNT_WITH_NO_DECIMALS)
    }

    fun String?.removeSpecialCharFromAmount(symbol: String = ","): Double =
        (this?.replace(symbol, "").orEmpty().toDoubleOrNull()) ?: 0.00

    private fun amountFormatter(
        amount: Double?,
        formatterType: String = FORMAT_AMOUNT_WITH_DECIMALS
    ): String {
        val nonNullableAmount = amount ?: 0.00
        return DecimalFormat(
            formatterType,
            DecimalFormatSymbols.getInstance(Locale.ENGLISH)
        ).format(nonNullableAmount)
    }


    fun getInputLength(amount: Double?) = when {
        amount == null -> getFormattedDefaultAmount().length // 100,000,000,000,000.00
        amount <= 0 -> 0
        else -> amountFormatter(amount).length
    }

    private fun getFormattedDefaultAmount() =
        DecimalFormat(FORMAT_AMOUNT_WITH_NO_DECIMALS, DecimalFormatSymbols(Locale.US)).format(
            BigDecimal(DEFAULT_MAX_AMOUNT_LIMIT.toString())
        )

    // formatte amount keyboard done button click
    fun formatDecimalPoint(amount: String): String {
        return if (amount.contains(".")) {
            when (val decimals = amount.substringAfter(".")) {
                "00" -> amount
                else -> {
                    when (decimals.length) {
                        2 -> amount
                        1 -> amount + "0"
                        else -> amount.substringBefore(".") + "." + decimals.take(2)
                    }
                }
            }
        } else {
            "$amount.00"
        }
    }

    fun getMaxLimit(
        amount: Double?
    ) = amount ?: DEFAULT_MAX_AMOUNT_LIMIT

    fun String.properDecimalInput(): Double {
        val sb = StringBuilder()
        var dotSeen = false

        for (c in this) {
            when {
                c.isDigit() -> sb.append(c)
                c == '.' && !dotSeen -> {
                    sb.append(c)
                    dotSeen = true
                }
            }
        }
        return sb.toString().removeSpecialCharFromAmount()
    }
}