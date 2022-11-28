package com.txurdinaga.didaktikapp.actividades

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import com.txurdinaga.didaktikapp.ActividadesProvider
import com.txurdinaga.didaktikapp.R
import com.txurdinaga.didaktikapp.databinding.FragmentActividad6Binding
import com.txurdinaga.didaktikapp.databinding.LayoutActividadBinding

class MainActividad6 : AppCompatActivity(){
    private lateinit var binding: LayoutActividadBinding
    private lateinit var binding6: FragmentActividad6Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = LayoutActividadBinding.inflate(layoutInflater)
        binding6 = FragmentActividad6Binding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.fondoIV.setImageResource(ActividadesProvider.actividad[6].fondo)
        binding.explicacionTV.text = getString(ActividadesProvider.actividad[6].explicacion)
        binding.zoomBT.visibility = View.INVISIBLE

        binding.containerVW.layoutResource = R.layout.fragment_actividad_1
        binding.containerVW.inflate()

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