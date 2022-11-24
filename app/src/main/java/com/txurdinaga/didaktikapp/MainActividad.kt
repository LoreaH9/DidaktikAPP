package com.txurdinaga.didaktikapp

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import com.txurdinaga.didaktikapp.databinding.LayoutActividadBinding

class MainActividad : AppCompatActivity(), Actividades{
    private lateinit var binding: LayoutActividadBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = LayoutActividadBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var set = intent?.getIntExtra("set", 0) ?: throw IllegalStateException()
        setActividad(set)

        binding.ayudaBT.setOnClickListener{
            if (!binding.explicacionTV.isVisible){
                binding.explicacionTV.visibility = View.VISIBLE
                binding.ayudaBT.setImageDrawable(getDrawable(R.drawable.ic_x))
            } else {
                binding.explicacionTV.visibility = View.INVISIBLE
                binding.ayudaBT.setImageDrawable(getDrawable(R.drawable.ic_help))
            }
        }
    }

    private fun setActividad(set: Int){
        binding.fondoIV.setImageDrawable(getDrawable(Params.getFondo(set)))
        binding.explicacionTV.text = getString(Params.getExplicacion(set))
        when(set){
            1 -> run1(binding, layoutInflater)
            2 -> run2(binding, layoutInflater)
            //2 -> run2(binding, layoutInflater, applicationContext)
        }
    }
}