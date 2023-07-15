package com.flasshka.daytime

import android.util.Log
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.unit.*
import kotlinx.coroutines.*
import org.threeten.bp.LocalTime
import org.threeten.bp.format.DateTimeFormatter

class Clock {

    companion object {
        val timeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss")
    }

    private val timeState = mutableStateOf(LocalTime.now())

    init {
        timerUpd()
    }

    private fun timerUpd() {
        MainScope().launch {
            while (true) {
                timeState.value = LocalTime.now()
                Log.d("test", timeState.value.format(timeFormatter))
                delay(100)
            }
        }
    }

    @Composable
    fun DrawTimer(
        modifier: Modifier = Modifier,
        fontSize: TextUnit = 35.sp,
        widthOfSecondArc: Dp = 15.dp,
        secondArcColors: List<Color> = listOf(Color.Blue, Color.Green),
    ) {
        Box(
            modifier = modifier,
            contentAlignment = Alignment.Center
        ) {
            Canvas(modifier = modifier
            ) {
                drawArc(
                    brush = Brush.horizontalGradient(
                        colors = secondArcColors
                    ),
                    startAngle = -90f,
                    sweepAngle = (timeState.value.nano * 0.000000001f + timeState.value.second) * 6f,
                    useCenter = false,
                    style = Stroke (
                        width = widthOfSecondArc.toPx(),
                        cap = StrokeCap.Round
                    )
                )
            }
            Text(text = timeState.value.format(timeFormatter), fontSize = fontSize)
        }
    }
}