package com.txurdinaga.didaktikapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.txurdinaga.didaktikapp.databinding.FragmentInformacionBinding

class FragmentInformacion: AppCompatActivity() {
    lateinit var binding: FragmentInformacionBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= FragmentInformacionBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

}