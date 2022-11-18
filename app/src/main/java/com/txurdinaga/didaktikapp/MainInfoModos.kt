package com.txurdinaga.didaktikapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.txurdinaga.didaktikapp.databinding.LayoutInfoModosBinding

class MainInfoModos : AppCompatActivity(){
    private lateinit var binding: LayoutInfoModosBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = LayoutInfoModosBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}