package com.flasshka.daytime

import android.os.Bundle
import android.util.DisplayMetrics
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.ScrollableState
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.verticalScroll
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.jakewharton.threetenabp.AndroidThreeTen
import org.threeten.bp.LocalTime

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AndroidThreeTen.init(this)
            val clock = Clock()

            val displayMetrics = DisplayMetrics()
            windowManager.defaultDisplay.getMetrics(displayMetrics)

            Column (
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.White),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center

            ) {
                Spacer(modifier = Modifier.size(0.dp, 75.dp))
                
                clock.DrawTimer(
                    modifier = Modifier.size(200.dp)
                )

                Spacer(modifier = Modifier.size(0.dp, 200.dp))
                for(i in 1..15) {
                    val act = Activity("this tag $i", "test activity", LocalTime.now())
                    ListActivities.Add(act)
                }
                ListActivities.DrawActivities(displayMetrics)
            }

        }


    }
}
