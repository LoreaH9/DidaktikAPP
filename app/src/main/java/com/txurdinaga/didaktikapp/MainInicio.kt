package com.txurdinaga.didaktikapp

import android.Manifest
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContentProviderCompat.requireContext
import com.txurdinaga.didaktikapp.databinding.DialogNombreBinding
import com.txurdinaga.didaktikapp.databinding.LayoutInfoModosBinding
import com.txurdinaga.didaktikapp.databinding.LayoutInicioBinding

class MainInicio : AppCompatActivity(){
    private lateinit var binding: LayoutInicioBinding
    private lateinit var binding2: LayoutInfoModosBinding
    private lateinit var nombre: String
    override fun onCreate(savedInstanceState: Bundle?) {
        Thread.sleep(1000)
        setTheme(R.style.Theme_DidaktikAPP)
        super.onCreate(savedInstanceState)
        binding = LayoutInicioBinding.inflate(layoutInflater)

        setContentView(binding.root)
        if (SharedPrefs.idioma.idioma==null){
            SharedPrefs.idioma.idioma="es"
        }
        SharedPrefs.modolibre.modo = false
        //Comprueba los permisos de navegación
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED)
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),1)


        binding2 = LayoutInfoModosBinding.inflate(layoutInflater)

        //DialogNombre().show(supportFragmentManager, "LoginDialog")

        binding.infoModos.setOnClickListener {
            val intent= Intent(this,MainInfoModos::class.java)
            startActivity(intent)
        }

        binding.btLibre.setOnClickListener {
            SharedPrefs.modolibre.modo = true
            val intent= Intent(this,MainMenu::class.java)
            startActivity(intent)

        }

        binding.btGuiado.setOnClickListener {
            SharedPrefs.modolibre.modo = false
            if(SharedPrefs.puntopartida.partida != "0"){
                AlertDialog.Builder(this)
                    .setTitle("Datos guardados")
                    .setMessage("¿Quieres continuar la partida guardada?\nProgreso: ${SharedPrefs.puntopartida.partida}")
                    .setPositiveButton("Continuar",
                        DialogInterface.OnClickListener { _, _ ->
                            startActivity(Intent(this,MainMenu::class.java))
                        })
                    .setNegativeButton("Nueva partida",
                        DialogInterface.OnClickListener { _, _ ->
                            SharedPrefs.puntopartida.partida = "0"
                            startActivity(Intent(this, MainDialogo::class.java)
                                .putExtra("set", 0))
                        })
                    .create()
                    .show()
            } else {
                startActivity(Intent(this, MainDialogo::class.java)
                    .putExtra("set", 0))
            }
        }
    }
}