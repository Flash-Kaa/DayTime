package com.flasshka.daytime

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Button
import androidx.compose.material.Checkbox
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

object CreateActivityPage {

    private val drawCreateButton = mutableStateOf(true)
    private val description =  mutableStateOf("")
    private val tag = mutableStateOf("")

    private fun backState() {
        drawCreateButton.value = true
        description.value = ""
        tag.value = ""
    }

    @Composable
    fun Draw (
        image: Painter,
        createButtonModifier: Modifier = Modifier,
        addAndBackButtonsModifier: Modifier = Modifier,
        fontSize: TextUnit = 14.sp
    ) {
        if(drawCreateButton.value) {
            Image(
                painter = image,
                contentDescription = "button to create activity",
                modifier = createButtonModifier.clickable { drawCreateButton.value = false }
            )
        }
        else {
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                TextField(
                    value = tag.value,
                    onValueChange = { tag.value = it},
                    singleLine =  true,
                    label = { Text(text = "The tag of your activity") },
                    placeholder = { Text(text = "None") }
                )

                TextField(
                    value = description.value,
                    onValueChange = { description.value = it},
                    singleLine =  true,
                    label = { Text(text = "Describe your activity") },
                    placeholder = { Text(text = "What are you doing?") }
                )

                var needTimer by remember { mutableStateOf(false)}
                var time: String? by remember { mutableStateOf(null)}

                Row {
                    // or use DropdownMenuItem
                    Text(
                        text = "Need to be reminded",
                        fontSize = fontSize,
                        modifier = Modifier.padding(vertical = 10.dp)
                    )
                    Checkbox(checked = needTimer, onCheckedChange = { needTimer = !needTimer })
                }

                if(needTimer) {
                    TextField(
                        value = time ?: "",
                        onValueChange = { time = it },
                        singleLine =  true,
                        label = { Text(text = "Set timer") },
                        placeholder = { Text(text = "hh:mm") }
                    )
                }

                Spacer(modifier = Modifier.size(10.dp))

                Row {
                    Button(
                        modifier = addAndBackButtonsModifier,
                        onClick = {
                            ListActivities.Add(Activity(tag.value, description.value))
                            backState()
                        },
                        content = { Text("Add", fontSize = fontSize) }
                    )

                    Spacer(modifier = Modifier.size(30.dp))

                    Button(
                        modifier = addAndBackButtonsModifier,
                        onClick = { backState() },
                        content = { Text("Back", fontSize = fontSize) }
                    )
                }
            }
        }
    }
}