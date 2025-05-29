package com.example.windowscompactexample

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsFocusedAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.text2.BasicTextField2
import androidx.compose.foundation.text2.input.InputTransformation
import androidx.compose.foundation.text2.input.TextFieldBuffer
import androidx.compose.foundation.text2.input.TextFieldCharSequence
import androidx.compose.foundation.text2.input.TextFieldLineLimits
import androidx.compose.foundation.text2.input.maxLengthInChars
import androidx.compose.foundation.text2.input.rememberTextFieldState
import androidx.compose.foundation.text2.input.then
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.windowscompactexample.components.AdibBaseButton
import com.example.windowscompactexample.ui.theme.Adib14CaptionRegular
import com.example.windowscompactexample.ui.theme.Adib16BodyRegular
import com.example.windowscompactexample.ui.theme.AdibColorInputsBackground
import com.example.windowscompactexample.ui.theme.AdibColorInputsPlaceholder
import com.example.windowscompactexample.ui.theme.AdibColorInteraction
import com.example.windowscompactexample.ui.theme.AdibColorPlaceholderText
import com.example.windowscompactexample.ui.theme.ColorInputsBackground
import com.example.windowscompactexample.ui.theme.ColorSemanticErrorTwo
import com.example.windowscompactexample.ui.theme.ColorTextBase
import com.example.windowscompactexample.ui.theme.ColorTextSubdued
import com.example.windowscompactexample.ui.theme.Dimens.AdibSizeFontOne
import com.example.windowscompactexample.ui.theme.Dimens.AdibSizeFontWebCaption
import com.example.windowscompactexample.ui.theme.Dimens.AdibSizeSpacingMedium
import com.example.windowscompactexample.ui.theme.Dimens.AdibSizeSpacingSmall
import com.example.windowscompactexample.utils.Utils.KeyboardVisibilityObserver
import com.example.windowscompactexample.testing.ShowInputUI
import com.example.windowscompactexample.testing.PosStyleMoneyInput

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalFoundationApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Scaffold(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(vertical = 60.dp, horizontal = 60.dp)
            ) { innerPadding ->

                Column() {
                    PosStyleMoneyInput()
                }
            }
        }
    }

    @OptIn(ExperimentalFoundationApi::class)
    @Composable
    private fun ShowBasicTextField(
        modifier: Modifier = Modifier,
        label: String,
        labelStyle: TextStyle = Adib16BodyRegular,
        labelIcon: Int? = null,
        labelOnclick: (() -> Unit)? = null,
        prefixIcon: Int? = null,
        inputText: String,
        placeholder: String,
        limits: TextFieldLineLimits = TextFieldLineLimits.SingleLine,
        inputTransformation: InputTransformation? = null,
        keyboardType: KeyboardType,
        imeAction: ImeAction,
        inputTrailingIcon: Int? = null,
        isError: Boolean = false,
        hint: String? = null,
        onTextChange: (String) -> Unit = {},
        onImeActionClicked: () -> Unit = {}
    ) {

        Column(modifier = modifier.padding(20.dp)) {



            TextWithTrailingIcon(
                title = label,
                titleStyle = labelStyle,
                trailingIcon = labelIcon,
                labelOnclick = labelOnclick
            )

            ShowInputUIField(
                prefixIcon = prefixIcon,
                inputText = inputText,
                placeholder = placeholder,
                limits = limits,
                inputTransformation = inputTransformation,
                keyboardType = keyboardType,
                imeAction = imeAction,
                trailingIcon = inputTrailingIcon,
                isErrorEnabled = isError,
                onTextChange = onTextChange,
                onImeActionClicked = onImeActionClicked
            )

            if (!hint.isNullOrBlank()) {
                Text(
                    modifier = Modifier.padding(top = AdibSizeFontOne.dp),
                    text = hint,
                    style = Adib14CaptionRegular,
                    color = if (isError) ColorSemanticErrorTwo else ColorTextSubdued
                )
            }
        }
    }

    @OptIn(ExperimentalFoundationApi::class)
    @Composable
    private fun ShowInputUIField(
        prefixIcon: Int? = null,
        inputText: String,
        placeholder: String,
        limits: TextFieldLineLimits,
        inputTransformation: InputTransformation? = null,
        keyboardType: KeyboardType,
        imeAction: ImeAction,
        trailingIcon: Int? = null,
        isErrorEnabled: Boolean = false,
        onTextChange: (String) -> Unit = {},
        onImeActionClicked: () -> Unit = {},
        maxLenth: Int = 3
    ) {
        val interactionSource = remember { MutableInteractionSource() }
        val isFocused by interactionSource.collectIsFocusedAsState()
        val focusManager = LocalFocusManager.current
        val textFieldState = rememberTextFieldState(initialText = inputText)
        val focusRequester = remember { FocusRequester() }
        val borderColor = when {
            isErrorEnabled -> ColorSemanticErrorTwo
            isFocused -> AdibColorPlaceholderText
            else -> Color.Transparent
        }

        LaunchedEffect(textFieldState) {
            val input = textFieldState.text.toString().trim()

            val maxLines = input.split("\n").size

            if (maxLines <= maxLenth) {
                println("✅ Kethu LaunchedEffect: maxLines $maxLines")
                onTextChange(input)
            }
        }


        KeyboardVisibilityObserver { visible ->
            println("🧭 Kethu Keyboard is ${if (visible) "OPEN" else "CLOSED"}")
            if (!visible) {
                focusManager.clearFocus()
            }
        }

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = AdibSizeFontOne.dp)
                .border(
                    width = 1.dp,
                    color = borderColor,
                    shape = RoundedCornerShape(AdibSizeSpacingMedium.dp)
                )
                .background(
                    color = AdibColorInputsBackground,
                    shape = RoundedCornerShape(AdibSizeSpacingMedium.dp)
                )
                .focusRequester(focusRequester)
                .clickable(
                    indication = null,
                    interactionSource = interactionSource
                ) {
                    println("🟢 Kethu Field tapped - requesting focus")
                    focusRequester.requestFocus()
                }
        ) {
            Row {
                if (prefixIcon != null) {
                    Icon(
                        modifier = Modifier.padding(start = AdibSizeSpacingSmall.dp),
                        imageVector = ImageVector.vectorResource(prefixIcon),
                        contentDescription = null
                    )
                }

                Box(
                    Modifier
                        .weight(1f)
                        .padding(
                            start = if (prefixIcon != null) AdibSizeFontWebCaption.dp else AdibSizeSpacingMedium.dp,
                            top = 12.dp,
                            bottom = 12.dp
                        )
                ) {
                    if (textFieldState.text.isEmpty()) {
                        Text(
                            text = placeholder,
                            style = Adib16BodyRegular,
                            color = AdibColorInputsPlaceholder
                        )
                    }
                    BasicTextField2(
                        state = textFieldState,
                        interactionSource = interactionSource,
                        lineLimits = limits,
                        textStyle = Adib16BodyRegular,
                        keyboardOptions = KeyboardOptions.Default.copy(
                            keyboardType = keyboardType,
                            imeAction = imeAction
                        ),
                        cursorBrush = SolidColor(ColorTextBase),
                        keyboardActions = getBoardActions(imeAction) {
                            onImeActionClicked.invoke()
                            focusManager.clearFocus()
                        },
                        inputTransformation = inputTransformation
                    )
                }

                if (trailingIcon != null) {
                    Icon(
                        imageVector = ImageVector.vectorResource(trailingIcon),
                        contentDescription = null,
                        tint = AdibColorInteraction
                    )
                }
            }
        }
    }

    @Composable
    private fun getBoardActions(imeAction: ImeAction, onImeAction: () -> Unit) =
        KeyboardActions(
            onDone = { if (imeAction == ImeAction.Done) onImeAction() },
            onNext = { if (imeAction == ImeAction.Next) onImeAction() },
            onGo = { if (imeAction == ImeAction.Go) onImeAction() },
            onSend = { if (imeAction == ImeAction.Send) onImeAction() },
            onSearch = { if (imeAction == ImeAction.Search) onImeAction() },
            onPrevious = { if (imeAction == ImeAction.Previous) onImeAction() }
        )

    @Composable
    private fun TextWithTrailingIcon(
        modifier: Modifier = Modifier,
        title: String,
        titleStyle: TextStyle,
        trailingIcon: Int? = null,
        labelOnclick: (() -> Unit)? = null,
    ) {
        Row(modifier = modifier) {
            Text(
                text = title,
                style = titleStyle,
            )
            if (trailingIcon != null) {
                Icon(
                    modifier = Modifier
                        .padding(start = AdibSizeSpacingSmall.dp)
                        .align(Alignment.CenterVertically)
                        .clickable {
                            labelOnclick?.invoke()
                        },
                    imageVector = ImageVector.vectorResource(R.drawable.ic_information_icon),
                    contentDescription = null,
                    tint = AdibColorInteraction
                )
            }
        }
    }


    @OptIn(ExperimentalFoundationApi::class)
    data class RegexTransformation(
        private val regex: Regex,
        private val onErrorEnabled: (Boolean) -> Unit
    ) : InputTransformation {
        override fun transformInput(
            originalValue: TextFieldCharSequence,
            valueWithChanges: TextFieldBuffer
        ) {
            val newData = valueWithChanges.toString()
            val isFullName = newData.split(" ").size >= 2
            onErrorEnabled.invoke(!isFullName)
            if (!regex.matches(valueWithChanges.toString())) {
                return
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


    @OptIn(ExperimentalFoundationApi::class)
    @Composable
    fun PasswordTextField2(
        inputText: String,
        modifier: Modifier = Modifier,
        label: String = "Password",
        isPassword: Boolean = true,
        inputLength: Int = 8,
    ) {

        val focusManager = LocalFocusManager.current
        val textFieldState = rememberTextFieldState(initialText = inputText)
        val interactionSource = remember { MutableInteractionSource() }
        val focusRequester = remember { FocusRequester() }

        KeyboardVisibilityObserver { visible ->
            println("🧭 Kethu Keyboard is ${if (visible) "OPEN" else "CLOSED"}")
            if (!visible) {
                focusManager.clearFocus()
            }
        }


        Column(modifier = modifier.padding(horizontal = 32.dp, vertical = 64.dp)) {
            Text(text = label, style = TextStyle(fontSize = 14.sp, color = Color.Gray))
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .focusRequester(focusRequester)
                    .clickable(
                        indication = null,
                        interactionSource = interactionSource
                    ) {
                        println("🟢 Kethu Field tapped - requesting focus")
                        focusRequester.requestFocus()
                    }
                    .padding(vertical = 8.dp)
                    .height(56.dp)
                    /*.border(
                    width = 1.dp,
                    color = Color.Gray,
                    shape = OutlinedTextFieldDefaults.shape
                )*/
                    .padding(horizontal = 16.dp, vertical = 8.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                if (textFieldState.text.isEmpty()) {
                    Text(
                        text = "Enter your password",
                        style = TextStyle(fontSize = 16.sp, color = Color.LightGray)
                    )
                }
                BasicTextField2(
                    state = textFieldState,
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                    textStyle = TextStyle(fontSize = 16.sp, color = Color.Black),
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxHeight(),
                    inputTransformation = InputTransformation.maxLengthInChars(inputLength).then(
                        PasswordInputTransformation
                    ),
                )
            }
        }
    }

    @OptIn(ExperimentalFoundationApi::class)
    object PasswordInputTransformation :
        InputTransformation {

        override fun transformInput(
            originalValue: TextFieldCharSequence,
            valueWithChanges: TextFieldBuffer
        ) {
            val actualText = valueWithChanges.toString()
            val displayedText = "•".repeat(actualText.length)

            valueWithChanges.replace(
                0,
                valueWithChanges.length,
                displayedText
            )

        }
    }


    @OptIn(ExperimentalFoundationApi::class)
    data class MultiLineTransformation(val maxLines: Int = 3) :
        InputTransformation {

        override fun transformInput(
            originalValue: TextFieldCharSequence,
            valueWithChanges: TextFieldBuffer
        ) {
            val actualText = valueWithChanges.toString()
            val lineCount = actualText.count { it == '\n' } + 1
            val isValid = lineCount <= 3

            if (!isValid) {
                return
            }
        }
    }
}