@file:Suppress("DEPRECATION")

package com.txurdinaga.didaktikapp.room

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import android.content.res.Resources
import androidx.room.Room
import com.txurdinaga.didaktikapp.Constantes
import com.txurdinaga.didaktikapp.SharedPrefs
import java.util.*

open class DataBaseRoomApp: Application() {

    companion object {
        lateinit var DataBase:UsuarioDb
    }


    override fun onCreate() {
        super.onCreate()
        SharedPrefs.users = Constantes.User(applicationContext)
        SharedPrefs.tipousu = Constantes.TipoUsu(applicationContext)
        SharedPrefs.puntopartida = Constantes.PuntoPartida(applicationContext)
        SharedPrefs.modolibre = Constantes.ModoLibre(applicationContext)
        SharedPrefs.idioma = Constantes.idioma(applicationContext)
        DataBase = Room.databaseBuilder(applicationContext, UsuarioDb::class.java, UsuarioDb.DATABASE_NAME).allowMainThreadQueries().build()
    }
}