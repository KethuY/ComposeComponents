package com.example.windowscompactexample.components.input.models

/**
 * @Author: Yerramma Kethu
 * @Date: 21/06/2025
 */
data class AmountUICallBackListener(
    val onAmountChange: ((String) -> Unit)? = null,
    val onAccountSelection: (() -> Unit)? = null,
    val onFocused: ((Boolean) -> Unit)? = null,
    val onDone: (() -> Unit)? = null
)