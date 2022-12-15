package com.txurdinaga.didaktikapp.actividades

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import com.txurdinaga.didaktikapp.*
import com.txurdinaga.didaktikapp.activities.MainContrasena
import com.txurdinaga.didaktikapp.activities.MainMenu
import com.txurdinaga.didaktikapp.databinding.FragmentActividad6Binding
import com.txurdinaga.didaktikapp.databinding.LayoutActividadBinding

class MainActividad6 : AppCompatActivity(){
    private lateinit var binding: LayoutActividadBinding
    private lateinit var binding6: FragmentActividad6Binding

    var set = 6

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = LayoutActividadBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.fondoIV.setImageResource(ActividadesProvider.actividad[set].fondo)
        binding.explicacionTV.text = getString(ActividadesProvider.actividad[set].explicacion)
        binding.verBT.visibility = View.INVISIBLE

        binding6 = FragmentActividad6Binding.inflate(layoutInflater)
        binding.fragFL.addView(binding6.root)

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
            if (SharedPrefs.modolibre.modo)
                SharedPrefs.hecho_libre[set-1] = true
            terminarActividad()
        }

        binding.saltarBT.setOnClickListener{
            if (SharedPrefs.modolibre.modo)
                SharedPrefs.hecho_libre[set-1] = true
            terminarActividad()
        }

    }

    fun terminarActividad() {
        AlertDialog.Builder(this)
            .setTitle("Actividad $set")
            .setMessage("${getString(ActividadesProvider.actividad[set].enhorabuena)}\n\n${getString(R.string.quequiereshacer)}")
            .setPositiveButton("Continuar"
            ) { _, _ ->
                if (SharedPrefs.puntopartida.partida.toInt() < set && !SharedPrefs.modolibre.modo) {
                    SharedPrefs.puntopartida.partida = "$set"
                }
                startActivity(Intent(this, MainMenu::class.java))
            }
            .setNegativeButton("Repetir"
            ) { _, _ ->
                startActivity(
                    Intent(this, MainContrasena::class.java)
                        .putExtra("set", set)
                )
            }
            .create()
            .show()
    }
}