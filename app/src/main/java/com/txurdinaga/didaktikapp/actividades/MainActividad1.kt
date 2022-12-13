package com.txurdinaga.didaktikapp.actividades

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import com.txurdinaga.didaktikapp.ActividadesProvider
import com.txurdinaga.didaktikapp.R
import com.txurdinaga.didaktikapp.databinding.FragmentActividad1Binding
import com.txurdinaga.didaktikapp.databinding.FragmentActividad2Binding
import com.txurdinaga.didaktikapp.databinding.LayoutActividadBinding

class MainActividad1 : AppCompatActivity(){
    private lateinit var binding: LayoutActividadBinding
    private lateinit var binding1: FragmentActividad1Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = LayoutActividadBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.fondoIV.setImageResource(ActividadesProvider.actividad[1].fondo)
        binding.explicacionTV.text = getString(ActividadesProvider.actividad[1].explicacion)
        binding.verBT.visibility = View.INVISIBLE

        binding1 = FragmentActividad1Binding.inflate(layoutInflater)
        binding.fragFL.addView(binding1.root)

        binding.ayudaBT.setOnClickListener{
            if (!binding.explicacionTV.isVisible){
                binding.explicacionTV.visibility = View.VISIBLE
                binding.ayudaBT.setImageResource(R.drawable.ic_x)
            } else {
                binding.explicacionTV.visibility = View.INVISIBLE
                binding.ayudaBT.setImageResource(R.drawable.ic_help)
            }
        }

    }
}