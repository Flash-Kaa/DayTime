package com.flasshka.daytime

import Clock
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.jakewharton.threetenabp.AndroidThreeTen


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AndroidThreeTen.init(this)
            val clock = Clock()
            MainPage(clock)
        }
    }
}

@Composable
fun MainPage(clock: Clock) {
    Column (
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Green),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center

    ) {
        clock.DrawTimer(
            modifier = Modifier.size(200.dp),
            color = Color.Black,
            width = 10.dp,
            fontSize = 25.sp
        )
    }
}
