package com.flasshka.daytime

import android.util.DisplayMetrics
import android.util.Log
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.threeten.bp.LocalTime

object ListActivities {
    var offset = 10.dp
    var strokeWidth = 3.dp
    var fontSize = 20.sp

    private val activities = mutableListOf<Activity>()
    private var changeToRedraw by mutableStateOf(false)

    private val splitSymbol = "\n"

    fun Add(element: Activity) {
        activities.add(0, element)
        RedrawList()
    }

    fun Remove(element: Activity) {
        activities.remove(element)
        RedrawList()
    }

    fun Remove(index: Int) {
        activities.removeAt(index)
        RedrawList()
    }

    fun Count() = activities.size

    fun ReadSetString() = activities.map { it.GetStartTime() + splitSymbol +
            it.GetEndTime() + splitSymbol+ it.GetTag() + splitSymbol +
            it.GetDescription() + splitSymbol }.toSet()

    fun WriteSetString(set: MutableSet<String>) {
        set.forEach {
            val split = it.split(splitSymbol)

            if(split.size < 4) {
                throw Exception("Serialization exception")
            }

            val startTime = getLocalTime(split[0])
            val endTime = if(split[1] == "") null else getLocalTime(split[1])
            val tag = split[2]
            val description = split[3]

            activities.add(0, Activity(tag, description, startTime, mutableStateOf(endTime)))
        }
    }

    private fun getLocalTime(timeInString: String): LocalTime {
        val strStartTime = timeInString.split(":")
        val hours = strStartTime[0].toIntOrNull()
        val minutes = strStartTime[1].toIntOrNull()

        return if(hours != null && minutes != null) LocalTime.of(hours, minutes) else LocalTime.now()
    }

    private fun RedrawList() {
        changeToRedraw = !changeToRedraw
    }

    @Composable
    fun DrawActivities(displayWidth: DisplayMetrics) {
        Log.d(Tags.ActivityChangeLogTag, "createLazyList")

        LazyColumn {
            if(changeToRedraw || !changeToRedraw) {
                Log.d(Tags.ActivityChangeLogTag, "drawActivities")

                items(activities) { activity ->
                    Log.d(Tags.ActivityChangeLogTag, "drawActivity $activity")

                    Canvas(modifier = Modifier.padding(vertical = 5.dp)) {
                        drawLine(
                            color = Color.Black,
                            start = Offset(offset.toPx(), 0f),
                            end = Offset(displayWidth.widthPixels.toFloat() - offset.toPx(), 0f),
                            strokeWidth = strokeWidth.toPx()
                        )
                    }
                    activity.Draw(fontSize = fontSize)
                }
            }
        }
    }
}