package com.txurdinaga.didaktikapp

import android.os.Bundle
import android.text.Layout
import android.view.View
import android.widget.ImageButton
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import com.txurdinaga.didaktikapp.databinding.FragmentActividad2Binding
import com.txurdinaga.didaktikapp.databinding.LayoutActividadBinding

class MainActividad : AppCompatActivity(){
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

        binding.zoomBT.setOnClickListener{
            if (set==2){
                AlertDialog.Builder(this)
                    .setView(layoutInflater.inflate(R.layout.fragment_actividad_2_2, null))
                    .create()
                    .show()
            }
        }
    }

    private fun setActividad(set: Int){
        binding.fondoIV.setImageDrawable(getDrawable(Params.getFondo(set)))
        binding.explicacionTV.text = getString(Params.getExplicacion(set))
        binding.zoomBT.visibility = View.INVISIBLE
        var layout: Int
        when(set){
            1 -> layout = R.layout.fragment_actividad_1
            2 -> {
                binding.zoomBT.visibility = View.VISIBLE
                layout = R.layout.fragment_actividad_2
            }
            3 -> layout = R.layout.fragment_actividad_3
            4 -> layout = R.layout.fragment_actividad_4
            5 -> layout = R.layout.fragment_actividad_5
            6 -> layout = R.layout.fragment_actividad_6
            7 -> layout = R.layout.fragment_actividad_7
            else -> layout = 0
        }
        if(layout!=0) binding.containerVW.layoutResource = layout
        binding.containerVW.inflate()
    }
}