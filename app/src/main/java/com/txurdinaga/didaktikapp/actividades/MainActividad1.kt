package com.txurdinaga.didaktikapp.actividades

import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import com.txurdinaga.didaktikapp.*
import com.txurdinaga.didaktikapp.databinding.FragmentActividad1Binding
import com.txurdinaga.didaktikapp.databinding.FragmentActividad2Binding
import com.txurdinaga.didaktikapp.databinding.LayoutActividadBinding

class MainActividad1 : AppCompatActivity(){
    private lateinit var binding: LayoutActividadBinding
    private lateinit var binding1: FragmentActividad1Binding

    var set = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = LayoutActividadBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.fondoIV.setImageResource(ActividadesProvider.actividad[set].fondo)
        binding.explicacionTV.text = getString(ActividadesProvider.actividad[set].explicacion)
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

        binding.terminarActividadBT.setOnClickListener{
            terminarActividad()
        }

        binding.saltarBT.setOnClickListener{
            terminarActividad()
        }

    }

    fun terminarActividad() {
        AlertDialog.Builder(this)
            .setTitle("Actividad $set")
            .setMessage("${getString(ActividadesProvider.actividad[set].enhorabuena)}\n\n${getString(R.string.quequiereshacer)}")
            .setPositiveButton("Continuar",
                DialogInterface.OnClickListener { _, _ ->
                    if(SharedPrefs.puntopartida.partida.toInt() < set && !SharedPrefs.modolibre.modo) {
                        SharedPrefs.puntopartida.partida = "$set"
                    }
                    startActivity(Intent(this, MainMenu::class.java))
                })
            .setNegativeButton("Repetir",
                { _, _ ->
                    startActivity(
                        Intent(this, MainContrasena::class.java)
                            .putExtra("set", set)
                    )
                })
            .create()
            .show()
    }
}