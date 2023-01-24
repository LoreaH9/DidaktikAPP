package com.txurdinaga.didaktikapp.activities

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NotificationCompat
import com.google.gson.Gson
import com.txurdinaga.didaktikapp.Constantes.localizacion
import com.txurdinaga.didaktikapp.Constantes.mSocket
import com.txurdinaga.didaktikapp.databinding.ActivityMainAtencionClienteBinding

class MainAtencionCliente : AppCompatActivity() {
    lateinit var binding: ActivityMainAtencionClienteBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityMainAtencionClienteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.button.setOnClickListener {
            val message: String = binding.editText.text.toString()
            mSocket.emit("alarma", localizacion,message)
        }

        mSocket.on("alarma") { args ->

            if (args[0] != null && args[1] != null) {
                val localizacion = args[0]
                val message = args[1]
                runOnUiThread {
                    Toast.makeText(this, "$message",Toast.LENGTH_LONG).show ()
                    binding.textView8.text =localizacion.toString()
                }
            }
        }
    }
}


