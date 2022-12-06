package com.txurdinaga.didaktikapp.actividades

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import com.txurdinaga.didaktikapp.ActividadesProvider
import com.txurdinaga.didaktikapp.R
import com.txurdinaga.didaktikapp.databinding.FragmentActividad2Binding
import com.txurdinaga.didaktikapp.databinding.FragmentActividad4Binding
import com.txurdinaga.didaktikapp.databinding.LayoutActividadBinding

class MainActividad4 : AppCompatActivity(){
    private lateinit var binding: LayoutActividadBinding
    private lateinit var binding4: FragmentActividad4Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = LayoutActividadBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.fondoIV.setImageResource(ActividadesProvider.actividad[4].fondo)
        binding.explicacionTV.text = getString(ActividadesProvider.actividad[4].explicacion)
        binding.verBT.visibility = View.INVISIBLE

        binding4 = FragmentActividad4Binding.inflate(layoutInflater)
        binding.fragFL.addView(binding4.root)

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