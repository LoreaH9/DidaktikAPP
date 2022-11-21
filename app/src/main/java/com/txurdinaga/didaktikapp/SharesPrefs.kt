package com.txurdinaga.didaktikapp
import android.app.Application

class SharesPrefs : Application(){
    companion object {

        internal lateinit var users: Constantes.User
        internal lateinit var tipousu: Constantes.TipoUsu
        internal lateinit var puntopartida: Constantes.PuntoPartida
        internal lateinit var modolibre: Constantes.ModoLibre

    }

    override fun onCreate() {
        super.onCreate()

        users = Constantes.User(applicationContext)
        tipousu = Constantes.TipoUsu(applicationContext)
        puntopartida = Constantes.PuntoPartida(applicationContext)
        modolibre = Constantes.ModoLibre(applicationContext)

    }
}