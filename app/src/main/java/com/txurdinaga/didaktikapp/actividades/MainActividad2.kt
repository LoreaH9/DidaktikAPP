package com.txurdinaga.didaktikapp.actividades

import android.content.DialogInterface
import android.content.Intent
import android.content.res.ColorStateList
import android.graphics.Typeface
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import com.txurdinaga.didaktikapp.*
import com.txurdinaga.didaktikapp.databinding.DialogEnhorabuenaBinding
import com.txurdinaga.didaktikapp.databinding.FragmentActividad2Binding
import com.txurdinaga.didaktikapp.databinding.LayoutActividadBinding
import kotlin.concurrent.thread

class MainActividad2 : AppCompatActivity(){
    private lateinit var binding: LayoutActividadBinding
    private lateinit var binding2: FragmentActividad2Binding

    var set = 2

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = LayoutActividadBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.fondoIV.setImageResource(ActividadesProvider.actividad[set].fondo)
        binding.explicacionTV.text = getString(ActividadesProvider.actividad[set].explicacion)
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
            terminarActividad()
        }

        binding.saltarBT.setOnClickListener{
            terminarActividad()
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
                runOnUiThread{
                var comprobaketa : List<Boolean> = listOf(
                    comprobatuLetra(listA, "A"),
                    comprobatuLetra(listE, "E"),
                    comprobatuLetra(listI, "I"),
                    comprobatuLetra(listO, "O"),
                    comprobatuLetra(listU, "U") )
                    if (!comprobaketa.contains(false)) {
                        binding.terminarActividadBT.visibility = View.VISIBLE
                    }
                    else
                        binding.terminarActividadBT.visibility = View.INVISIBLE
                }
                Thread.sleep(1000)
            }
        }

    }

    fun terminarActividad() {
        AlertDialog.Builder(this)
            .setTitle("Actividad $set")
            .setMessage("${getString(ActividadesProvider.actividad[set].enhorabuena)}\n\n${getString(R.string.quequiereshacer)}")
            .setPositiveButton("Continuar",
                DialogInterface.OnClickListener { _, _ ->
                    if(SharedPrefs.puntopartida.Partida.toInt() < set && !SharedPrefs.modolibre.modo) {
                        SharedPrefs.puntopartida.Partida = "$set"
                    }
                    startActivity(Intent(this, MainMenu::class.java))
                })
            .setNegativeButton("Repetir",
                DialogInterface.OnClickListener { _, _ ->
                    startActivity(
                        Intent(this, MainContrasena::class.java)
                            .putExtra("set", set)
                    )
                })
            .create()
            .show()
    }

    fun comprobatuLetra(list: List<EditText>, letra: String) :Boolean{
        var ret = true
        var col: Int
        list.forEach {
            var str:String = "${it.text}".toUpperCase()
            if(str.toUpperCase() == letra){
                col = ContextCompat.getColor(this, R.color.verdeosc)
                it.isEnabled = false
                it.setTypeface(null, Typeface.BOLD)
                it.setText("${it.text}".toUpperCase())
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