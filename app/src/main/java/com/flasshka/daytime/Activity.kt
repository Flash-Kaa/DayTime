package com.flasshka.daytime

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import org.threeten.bp.LocalTime

class Activity(
    private val tag: String, private val description: String,
    private val startTime: LocalTime, private val endTime: MutableState<LocalTime?> = mutableStateOf(null)) {

    @Composable
    fun Draw(
        fontSize: TextUnit,
        modifier: Modifier = Modifier,
    ) {
        Row(
            modifier = modifier.padding(5.dp).fillMaxWidth()
        ) {
            Column(modifier = Modifier.fillMaxWidth(0.25f)) {
                Text(text = startTime.format(Clock.timeFormatter), fontSize = fontSize)

                Canvas(modifier = Modifier) {
                    drawLine(Color.Gray, Offset( 20f, 0f), Offset(200f, 0f), strokeWidth = 2f)
                }
                endTime.value?.let { Text(text = it.format(Clock.timeFormatter), fontSize = fontSize)}
            }

            Column(modifier = Modifier.fillMaxWidth(0.65f)) {
                Row(){
                    Image(
                        modifier = Modifier.padding(5.dp).size(20.dp),
                        painter = painterResource(id = R.drawable.tag),
                        contentDescription = "tag"
                    )
                    Text(text = tag, fontSize = fontSize)                    
                }
                Text(
                    text = description,
                    fontSize = fontSize,
                    modifier = Modifier
                        .background(Color.LightGray)
                        .padding(horizontal = 7.dp, vertical = 0.dp))
            }

            if(endTime.value == null) {
                Button(
                    onClick = { endTime.value = LocalTime.now() },
                    colors = ButtonDefaults.buttonColors(backgroundColor = Color.Red, contentColor = Color.White),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 10.dp)
                ) {
                    Text(text = "End", color = Color.White)
                }
            }
            else {
                Image(
                    modifier = Modifier.padding(5.dp).size(40.dp),
                    painter = painterResource(id = R.drawable.delete),
                    contentDescription = "delete"
                )
                Image(
                    modifier = Modifier.padding(5.dp).size(40.dp),
                    painter = painterResource(id = R.drawable.delete),
                    contentDescription = "delete"
                )
            }
        }
    }
}
