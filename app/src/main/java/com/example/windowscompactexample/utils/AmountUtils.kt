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


    fun getInputLength(amount: Double?): Int {
        if (amount == null) {
            val defaultAmount = BigDecimal.valueOf(DEFAULT_MAX_AMOUNT_LIMIT)
            val defaultLength = defaultAmount.toPlainString().length
            println("Kethu getInputLength $defaultLength ${defaultAmount.toPlainString()}")
            return defaultAmount.toPlainString().length
        }
        return if (amount <= 0) {
            return 0
        } else amountFormatter(amount).length
    }

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
}