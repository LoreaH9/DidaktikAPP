package com.txurdinaga.didaktikapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainDialogo : AppCompatActivity() {
    private lateinit var binding: LayoutDialogoBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = LayoutDialogoBinding.inflate(layoutInflater)
        setContentView(binding.root)

    }
}