package com.txurdinaga.didaktikapp

import android.app.Application
import com.txurdinaga.didaktikapp.room.DataBaseRoomApp

class DataBaseApp : Application() {
    lateinit var sharedApp: SharedPrefs
    lateinit var roomApp: DataBaseRoomApp

    override fun onCreate() {
        super.onCreate()
        sharedApp = SharedPrefs()
        roomApp = DataBaseRoomApp()
    }
}
