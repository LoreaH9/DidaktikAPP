package com.txurdinaga.didaktikapp

import android.content.pm.ActivityInfo
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainDialogo : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.layout_dialogo)
        //requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE
    }
}