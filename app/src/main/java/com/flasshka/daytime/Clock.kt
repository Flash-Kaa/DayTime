package com.flasshka.daytime

import android.content.Context
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
import com.jakewharton.threetenabp.AndroidThreeTen
import kotlinx.coroutines.*
import org.threeten.bp.LocalTime
import org.threeten.bp.format.DateTimeFormatter

class Clock(context: Context) {

    companion object {
        val timeFormatter: DateTimeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss")
        val timeFormatterWithoutSeconds: DateTimeFormatter = DateTimeFormatter.ofPattern("HH:mm")
    }

    init {
        AndroidThreeTen.init(context)
    }

    private val timeState = mutableStateOf(LocalTime.now())

    @Composable
    fun DrawTimer(
        modifier: Modifier = Modifier,
        fontSize: TextUnit = 35.sp,
        widthOfSecondArc: Dp = 15.dp,
        arcColors: List<Color> = listOf(Color.Blue, Color.Green),
    ) {
        Box(
            modifier = modifier,
            contentAlignment = Alignment.Center
        ) {
            Canvas(modifier = modifier) {
                drawArc(
                    brush = Brush.horizontalGradient(arcColors),
                    startAngle = -90f,
                    sweepAngle = ((timeState.value.nano * 1e-9 + timeState.value.second) * 6f).toFloat(),
                    useCenter = false,
                    style = Stroke(
                        width = widthOfSecondArc.toPx(),
                        cap = StrokeCap.Round
                    )
                )
            }

            Text(text = timeState.value.format(timeFormatter), fontSize = fontSize)

            LaunchedEffect(Unit) {
                while (true) {
                    delay(20)
                    timeState.value = LocalTime.now()
                }
            }
        }
    }
}