package com.txurdinaga.didaktikapp

import android.content.Intent
import android.media.MediaPlayer
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.txurdinaga.didaktikapp.databinding.DialogAudioBinding
import com.txurdinaga.didaktikapp.databinding.LayoutDialogoBinding

class MainDialogo : AppCompatActivity(), AudioController {
    private lateinit var binding: LayoutDialogoBinding
    private lateinit var audio_binding: DialogAudioBinding
    private lateinit var audioPlayer :MediaPlayer
    var volumeLevel: Float = 0.6f

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = LayoutDialogoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        audioPlayer = MediaPlayer.create(this, R.raw.audio_0_1)
        audioPlayer.play()

        var set = 0

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


        audio_binding = DialogAudioBinding.inflate(layoutInflater)
        audio_binding.rewindBT.setOnClickListener{
            audioPlayer.rewind()
        }
        audio_binding.playPauseBT.setOnClickListener{
            if(!audioPlayer.isPlaying){
                audioPlayer.play()
                //audio_binding.playPauseBT.setImageResource(R.drawable.pause)
            } else {
                audioPlayer.pause()
                //audio_binding.playPauseBT.setImageResource(R.drawable.play)
            }
        }
        audio_binding.volumeBT.setOnClickListener{
            when(volumeLevel){
                0f -> {
                    volumeLevel = 0.3f
                    //audio_binding.playPauseBT.setImageResource(R.drawable.vol_1)
                }
                0.3f -> {
                    volumeLevel = 0.6f
                    //audio_binding.playPauseBT.setImageResource(R.drawable.vol_2)
                }
                0.6f -> {
                    volumeLevel = 0.8f
                    //audio_binding.playPauseBT.setImageResource(R.drawable.vol_3)
                }
                0.8f -> {
                    volumeLevel = 0f
                    //audio_binding.playPauseBT.setImageResource(R.drawable.vol_0)
                }
            }
            audioPlayer.setVolume(volumeLevel, volumeLevel)
        }

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