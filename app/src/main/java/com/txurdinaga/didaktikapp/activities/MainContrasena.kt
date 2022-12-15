package com.txurdinaga.didaktikapp.activities

import android.content.Intent
import android.os.Bundle
import android.text.InputType.TYPE_CLASS_NUMBER
import android.text.InputType.TYPE_CLASS_TEXT
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.txurdinaga.didaktikapp.ActividadesProvider
import com.txurdinaga.didaktikapp.databinding.DialogContrasenaBinding
import com.txurdinaga.didaktikapp.databinding.FragmentPistaBinding
import com.txurdinaga.didaktikapp.databinding.LayoutContrasenaBinding

class MainContrasena : AppCompatActivity() {
    private lateinit var binding: LayoutContrasenaBinding
    private lateinit var binding2: FragmentPistaBinding
    private lateinit var binding3: DialogContrasenaBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = LayoutContrasenaBinding.inflate(layoutInflater)
        binding2 = FragmentPistaBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var set = intent?.getIntExtra("set", 0) ?: throw IllegalStateException()
        setEscenario(set)

        binding.t1BT.setOnClickListener{
            showPista(set,1)
        }
        binding.t2BT.setOnClickListener{
            showPista(set, 2)
        }
        binding.t3BT.setOnClickListener{
            showPista(set,3)
        }
        binding.actividadBT.setOnClickListener{
            showDialogContrasena(set)
        }
        binding2.aceptarBT.setOnClickListener{
            hidePista()
        }
    }

    private fun setEscenario(set: Int){
        val params1 = (binding.t1BT.layoutParams as ViewGroup.MarginLayoutParams)
        val params2 = (binding.t2BT.layoutParams as ViewGroup.MarginLayoutParams)
        val params3 = (binding.t3BT.layoutParams as ViewGroup.MarginLayoutParams)
        var margins = listOf(1, 1, 1, 1, 1, 1)
        when(set){            // 1TOP LFT 2BOT LFT 3TOP RGHT
            1 -> margins = listOf(60, 120, 40, 300, 140, 80)
            2 -> margins = listOf(40, 100, 40, 200, 140, 60)
            3 -> margins = listOf(220, 60, 240, 200, 80, 80)
            4 -> margins = listOf(240, 40, 220, 140, 120, 80)
            5 -> margins = listOf(240, 40, 220, 120, 160, 80)
            6 -> margins = listOf(120, 60, 40, 260, 60, 100)
            7 -> margins = listOf(240, 60, 140, 200, 60, 80)
            else -> finish()
        }
        params1.topMargin = margins[0] *3
        params1.leftMargin = margins[1] *3
        binding.t1BT.layoutParams = params1

        params2.bottomMargin = margins[2] *3
        params2.leftMargin = margins[3] *3
        binding.t2BT.layoutParams = params2

        params3.topMargin = margins[4] *3
        params3.rightMargin = margins[5] *3
        binding.t3BT.layoutParams = params3

        binding.fondoIV.setImageResource(ActividadesProvider.actividad[set].fondo)
        binding2.fondoPistaIV.setImageResource(ActividadesProvider.actividad[set].fondo)
        binding2.personajeIV.setImageResource(ActividadesProvider.actividad[set].perso)
    }

    private fun showPista(set: Int, pista: Int){
        setContentView(binding2.root)
        binding2.pistaTV.text = getString(ActividadesProvider.actividad[set].pistas[pista-1])
    }

    private fun hidePista(){
        setContentView(binding.root)
    }

    private fun showDialogContrasena(set: Int){
        binding3 = DialogContrasenaBinding.inflate(layoutInflater)
        binding3.enunciadoTV.text = getString(ActividadesProvider.actividad[set].enunciado)
        if(Regex("\\d+").matches(getString(ActividadesProvider.actividad[set].contrasena))){
            binding3.contrasenaET.inputType = TYPE_CLASS_NUMBER
        } else {
            binding3.contrasenaET.inputType = TYPE_CLASS_TEXT
        }
        binding3.continuarBT.setOnClickListener{
            var contrasena : String = binding3.contrasenaET.text.toString()
            if (contrasena.toLowerCase() == getString(ActividadesProvider.actividad[set].contrasena)){
                if(set == 2 || set == 5 || set == 7){
                    startActivity(Intent(this, ActividadesProvider.actividad[set].main)
                        .putExtra("set", set)
                    )
                } else {
                    startActivity(Intent(this, MainMenu::class.java))
                }
            }
        }
        AlertDialog.Builder(this)
            .setView(binding3.root)
            .create()
            .show()
    }



}