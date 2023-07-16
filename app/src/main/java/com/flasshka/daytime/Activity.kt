package com.flasshka.daytime

import android.util.DisplayMetrics
import android.util.Log
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import org.threeten.bp.LocalTime

class Activity(
    private val tag: String, private val description: String,
    private val startTime: LocalTime, private val endTime: MutableState<LocalTime?> = mutableStateOf(null)
) {
    companion object {
        var startLineBetweenTimes = 5.dp
        var lenLineBetweenTimes = 100.dp
        var tagSize = 20.dp
        var deleteAndRepeatButtonsStize = 40.dp
    }

    @Composable
    fun Draw(
        fontSize: TextUnit,
        displayWidth: DisplayMetrics
    ) {
        Row(
            modifier = Modifier
                .padding(5.dp)
                .fillMaxWidth()
        ) {

            DrawTime(fontSize = fontSize)
            DrawTagWithDescription(fontSize = fontSize)

            if(endTime.value == null) {
                DrawButtonToEndTimer(fontSize = fontSize)
            }
            else {
                DrawDeleteAndRepeatButtons(displayWidth)
            }
        }
    }

    @Composable
    private fun DrawTime(fontSize: TextUnit) {
        Column(modifier = Modifier.fillMaxWidth(0.25f)) {
            Text(text = startTime.format(Clock.timeFormatter), fontSize = fontSize)

            Canvas(modifier = Modifier) {
                drawLine(
                    color = Color.Gray,
                    start =  Offset( startLineBetweenTimes.toPx(), 0f),
                    end = Offset(lenLineBetweenTimes.toPx() + startLineBetweenTimes.toPx(), 0f),
                    strokeWidth = 2f
                )
            }
            endTime.value?.let {
                Text(text = it.format(Clock.timeFormatter), fontSize = fontSize)
            }
        }
    }

    @Composable
    private fun DrawTagWithDescription(fontSize: TextUnit) {
        Column(modifier = Modifier.fillMaxWidth(0.65f)) {
            Row {
                Image(
                    modifier = Modifier
                        .padding(5.dp)
                        .size(tagSize),
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
                    .fillMaxWidth()
                    .padding(horizontal = 7.dp, vertical = 0.dp))
        }
    }

    @Composable
    private fun DrawButtonToEndTimer(fontSize: TextUnit) {
        Button(
            onClick = { endTime.value = LocalTime.now() },
            colors = ButtonDefaults.buttonColors(backgroundColor = Color.Red, contentColor = Color.White),
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 10.dp)
        ) {
            Text(text = "End", color = Color.White, fontSize = fontSize)
        }
    }

    @Composable
    private fun DrawDeleteAndRepeatButtons(displayWidth: DisplayMetrics) {
        Image(
            modifier = Modifier
                .padding(5.dp)
                .size(deleteAndRepeatButtonsStize)
                .clickable {
                    val newActivity = Activity(
                        tag = tag,
                        description = description,
                        startTime = LocalTime.now()
                    )

                    ListActivities.Add(newActivity)
                    Log.d(
                        LogTags.ActivityChangeTag,
                        "added $newActivity [tag: $tag; description: $description; timeStart: $startTime; endTime: $endTime], new len: ${ListActivities.Count()}"
                    )
                    ListActivities.RedrawList()
                },
            painter = painterResource(id = R.drawable.repeat),
            contentDescription = "repeat"
        )

        Image(
            modifier = Modifier
                .padding(5.dp)
                .size(deleteAndRepeatButtonsStize)
                .clickable {
                    ListActivities.Remove(this)
                    Log.d(
                        LogTags.ActivityChangeTag,
                        "removed $this [tag: $tag; description: $description; timeStart: $startTime; endTime: $endTime], new len: ${ListActivities.Count()}"
                    )

                    ListActivities.RedrawList()
               },
            painter = painterResource(id = R.drawable.delete),
            contentDescription = "delete"
        )
    }
}