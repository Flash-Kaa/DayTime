package com.flasshka.daytime

import android.annotation.SuppressLint
import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.util.DisplayMetrics
import android.util.Log
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

    lateinit var sharedPref: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val createActivityPage = CreateActivityPage()

        setContent {
            val clock = Clock(context = this)

            val displayMetrics = DisplayMetrics()
            windowManager.defaultDisplay.getMetrics(displayMetrics)
            MainPage(
                clock = clock,
                displayMetrics = displayMetrics,
                createActivityPage = createActivityPage
            )


            sharedPref = this.getPreferences(Context.MODE_PRIVATE)
            val set = sharedPref.getStringSet(Tags.ListActivitySaveTag, mutableSetOf())
            set?.let { ListActivities.WriteSetString(it) }
        }
    }

    @SuppressLint("CommitPrefEdits")
    override fun onPause() {
        super.onPause()

        val editor = sharedPref.edit()
        val r = ListActivities.ReadSetString()
        if(editor == null)
            Log.d("Tags.ListActivitySaveTag", "editor null")
        editor?.putStringSet(Tags.ListActivitySaveTag, r)
        editor?.apply()
        Log.d("Tags.ListActivitySaveTag", r.toString() + editor.toString())
    }

    @Composable
    private fun MainPage(
        clock: Clock,
        displayMetrics: DisplayMetrics,
        createActivityPage: CreateActivityPage
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

            createActivityPage.Draw(
                image = painterResource(id = R.drawable.creating_button),
                createButtonModifier = Modifier.size(80.dp),
                addAndCancelButtonsModifier = Modifier.size(width = 110.dp, height = 50.dp),
                fontSize = 18.sp
            )

            ListActivities.DrawActivities(displayMetrics)
        }
    }
}
