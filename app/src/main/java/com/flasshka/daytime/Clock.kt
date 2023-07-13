import android.os.Build
import android.os.SystemClock
import android.util.Log
import android.widget.TextClock
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import com.jakewharton.threetenabp.AndroidThreeTen
import kotlinx.coroutines.*
import org.threeten.bp.LocalTime
import org.threeten.bp.format.DateTimeFormatter
import java.time.Clock
import kotlin.concurrent.thread


class Clock {

    private val timeToWriteState = mutableStateOf("")

    var timeToWrite: String
        get() = timeToWriteState.value
        private set(value) {
            timeToWriteState.value = value
        }

    init {
        timerUpd()
    }

    @Composable
    fun DrawTimer(
        modifier: Modifier,
        color: Color,
        width: Dp,
        fontSize: TextUnit
    ) {
        Box(
            modifier = modifier,
            contentAlignment = Alignment.Center
        ) {
            Canvas(modifier = modifier
            ) {

                drawArc(
                    color = color,
                    startAngle = -90f,
                    sweepAngle = 134f,
                    useCenter = false,
                    style = Stroke (
                        width = width.toPx(),
                        cap = StrokeCap.Round
                    )
                )
            }
            Text(text = timeToWrite, fontSize = fontSize)
            Log.d("test", "text written")
        }
    }

    fun timerUpd() {
        val formatter = DateTimeFormatter.ofPattern("HH:mm:ss")
        Log.d("test", "formatter init")

        MainScope().launch {
            while (true) {
                val time = LocalTime.now().format(formatter)

                if(time != timeToWrite) {
                    Log.d("test", time)
                    timeToWrite = time
                }
                delay(50)
            }
        }
    }
}
