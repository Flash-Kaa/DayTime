package com.flasshka.daytime

import android.os.Bundle
import android.util.DisplayMetrics
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import org.threeten.bp.LocalTime

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val clock = Clock(context = this)

            val displayMetrics = DisplayMetrics()
            windowManager.defaultDisplay.getMetrics(displayMetrics)

            Column (
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.White),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center

            ) {
                Spacer(modifier = Modifier.size(0.dp, 90.dp))
                
                clock.DrawTimer(
                    modifier = Modifier.size(200.dp)
                )

                Image(
                    painter = painterResource(id = R.drawable.creating_button),
                    contentDescription = "button to create activity",
                    modifier = Modifier
                        .padding(top = 100.dp)
                        .size(80.dp)
                        .clickable {
                            ListActivities.Add(
                                Activity(
                                    tag = "this tag",
                                    description = "test activity", LocalTime.now()
                                )
                            )
                        }
                )

                ListActivities.DrawActivities(displayMetrics)
            }

        }


    }
}
