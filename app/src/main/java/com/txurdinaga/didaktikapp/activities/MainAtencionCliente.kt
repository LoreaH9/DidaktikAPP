package com.txurdinaga.didaktikapp.activities

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.txurdinaga.didaktikapp.databinding.ActivityMainAtencionClienteBinding
import com.txurdinaga.didaktikapp.socket.SocketHandler



class MainAtencionCliente : AppCompatActivity() {

    lateinit var binding: ActivityMainAtencionClienteBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityMainAtencionClienteBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val mSocket = SocketHandler.getSocket()
        mSocket.emit("eventName", "a")
        mSocket.on("eventName") { args ->
            if (args[0] != null) {
                val counter = args[0] as Int
                Log.i("I",counter.toString())
                runOnUiThread {
                    // The is where you execute the actions after you receive the data
                }
            }}
        // The following lines connects the Android app to the server.
        //SocketHandler.setSocket()
        //SocketHandler.establishConnection()
        // The follwing line disconnects the Android app to the server.
        //SocketHandler.closeConnection()

    }
}