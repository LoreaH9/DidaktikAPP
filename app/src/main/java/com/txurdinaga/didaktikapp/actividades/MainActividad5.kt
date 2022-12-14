package com.txurdinaga.didaktikapp.actividades

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Typeface
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import com.google.android.material.internal.ViewUtils.hideKeyboard
import com.txurdinaga.didaktikapp.*
import com.txurdinaga.didaktikapp.activities.MainContrasena
import com.txurdinaga.didaktikapp.activities.MainMenu
import com.txurdinaga.didaktikapp.databinding.FragmentActividad5Binding
import com.txurdinaga.didaktikapp.databinding.LayoutActividadBinding
import kotlin.concurrent.thread

class MainActividad5 : AppCompatActivity(){
    private lateinit var binding: LayoutActividadBinding
    private lateinit var binding5: FragmentActividad5Binding

    var set = 5

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        SharedPrefs.idioma.aldatu(SharedPrefs.idioma.idioma, resources)
        binding = LayoutActividadBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.fondoIV.setImageResource(ActividadesProvider.actividad[set].fondo)
        binding.explicacionTV.text = getString(ActividadesProvider.actividad[set].explicacion)
        binding.verBT.visibility = View.INVISIBLE

        binding5 = FragmentActividad5Binding.inflate(layoutInflater)
        binding.fragFL.addView(binding5.root)

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


        binding.verBT.visibility = View.VISIBLE
        binding.verBT.setOnClickListener{
            AlertDialog.Builder(this)
                .setView(layoutInflater.inflate(R.layout.fragment_actividad_5_2, null))
                .create()
                .show()
        }

        val tres : List<EditText> = listOf(
            binding5.tres, binding5.tres1, binding5.tres2, binding5.tres3)

        val monumental : List<EditText> = listOf(
            binding5.monumental, binding5.medio4, binding5.monumental2, binding5.monumental3,
            binding5.monumental4, binding5.monumental5, binding5.monumental6, binding5.monumental7,
            binding5.monumental8, binding5.monumental9)

        val medio : List<EditText> = listOf(
            binding5.medio, binding5.tres2, binding5.medio2, binding5.medio3, binding5.medio4)

        val chavarri : List<EditText> = listOf(
            binding5.chavarri, binding5.chavarri2, binding5.chavarri3, binding5.chavarri4,
            binding5.empinadas5, binding5.chavarri6, binding5.chavarri5, binding5.metalurgia8)

        val empinadas : List<EditText> = listOf(
            binding5.monumental5, binding5.empinadas1, binding5.empinadas2, binding5.empinadas3,
            binding5.empinadas4, binding5.empinadas5, binding5.empinadas6, binding5.empinadas7,
            binding5.empinadas8)

        val piedras : List<EditText> = listOf(
            binding5.piedras, binding5.piedras1, binding5.metalurgia1, binding5.piedras3,
            binding5.piedras4, binding5.piedras5, binding5.piedras6)

        val metalurgia : List<EditText> = listOf(
            binding5.metalurgia, binding5.metalurgia1, binding5.metalurgia2, binding5.monumental8,
            binding5.metalurgia4, binding5.metalurgia5, binding5.metalurgia6, binding5.metalurgia7,
            binding5.metalurgia8, binding5.metalurgia9)

        val respuestas : List<List<EditText>> = listOf(tres, monumental, medio, chavarri,
            empinadas, piedras, metalurgia)

        val solucciones: List<String> = listOf("tres", "monumental", "medio", "chavarri",
            "empinadas", "piedras", "metalurgia")

        thread {
            while(true) {
                runOnUiThread{
                    if (comprobar(respuestas, solucciones)) {
                        binding.terminarActividadBT.visibility = View.VISIBLE
                    }
                    else
                        binding.terminarActividadBT.visibility = View.INVISIBLE
                }
                Thread.sleep(1000)
            }
        }

        /*binding.terminarActividadBT.visibility = View.VISIBLE
        binding.terminarActividadBT.setOnClickListener{
            if(comprobar5(respuestas, solucciones)){
                binding.terminarActividadBT.visibility = View.VISIBLE
                Toast.makeText(applicationContext, "HAS FINALIZADO EL JUEGO", Toast.LENGTH_LONG).show()
            }
        }*/
    }

    fun terminarActividad() {
        AlertDialog.Builder(this)
            .setTitle("${getString(R.string.actividad)} $set")
            .setMessage("${getString(ActividadesProvider.actividad[set].enhorabuena)}")
            .setView(R.layout.dialog_enhorabuena)
            .setPositiveButton(getString(R.string.continuar)
            ) { _, _ ->
                if (SharedPrefs.puntopartida.partida.toInt() < set && !SharedPrefs.modolibre.modo) {
                    SharedPrefs.puntopartida.partida = "$set"
                }
                startActivity(Intent(this, MainMenu::class.java))
            }
            .setNegativeButton(getString(R.string.repetir)
            ) { _, _ ->
                startActivity(
                    Intent(this, MainContrasena::class.java)
                        .putExtra("set", set)
                )
            }
            .create()
            .show()
    }

    fun formarRespuesta(list:List<EditText>) : String{
        var str = ""
        for(i in list.indices) str += "${list[i].text}"
        return str
    }

    fun comprobar5(resp: List<List<EditText>>, solu: List<String>) : Boolean{
        var r = true
        for(i in resp.indices)
            if(formarRespuesta(resp[i]) != solu[i]) {
                r = false
            }
        return r
    }

    fun comprobar(resp: List<List<EditText>>, solu: List<String>) : Boolean {
        var r = true
        for(i in resp.indices) {
            var soluChar = solu[i].toCharArray()
            for(i2 in resp[i].indices) {
                if ("${resp[i][i2].text}".equals(soluChar[i2].toString(), ignoreCase = true)) {
                    resp[i][i2].isEnabled = false
                    resp[i][i2].setTypeface(null, Typeface.BOLD)
                    resp[i][i2].setText("${resp[i][i2].text}".toUpperCase())
                    resp[i][i2].setTextColor(getColor(R.color.verdeosc))
                } else if (resp[i][i2].isEnabled) {
                    resp[i][i2].setTextColor(getColor(R.color.rojo))
                    r = false
                }
            }
        }
        return r
    }
}