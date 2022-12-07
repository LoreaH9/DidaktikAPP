package com.txurdinaga.didaktikapp.actividades

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import com.txurdinaga.didaktikapp.ActividadesProvider
import com.txurdinaga.didaktikapp.R
import com.txurdinaga.didaktikapp.databinding.FragmentActividad7Binding
import com.txurdinaga.didaktikapp.databinding.LayoutActividadBinding

class MainActividad7 : AppCompatActivity(){
    private lateinit var binding: LayoutActividadBinding
    private lateinit var binding7: FragmentActividad7Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = LayoutActividadBinding.inflate(layoutInflater)
        binding7 = FragmentActividad7Binding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.fondoIV.setImageResource(ActividadesProvider.actividad[7].fondo)
        binding.explicacionTV.text = getString(ActividadesProvider.actividad[7].explicacion)
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