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
        super.onCreate(savedInstanceState)
        binding = LayoutInicioBinding.inflate(layoutInflater)
        binding2 = LayoutInfoModosBinding.inflate(layoutInflater)

        setContentView(binding.root)

        SharesPrefs.modolibre.modo = false

        binding.infoModos.setOnClickListener {
            val intent= Intent(this,MainInfoModos::class.java)
            startActivity(intent)
        }

        binding.btLibre.setOnClickListener {
            SharesPrefs.modolibre.modo = true
            val intent= Intent(this,MainMenu::class.java)
            startActivity(intent)
        }

        binding.btGuiado.setOnClickListener {
            SharesPrefs.modolibre.modo = true
            val intent= Intent(this,MainMenu::class.java)
            startActivity(intent)
        }
    }
}