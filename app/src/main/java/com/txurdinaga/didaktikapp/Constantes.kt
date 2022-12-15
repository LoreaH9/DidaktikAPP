@file:Suppress("DEPRECATION")

package com.txurdinaga.didaktikapp

import android.content.Context
import android.content.SharedPreferences
import android.content.res.Resources
import com.google.android.gms.maps.model.LatLng
import java.util.*

object Constantes {

    internal class TipoUsu(context: Context) {
        val PREFS_NAME = "com.g2.sharedpreferences"
        val SHARED_NAME = "Tipo"
        val name: SharedPreferences = context.getSharedPreferences(PREFS_NAME, 0)
        var tipo: String
            //comprobamos el archivo de shared preferences
            get() = name.getString(SHARED_NAME, "alumno").toString()
            //modificamos el valor de shared preferences
            set(value) = name.edit().putString(SHARED_NAME, value).apply()
    }

    internal class PuntoPartida(context: Context) {
        val PREFS_NAME = "com.g2.didaktikapp.PuntoPartida"
        val SHARED_NAME = "Partida"
        val PuntoPartida: SharedPreferences = context.getSharedPreferences(PREFS_NAME, 0)
        var Partida: String
            get() = PuntoPartida.getString(SHARED_NAME, "0").toString()
            set(value) = PuntoPartida.edit().putString(SHARED_NAME, value).apply()
    }


    internal class ModoLibre(context: Context) {
        val PREFS_NAME = "com.g2.didaktikapp.sharedpreferences"
        val SHARED_NAME = "Modo"
        val name: SharedPreferences = context.getSharedPreferences(PREFS_NAME, 0)
        var modo: Boolean
            get() = name.getBoolean(SHARED_NAME, false)
            set(value) = name.edit().putBoolean(SHARED_NAME, value).apply()

    }

    internal class User(context: Context) {
        val PREFS_NAME = "com.g2.didaktikapp.sharedpreferences.User"
        val SHARED_NAME = "User"
        val User: SharedPreferences = context.getSharedPreferences(PREFS_NAME, 0)
        var user: String
            get() = User.getString(SHARED_NAME, "").toString()
            set(value) = User.edit().putString(SHARED_NAME, value).apply()
    }

    internal class idioma(context: Context) {
        val PREFS_NAME = "com.g2.didaktikapp.sharedpreferences.idioma"
        val SHARED_NAME = "Idioma"
        val Idioma: SharedPreferences = context.getSharedPreferences(PREFS_NAME, 0)
        var idioma: String
            get() = Idioma.getString(SHARED_NAME, "").toString()
            set(value) = Idioma.edit().putString(SHARED_NAME, value).apply()

        fun aldatu(hizkuntza_berria: String, resources: Resources) {
            SharedPrefs.idioma.idioma = hizkuntza_berria
            val locale = Locale(SharedPrefs.idioma.idioma)
            Locale.setDefault(locale)
            val resources = resources
            val configuration = resources.configuration
            configuration.setLocale(locale)
            resources.updateConfiguration(configuration, resources.displayMetrics)
        }
    }

    val BasilicaStaMaria = LatLng(43.319806, -3.016722)
    val PlazaSRoque = LatLng(43.3179121, -3.020576198034128)
    val TorreSalazar = LatLng(43.32024476997707, -3.0171418190002446)
    val Mercado = LatLng( 43.32164439841235, -3.0177814777832044)
    val Cascoviejo = LatLng(  43.3207146004226, -3.0178300457686014)
    val Zunzunegui = LatLng(  43.32254620046105, -3.0185475097471284)
    val Puente = LatLng(  43.323242893062826, -3.017147915508491)
    internal val paradas: List<LatLng> = listOf(BasilicaStaMaria, PlazaSRoque, TorreSalazar, Mercado, Cascoviejo, Zunzunegui, Puente)
    internal val nombre_paradas: List<String> = listOf("Actividad 1", "Actividad 2", "Actividad 3", "Actividad 4", "Actividad 5", "Actividad 6", "Actividad 7")

}