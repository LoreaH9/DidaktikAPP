package com.txurdinaga.didaktikapp.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.txurdinaga.didaktikapp.SharedPrefs
import com.txurdinaga.didaktikapp.databinding.LayoutInfoModosBinding

class MainInfoModos : AppCompatActivity(){
    private lateinit var binding: LayoutInfoModosBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        SharedPrefs.idioma.aldatu(SharedPrefs.idioma.idioma, resources)
        binding = LayoutInfoModosBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}