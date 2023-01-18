package com.txurdinaga.didaktikapp
import android.app.Application
import com.txurdinaga.didaktikapp.room.DataBaseRoomApp

class SharedPrefs : Application(){
    companion object {

        internal lateinit var users: Constantes.User
        internal lateinit var tipousu: Constantes.TipoUsu
        internal lateinit var puntopartida: Constantes.PuntoPartida
        internal lateinit var modolibre: Constantes.ModoLibre
        internal lateinit var idioma: Constantes.idioma

        internal var hecho_libre : Array<Boolean> = arrayOf(false, false, false, false, false, false, false)
    }
}