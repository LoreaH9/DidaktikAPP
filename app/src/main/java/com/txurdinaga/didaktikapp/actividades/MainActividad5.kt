package com.txurdinaga.didaktikapp.actividades

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import com.txurdinaga.didaktikapp.ActividadesProvider
import com.txurdinaga.didaktikapp.R
import com.txurdinaga.didaktikapp.databinding.FragmentActividad5Binding
import com.txurdinaga.didaktikapp.databinding.LayoutActividadBinding

class MainActividad5 : AppCompatActivity(){
    private lateinit var binding: LayoutActividadBinding
    private lateinit var binding5: FragmentActividad5Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = LayoutActividadBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.fondoIV.setImageResource(ActividadesProvider.actividad[5].fondo)
        binding.explicacionTV.text = getString(ActividadesProvider.actividad[5].explicacion)
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

        binding.terminarActividadBT.visibility = View.VISIBLE
        binding.terminarActividadBT.setOnClickListener{
            if(comprobar5(respuestas, solucciones)){
                finish()
            }
        }
    }

    fun formarRespuesta(list:List<EditText>) : String{
        var str : String = ""
        for(i in list.indices) str += "${list[i].text}"
        return str
    }

    fun comprobar5(resp: List<List<EditText>>, solu: List<String>) : Boolean{
        var r = true
        for(i in resp.indices)
            if(formarRespuesta(resp[i]) != solu[i])
                r = false
        return r
    }
}