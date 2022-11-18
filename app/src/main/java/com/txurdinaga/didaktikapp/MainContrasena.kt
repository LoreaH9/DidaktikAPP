package com.txurdinaga.didaktikapp

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.txurdinaga.didaktikapp.databinding.LayoutContrasenaBinding

class MainContrasena : AppCompatActivity() {
    private lateinit var binding: LayoutContrasenaBinding
    var textos: MutableList<Any> = mutableListOf()/*
    0->Fondo
    1->Personas
    2-4->Textos
    5->Enunciado
    6->Contraseña
    7->Actividad
    8->Enhorabuena (2,4,5,6,7)
    9-11->Opciones (4)
    */


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = LayoutContrasenaBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var set = intent?.getIntExtra("set", 0) ?: throw IllegalStateException()
        setEscenario(set)

        binding.t1BT.setOnClickListener{
            showPista()
        }
        binding.t2BT.setOnClickListener{
            showPista()
        }
        binding.t3BT.setOnClickListener{
            showPista()
        }
        binding.aceptarBT.setOnClickListener{
            hidePista()
        }
    }

    private fun setEscenario(set: Int){
        val params1 = (binding.t1BT.layoutParams as ViewGroup.MarginLayoutParams)
        val params2 = (binding.t2BT.layoutParams as ViewGroup.MarginLayoutParams)
        val params3 = (binding.t3BT.layoutParams as ViewGroup.MarginLayoutParams)
        when(set){
            1 -> {
                binding.fondoIV.setImageResource(R.drawable.fondo_1)
                params1.topMargin = 60 *3
                params1.leftMargin = 120 *3
                params2.bottomMargin = 40 *3
                params2.leftMargin = 300 *3
                params3.topMargin = 140 *3
                params3.rightMargin = 80 *3
                textos.add(getString(R.string.pista1_1))
                textos.add(getString(R.string.pista1_1))
                textos.add(getString(R.string.pista1_2))
                textos.add(getString(R.string.pista1_3))
                textos.add(getString(R.string.enunciado1))
                textos.add(getString(R.string.contrasena1))
            }
            else -> finish()
        }
        binding.t1BT.layoutParams = params1
        binding.t2BT.layoutParams = params2
        binding.t3BT.layoutParams = params3
    }

    private fun showPista(){
        binding.pistaTV.visibility = View.VISIBLE
        binding.aceptarBT.visibility = View.VISIBLE
        binding.personajeIV.visibility = View.VISIBLE
        binding.t1BT.isClickable = false
        binding.t2BT.isClickable = false
        binding.t3BT.isClickable = false
        binding.actividadBT.isClickable = false
    }

    private fun hidePista(){
        binding.pistaTV.visibility = View.INVISIBLE
        binding.aceptarBT.visibility = View.INVISIBLE
        binding.personajeIV.visibility = View.INVISIBLE
        binding.t1BT.isClickable = true
        binding.t2BT.isClickable = true
        binding.t3BT.isClickable = true
        binding.actividadBT.isClickable = true
    }

    private fun setPista(textos: List<Any>){

    }
}