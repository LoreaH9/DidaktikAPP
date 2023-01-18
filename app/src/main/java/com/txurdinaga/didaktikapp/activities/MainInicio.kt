package com.txurdinaga.didaktikapp.activities

import android.Manifest
import android.content.DialogInterface
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import com.txurdinaga.didaktikapp.R
import com.txurdinaga.didaktikapp.SharedPrefs
import com.txurdinaga.didaktikapp.databinding.LayoutInfoModosBinding
import com.txurdinaga.didaktikapp.databinding.LayoutInicioBinding

class MainInicio : AppCompatActivity(){
    private lateinit var binding: LayoutInicioBinding
    private lateinit var binding2: LayoutInfoModosBinding
    private lateinit var nombre: String
    override fun onCreate(savedInstanceState: Bundle?) {
        Thread.sleep(1000)
        setTheme(R.style.Theme_DidaktikAPP)

        SharedPrefs.idioma.aldatu(SharedPrefs.idioma.idioma, resources)
        super.onCreate(savedInstanceState)
        binding = LayoutInicioBinding.inflate(layoutInflater)

        setContentView(binding.root)
        SharedPrefs.modolibre.modo = false
        //Comprueba los permisos de navegación
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED)
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),1)


        binding2 = LayoutInfoModosBinding.inflate(layoutInflater)

        //DialogNombre().show(supportFragmentManager, "LoginDialog")

        binding.infoModos.setOnClickListener {
            val intent= Intent(this, MainInfoModos::class.java)
            startActivity(intent)
        }

        binding.btLibre.setOnClickListener {
            SharedPrefs.modolibre.modo = true
            val intent= Intent(this, MainMenu::class.java)
            startActivity(intent)

        }

        binding.btGuiado.setOnClickListener {
            SharedPrefs.modolibre.modo = false
            if(SharedPrefs.puntopartida.partida != "0"){
                AlertDialog.Builder(this)
                    .setTitle(R.string.datos_guardados)
                    .setMessage("${getString(R.string.continuar_partida_guardad)}\n${getString(R.string.progreso)}: ${SharedPrefs.puntopartida.partida}")
                    .setPositiveButton(R.string.continuar,
                        DialogInterface.OnClickListener { _, _ ->
                            startActivity(Intent(this, MainMenu::class.java))
                        })
                    .setNegativeButton(R.string.nueva_partida,
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