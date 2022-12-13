package com.txurdinaga.didaktikapp

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.os.Handler
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import com.txurdinaga.didaktikapp.databinding.LayoutInfoModosBinding
import com.txurdinaga.didaktikapp.databinding.LayoutInicioBinding

class MainInicio : AppCompatActivity(){
    private lateinit var binding: LayoutInicioBinding
    private lateinit var binding2: LayoutInfoModosBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        Thread.sleep(1000)
        setTheme(R.style.Theme_DidaktikAPP)
        super.onCreate(savedInstanceState)
        binding = LayoutInicioBinding.inflate(layoutInflater)
        binding2 = LayoutInfoModosBinding.inflate(layoutInflater)

        setContentView(binding.root)
        if (SharedPrefs.idioma.idioma==null){
            SharedPrefs.idioma.idioma="es"
        }
        SharedPrefs.modolibre.modo = false
        //Comprueba los permisos de navegación
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED)
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),1)

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
            val intent= Intent(this,MainMenu::class.java)
            startActivity(intent)
        }
    }
}