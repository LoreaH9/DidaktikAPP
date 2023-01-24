package com.txurdinaga.didaktikapp

import android.app.Service
import android.content.Intent
import android.os.Handler
import android.os.IBinder
import android.os.Looper
import android.widget.Toast
import com.google.gson.Gson


class ServicioAlerta : Service() {

    override fun onBind(intent: Intent): IBinder {
        TODO("Return the communication channel to the service.")
    }
    override fun onCreate() {
        Constantes.mSocket.on("alarma") { args ->
            if (args[0] != null) {
                val message = Gson().toJson(args[0])
                Handler(Looper.getMainLooper()).post {
                    Toast.makeText(this, "$message", Toast.LENGTH_LONG).show ()
                }
            }
        }
        super.onCreate()
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        Constantes.mSocket.emit("alarma", Constantes.localizacion,"inicio")
        Constantes.mSocket.connect();
        return super.onStartCommand(intent, flags, startId)
    }

    override fun onDestroy() {

        super.onDestroy()
    }
}