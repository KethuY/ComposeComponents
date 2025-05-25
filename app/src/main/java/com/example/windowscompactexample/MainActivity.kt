package com.example.windowscompactexample

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.text2.BasicTextField2
import androidx.compose.foundation.text2.input.TextFieldLineLimits
import androidx.compose.foundation.text2.input.rememberTextFieldState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import com.example.windowscompactexample.testing.ShowInputUI
import com.example.windowscompactexample.ui.theme.ColorInputsBackground
import com.example.windowscompactexample.utils.Utils.KeyboardVisibilityObserver

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalFoundationApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Scaffold(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(vertical = 60.dp)
            ) { innerPadding ->

                ShowInputUI()
            }
        }
    }

    @Composable
    @OptIn(ExperimentalFoundationApi::class)
    private fun BasicKeyboardFocusHandler() {
        val textFieldState = rememberTextFieldState()
        val focusRequester = remember { FocusRequester() }
        val focusManager = LocalFocusManager.current
        val interactionSource = remember { MutableInteractionSource() }

        KeyboardVisibilityObserver { visible ->
            // isKeyboardVisible = visible
            println("🧭 Kethu Keyboard is ${if (visible) "OPEN" else "CLOSED"}")
            if (!visible) {
                focusManager.clearFocus()
            }
        }


        //  var count by remember { mutableIntStateOf(0) }
        Column(
            modifier = Modifier
                .fillMaxSize()
                .pointerInput(Unit) {
                    detectTapGestures {
                        println("🖐️ Kethu Tapped outside - clearing focus")
                        focusManager.clearFocus()
                    }
                }
                .verticalScroll(rememberScrollState())
        ) {
            Text("Tap the input below. Try tapping outside or hitting Done/back.")

            BasicTextField2(
                state = textFieldState,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 32.dp)
                    .background(
                        ColorInputsBackground,
                        shape = RoundedCornerShape(20.dp)
                    )
                    .padding(
                        horizontal = 16.dp,
                        vertical = 24.dp
                    )
                    .focusRequester(focusRequester)
                    .clickable(
                        indication = null,
                        interactionSource = interactionSource
                    ) {
                        println("🟢 Kethu Field tapped - requesting focus")
                        focusRequester.requestFocus()
                    },
                textStyle = LocalTextStyle.current,
                lineLimits = TextFieldLineLimits.SingleLine,
                keyboardOptions = KeyboardOptions.Default.copy(
                    imeAction = ImeAction.Done
                ),
                keyboardActions = KeyboardActions(
                    onDone = {
                        println("✅ Kethu IME action Done pressed")
                        focusManager.clearFocus()
                    }
                ),
                cursorBrush = SolidColor(MaterialTheme.colorScheme.primary)
            )
        }
    }
}
