package com.example.windowscompactexample.utils

import android.text.style.ForegroundColorSpan
import android.text.style.StrikethroughSpan
import android.text.style.StyleSpan
import android.text.style.URLSpan
import android.text.style.UnderlineSpan
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.ime
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.TextToolbar
import androidx.compose.ui.platform.TextToolbarStatus
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.core.text.HtmlCompat
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock

/**
 * @Author: Yerramma Kethu
 * @Date: 21/05/2025
 */

object Utils {


    val disabledToolbar = object : TextToolbar {
        override val status: TextToolbarStatus
            get() = TextToolbarStatus.Hidden

        override fun hide() {
            // No-op
        }

        override fun showMenu(
            rect: Rect,
            onCopyRequested: (() -> Unit)?,
            onPasteRequested: (() -> Unit)?,
            onCutRequested: (() -> Unit)?,
            onSelectAllRequested: (() -> Unit)?
        ) {
            // No-op
        }
    }



    @Composable
    fun rememberSingleClick(debounceTime: Long = 1000L, onClick: () -> Unit): () -> Unit {
        var lastClickTime by remember { mutableLongStateOf(0L) }
        return {
            val currentTime = System.currentTimeMillis()
            val diff = currentTime - lastClickTime
            println("Clicked at $currentTime, diff = $diff")
            if (diff >= debounceTime) {
                lastClickTime = currentTime
                println("Kethu singleClick clicked differenceTime $diff")
                onClick()
            }
        }
    }


    fun Modifier.singleClickable(
        debounceTime: Long = 1000L,
        onClick: () -> Unit
    ): Modifier {
        val mutex = Mutex()
        var lastClickTime = 0L
        return this.then(
            Modifier.pointerInput(Unit) {
                coroutineScope {
                    detectTapGestures {
                        launch {
                            val currentTime = System.currentTimeMillis()
                            mutex.withLock {
                                val differenceTime = currentTime - lastClickTime
                                if (differenceTime >= debounceTime) {
                                    println("Kethu singleClick clicked differenceTime $differenceTime")
                                    lastClickTime = currentTime
                                    onClick()
                                }
                            }
                        }
                    }
                }
            }
        )
    }


    @Composable
    fun KeyboardVisibilityObserver(
        onKeyboardVisibilityChanged: (Boolean) -> Unit
    ) {
        val imeBottom = WindowInsets.ime.getBottom(LocalDensity.current)
        val isKeyboardVisible = remember(imeBottom) { imeBottom > 0 }

        // Send callback when visibility changes
        LaunchedEffect(isKeyboardVisible) {
            onKeyboardVisibilityChanged(isKeyboardVisible)
        }
    }

    fun htmlToAnnotatedString(html: String): AnnotatedString {
        val spanned = HtmlCompat.fromHtml(html, HtmlCompat.FROM_HTML_MODE_LEGACY)
        return buildAnnotatedString {
            val text = spanned.toString()
            append(text)

            val spans = spanned.getSpans(0, text.length, Any::class.java)

            for (span in spans) {
                val start = spanned.getSpanStart(span)
                val end = spanned.getSpanEnd(span)

                when (span) {
                    is StyleSpan -> when (span.style) {
                        android.graphics.Typeface.BOLD -> addStyle(
                            SpanStyle(fontWeight = FontWeight.Bold),
                            start, end
                        )
                        android.graphics.Typeface.ITALIC -> addStyle(
                            SpanStyle(fontStyle = FontStyle.Italic),
                            start, end
                        )
                        android.graphics.Typeface.BOLD_ITALIC -> addStyle(
                            SpanStyle(fontWeight = FontWeight.Bold, fontStyle = FontStyle.Italic),
                            start, end
                        )
                    }

                    is UnderlineSpan -> addStyle(
                        SpanStyle(textDecoration = TextDecoration.Underline),
                        start, end
                    )

                    is StrikethroughSpan -> addStyle(
                        SpanStyle(textDecoration = TextDecoration.LineThrough),
                        start, end
                    )

                    is ForegroundColorSpan -> addStyle(
                        SpanStyle(color = Color(span.foregroundColor)),
                        start, end
                    )

                    is URLSpan -> {
                        addStyle(
                            SpanStyle(
                                color = Color(0xFF1E88E5),
                                textDecoration = TextDecoration.Underline
                            ),
                            start, end
                        )
                        addStringAnnotation(
                            tag = "URL",
                            annotation = span.url,
                            start = start,
                            end = end
                        )
                    }
                }
            }
        }
    }
}