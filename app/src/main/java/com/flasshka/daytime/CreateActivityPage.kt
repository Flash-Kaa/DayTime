package com.flasshka.daytime

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class CreateActivityPage {

    private val drawCreateButton = mutableStateOf(true)
    private val description =  mutableStateOf("")
    private val tag = mutableStateOf("")

    @Composable
    fun Draw (
        image: Painter,
        createButtonModifier: Modifier = Modifier,
        addAndCancelButtonsModifier: Modifier = Modifier,
        fontSize: TextUnit = 14.sp,
        buttonSpaceSize: Dp = 30.dp
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
                DrawTextFields()

                Spacer(modifier = Modifier.size(10.dp))

                Row {
                    DrawAddAndCancelButtons(
                        fontSize = fontSize,
                        spaceSize = buttonSpaceSize,
                        modifier = addAndCancelButtonsModifier
                    )
                }
            }
        }
    }

    @Composable
    private fun DrawTextFields() {
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
    }

    @Composable
    private fun DrawAddAndCancelButtons(
        spaceSize: Dp,
        fontSize: TextUnit,
        modifier: Modifier = Modifier
    ) {
        Button(
            modifier = modifier,
            onClick = {
                ListActivities.Add(Activity(tag.value, description.value))
                backState()
            }
        ) {
            Text("Add", fontSize = fontSize)
        }

        Spacer(modifier = Modifier.size(spaceSize))

        Button(
            modifier = modifier,
            onClick = { backState() }
        ) {
            Text("Cancel", fontSize = fontSize)
        }
    }

    private fun backState() {
        drawCreateButton.value = true
        description.value = ""
        tag.value = ""
    }
}