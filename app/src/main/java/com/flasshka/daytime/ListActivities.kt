package com.flasshka.daytime

import android.util.DisplayMetrics
import android.util.Log
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

object ListActivities {
    var offset = 10.dp
    var strokeWidth = 3.dp
    var fontSize = 20.sp

    private val activities = mutableListOf<Activity>()
    private var changeToRedraw by mutableStateOf(false)

    fun RedrawList() {
        changeToRedraw = !changeToRedraw
    }

    fun Add(element: Activity) = activities.add(0, element)

    fun Remove(element: Activity) = activities.remove(element)
    fun Remove(index: Int) = activities.removeAt(index)

    fun Count() = activities.size

    @Composable
    fun DrawActivities(displayWidth: DisplayMetrics) {
        Log.d(LogTags.ActivityChangeTag, "createLazyList")

        LazyColumn(
        ) {
            if(changeToRedraw || !changeToRedraw) {
                Log.d(LogTags.ActivityChangeTag, "drawActivities")

                items(activities) { activity ->
                    Log.d(LogTags.ActivityChangeTag, "drawActivity $activity")

                    Canvas(modifier = Modifier.padding(vertical = 5.dp)) {
                        drawLine(
                            color = Color.Black,
                            start = Offset(offset.toPx(), 0f),
                            end = Offset(displayWidth.widthPixels.toFloat() - offset.toPx(), 0f),
                            strokeWidth = strokeWidth.toPx()
                        )
                    }
                    activity.Draw(fontSize = fontSize, displayWidth = displayWidth)
                }
            }
        }
    }
}