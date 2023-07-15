package com.flasshka.daytime

import android.util.DisplayMetrics
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

object ListActivities {

    val activities = mutableListOf<Activity>()

    @Composable
    fun DrawActivities(displayWidth: Float) {
        Column(
            modifier = Modifier.verticalScroll(ScrollState(0))
        ) {
            for (activity in activities) {
                Canvas(modifier = Modifier.padding(vertical = 5.dp)) {
                    drawLine(
                        color = Color.Black,
                        start = Offset(10.dp.toPx(), 0f),
                        end = Offset(displayWidth - 10.dp.toPx(), 0f),
                        strokeWidth = 3.dp.toPx()
                    )
                }
                activity.Draw(fontSize = 20.sp, modifier = Modifier.fillMaxWidth())
            }
        }
    }
}