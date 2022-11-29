package com.txurdinaga.didaktikapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.core.view.isVisible
import com.txurdinaga.didaktikapp.databinding.DialogAudioBinding
import com.txurdinaga.didaktikapp.databinding.LayoutDialogoBinding

class MainDialogo : AppCompatActivity() {
    private lateinit var binding: LayoutDialogoBinding
    private lateinit var audioController: DialogAudioBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = LayoutDialogoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var set = 2

        var line = 1
        setDialogo(set, 0)

        binding.viewBT.setOnClickListener{
            setDialogo(set, line)
            line++
        }

        binding.audioBT.setOnClickListener{
            AlertDialog.Builder(this)
                .setView(layoutInflater.inflate(R.layout.dialog_audio, null))
                .create()
                .show()
        }

        binding.siguienteBT.setOnClickListener{
            startActivity(Intent(this, MainContrasena::class.java)
                .putExtra("set", set)
            )
        }


        audioController = DialogAudioBinding.inflate(layoutInflater)
        audioController.rewindBT.setOnClickListener{

        }
        audioController.playPauseBT.setOnClickListener{

        }
        audioController.volumeBT.setOnClickListener{
            audioController.volumenSB.visibility = View.VISIBLE
        }
        //audioController.volumenSB.setOnSeekBarChangeListener()

    }

    fun setDialogo(set:Int, line: Int){
        if(line==0){
            binding.fondoIV.setImageResource(ActividadesProvider.actividad[set].fondo)
            binding.personaje2IV.setImageResource(ActividadesProvider.actividad[set].perso)
            binding.textoTV.text = ""
            binding.bocadilloIV.setImageResource(R.drawable.vacio)
            binding.personaje1IV.setImageResource(R.drawable.ali_1)
            binding.siguienteBT.visibility = View.INVISIBLE
        } else if(line < ActividadesProvider.actividad[set].dialogo.size){
            binding.textoTV.text = getString(ActividadesProvider.actividad[set].dialogo[line].texto)
            binding.bocadilloIV.setImageResource(ActividadesProvider.actividad[set].dialogo[line].bocadillo)
            binding.personaje1IV.setImageResource(ActividadesProvider.actividad[set].dialogo[line].ali)
        } else {
            binding.textoTV.text = ""
            binding.bocadilloIV.setImageResource(R.drawable.vacio)
            binding.personaje1IV.setImageResource(R.drawable.ali_1)
            binding.siguienteBT.visibility = View.VISIBLE
        }
    }

}