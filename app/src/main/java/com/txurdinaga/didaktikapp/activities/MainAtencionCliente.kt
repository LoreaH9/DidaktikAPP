package com.txurdinaga.didaktikapp.activities

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.txurdinaga.didaktikapp.Constantes.localizacion
import com.txurdinaga.didaktikapp.databinding.ActivityMainAtencionClienteBinding
import io.socket.client.IO


class MainAtencionCliente : AppCompatActivity() {
    lateinit var binding: ActivityMainAtencionClienteBinding
    private val mSocket = IO.socket("https://adorable-wobbly-plastic.glitch.me")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityMainAtencionClienteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.button.setOnClickListener {
            val message: String = binding.editText.text.toString()
            mSocket.emit("alarma", localizacion,message)
        }

        mSocket.on("alarma") { args ->
            if (args[0] != null) {
                val message = args[0]
                runOnUiThread {
                    Toast.makeText(this, "$message",Toast.LENGTH_LONG).show ()
                    binding.textView8.text =message.toString()

                }
            }
        }
        mSocket.connect();
    }
}