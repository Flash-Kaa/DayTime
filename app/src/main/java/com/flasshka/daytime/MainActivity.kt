package com.flasshka.daytime

import android.os.Bundle
import android.util.DisplayMetrics
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val clock = Clock(context = this)

            val displayMetrics = DisplayMetrics()
            windowManager.defaultDisplay.getMetrics(displayMetrics)
            MainPage(
                clock = clock,
                displayMetrics = displayMetrics
            )
        }
    }

    
    @Composable
    fun MainPage(
        clock: Clock,
        displayMetrics: DisplayMetrics
    ) {
        Column (
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White),
            horizontalAlignment = Alignment.CenterHorizontally,

        ) {
            Spacer(modifier = Modifier.size(0.dp, 50.dp))

            clock.DrawTimer(modifier = Modifier.size(200.dp))

            Spacer(modifier = Modifier.size(0.dp, 40.dp))

            CreateActivityPage.Draw(
                image = painterResource(id = R.drawable.creating_button),
                createButtonModifier = Modifier.size(80.dp),
                addAndBackButtonsModifier = Modifier.size(width = 90.dp, height = 50.dp),
                fontSize = 18.sp
            )

            ListActivities.DrawActivities(displayMetrics)
        }
    }
}
