package com.txurdinaga.didaktikapp.room

import android.app.Application
import androidx.room.Room

class DataBaseRoomApp: Application() {
    companion object {
        lateinit var DataBase:UsuarioDb
    }

    override fun onCreate() {
        super.onCreate()
        DataBase = Room.databaseBuilder(applicationContext, UsuarioDb::class.java, UsuarioDb.DATABASE_NAME).build()
    }
}