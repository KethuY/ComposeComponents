package com.example.windowscompactexample

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.windowscompactexample.components.Details
import com.example.windowscompactexample.testing.ChipGroupDemo
import com.example.windowscompactexample.testing.RadioButtonSample
import com.example.windowscompactexample.testing.RadioGroupSample
import com.example.windowscompactexample.testing.ShowAuthPin
import com.example.windowscompactexample.testing.ShowCheckBoxes
import com.example.windowscompactexample.testing.ShowTabs
import com.example.windowscompactexample.testing.YesNoToggleSwitch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                Column(modifier = Modifier.verticalScroll(rememberScrollState())) {
                    Spacer(modifier = Modifier.height(64.dp))
                    ShowCheckBoxes()
                   // Spacer(modifier = Modifier.height(32.dp))
                   // RadioButtonSample()
                  //  Spacer(modifier = Modifier.height(32.dp))
                  //  RadioGroupSample()
                    Spacer(modifier = Modifier.height(32.dp))
                    ChipGroupDemo()
                    ShowTabs()
                    Spacer(modifier = Modifier.height(32.dp))
                    Details()
                    Spacer(modifier = Modifier.height(32.dp))
                    ShowAuthPin()
                    Spacer(modifier = Modifier.height(32.dp))
                    YesNoToggleSwitch()
                }
            }
        }
    }
}
