package com.txurdinaga.didaktikapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.txurdinaga.didaktikapp.databinding.LayoutActividadBinding
import com.txurdinaga.didaktikapp.databinding.LayoutDialogoBinding

class MainActividad : AppCompatActivity(){
    private lateinit var binding: LayoutActividadBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = LayoutActividadBinding.inflate(layoutInflater)
        setContentView(binding.root)



    }

}