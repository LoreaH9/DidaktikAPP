package com.txurdinaga.didaktikapp

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
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

        binding.infoModos.setOnClickListener {
           val intent= Intent(this,MainInfoModos::class.java)
            startActivity(intent)
        }

        binding.btLibre.setOnClickListener {
            val intent= Intent(this,MainMapa::class.java)
            startActivity(intent)
        }
    }
}