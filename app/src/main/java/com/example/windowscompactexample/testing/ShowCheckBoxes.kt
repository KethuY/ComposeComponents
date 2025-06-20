package com.example.windowscompactexample.testing

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.windowscompactexample.components.checkbox.UiCheckBox

@Composable
fun ShowCheckBoxes() {
    UiCheckBox(
        title = "Header here",
        subTitle = "Sub header here",
    ) { checked,subTitle ->
        println("Kethu1 $checked $subTitle")
    }
    Spacer(modifier = Modifier.height(16.dp))
    UiCheckBox(
        subTitle = "Sub header here",
    ){ checked,subTitle ->
        println("Kethu2 $checked $subTitle")
    }

    Spacer(modifier = Modifier.height(16.dp))
    val listNames = listOf("Sub header here1", "Sub header here2", "Sub header here3")


    var listNames1 = mutableSetOf<String>()
    listNames.forEach {
        UiCheckBox(
            modifier = Modifier.padding(top = 16.dp, start = 16.dp, end = 16.dp),
            subTitle = it,
        ) { checked, subTitle ->
            println("Kethu3 $checked $subTitle")
            if (checked) {
                listNames1.add(subTitle)
            } else {
                listNames1 = listNames1.filter {  subTitle == it }.toMutableSet()
            }
            println("Kethu3 listNames1 $listNames1")
        }
    }
}