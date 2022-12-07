package com.txurdinaga.didaktikapp.actividades

import android.content.res.ColorStateList
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import com.txurdinaga.didaktikapp.ActividadesProvider
import com.txurdinaga.didaktikapp.R
import com.txurdinaga.didaktikapp.databinding.FragmentActividad2Binding
import com.txurdinaga.didaktikapp.databinding.LayoutActividadBinding
import kotlin.concurrent.thread

class MainActividad2 : AppCompatActivity(){
    private lateinit var binding: LayoutActividadBinding
    private lateinit var binding2: FragmentActividad2Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = LayoutActividadBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.fondoIV.setImageResource(ActividadesProvider.actividad[2].fondo)
        binding.explicacionTV.text = getString(ActividadesProvider.actividad[2].explicacion)
        binding.verBT.visibility = View.INVISIBLE

        binding2 = FragmentActividad2Binding.inflate(layoutInflater)
        binding.fragFL.addView(binding2.root)

        binding.ayudaBT.setOnClickListener{
            if (!binding.explicacionTV.isVisible){
                binding.explicacionTV.visibility = View.VISIBLE
                binding.ayudaBT.setImageResource(R.drawable.ic_x)
            } else {
                binding.explicacionTV.visibility = View.INVISIBLE
                binding.ayudaBT.setImageResource(R.drawable.ic_help)
            }
        }

        binding.verBT.visibility = View.VISIBLE
        binding.verBT.setOnClickListener{
            AlertDialog.Builder(this)
                .setView(layoutInflater.inflate(R.layout.fragment_actividad_2_2, null))
                .create()
                .show()
        }

        binding.terminarActividadBT.setOnClickListener{

        }

        var listA : List<EditText> = listOf(
            binding2.a1, binding2.a2, binding2.a3, binding2.a4, binding2.a5, binding2.a6, binding2.a7)
        var listE : List<EditText> = listOf(
            binding2.e1, binding2.e2, binding2.e3, binding2.e4, binding2.e5, binding2.e6, binding2.e7, binding2.e8)
        var listI : List<EditText> = listOf(
            binding2.i1, binding2.i2, binding2.i3, binding2.i4, binding2.i5, binding2.i6)
        var listO : List<EditText> = listOf(
            binding2.o1, binding2.o2, binding2.o3, binding2.o4, binding2.o5, binding2.o6, binding2.o7)
        var listU : List<EditText> = listOf(
            binding2.u1, binding2.u2, binding2.u3, binding2.u4)

        thread {
            while(true) {
                var comprobaketa : List<Boolean> = listOf(
                    comprobatuLetra(listA, "A"),
                    comprobatuLetra(listE, "E"),
                    comprobatuLetra(listI, "I"),
                    comprobatuLetra(listO, "O"),
                    comprobatuLetra(listU, "U") )
                runOnUiThread{
                    if (!comprobaketa.contains(false)) {
                        binding.terminarActividadBT.visibility = View.VISIBLE
                        Toast.makeText(applicationContext, "HAS FINALIZADO EL JUEGO", Toast.LENGTH_LONG).show()
                    }
                    else
                        binding.terminarActividadBT.visibility = View.INVISIBLE
                }
                Thread.sleep(1000)
            }
        }

    }

    fun comprobatuLetra(list: List<EditText>, letra: String) :Boolean{
        var ret = true
        var col: Int
        list.forEach {
            var str:String = "${it.text}".toUpperCase()
            if(str.toUpperCase() == letra){
                col = ContextCompat.getColor(this, R.color.verdecla)
            }
            else if(str == ""){
                col = ContextCompat.getColor(this, R.color.negro)
                ret = false
            }
            else {
                col = ContextCompat.getColor(this, R.color.rojo)
                ret = false
            }
            it.backgroundTintList = ColorStateList.valueOf(col)
            it.setTextColor(col)
        }
        return ret
    }



}