package com.txurdinaga.didaktikapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.txurdinaga.didaktikapp.databinding.LayoutDialogoBinding

class MainDialogo : AppCompatActivity() {
    private lateinit var binding: LayoutDialogoBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = LayoutDialogoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var set = 4
        var line = 1
        setDialogo(set, 0)

        binding.viewBT.setOnClickListener{
            setDialogo(set, line)
            line++
        }

        binding.siguienteBT.setOnClickListener{
            finish()
        }

    }

    private fun setDialogo(set: Int, line: Int){
        when(set){
            0 -> {dialogo_0(line)}
            1 -> {dialogo_1(line)}
            2 -> {dialogo_2(line)}
            3 -> {dialogo_3(line)}
            4 -> {dialogo_4(line)}
            5 -> {dialogo_3(line)}
            6 -> {dialogo_3(line)}
            7 -> {dialogo_3(line)}
            8 -> {dialogo_3(line)}
        }
    }

    private fun dialogo_0(line: Int){
        when(line){
            0 -> {      // invisibles
                binding.siguienteBT.visibility = View.INVISIBLE
                binding.bocadilloIV.visibility = View.INVISIBLE
                binding.textoTV.text = ""
                binding.personaje2IV.visibility = View.INVISIBLE
                        // imagenes
                binding.fondoIV.setImageResource(R.drawable.fondo_0)
                binding.personaje1IV.setImageResource(R.drawable.ali_1)
            }
            1 -> {      // bocadillo visible
                binding.bocadilloIV.visibility = View.VISIBLE
                binding.textoTV.visibility = View.VISIBLE
                        // texto
                binding.textoTV.text = getString(R.string.dialogo0_1)
                binding.bocadilloIV.setImageResource(R.drawable.bocadillo_1)
                        // ali
                binding.personaje1IV.setImageResource(R.drawable.ali_2)
            }
            2 -> {      // texto
                binding.textoTV.text = getString(R.string.dialogo0_2)
                        // ali
                binding.personaje1IV.setImageResource(R.drawable.ali_3)
            }
            3 -> {      //texto
                binding.textoTV.text = getString(R.string.dialogo0_3)
            }
            4 -> {      //texto
                binding.textoTV.text = getString(R.string.dialogo0_4)
                        //ali
                binding.personaje1IV.setImageResource(R.drawable.ali_2)
                        // siguiente visible
                binding.siguienteBT.visibility = View.VISIBLE
            }
        }
    }

    private fun dialogo_1(line: Int){
        when(line){
            0 -> {      // invisibles
                binding.siguienteBT.visibility = View.INVISIBLE
                binding.bocadilloIV.visibility = View.INVISIBLE
                binding.textoTV.text = ""
                        // imagenes
                binding.fondoIV.setImageResource(R.drawable.fondo_1)
                binding.personaje1IV.setImageResource(R.drawable.ali_1)
                binding.personaje2IV.setImageResource(R.drawable.perso_1)
            }
            1 -> {      // bocadillo visible
                binding.bocadilloIV.visibility = View.VISIBLE
                binding.textoTV.visibility = View.VISIBLE
                        // texto
                binding.textoTV.text = getString(R.string.dialogo1_1)
                binding.bocadilloIV.setImageResource(R.drawable.bocadillo_1)
                        // ali
                binding.personaje1IV.setImageResource(R.drawable.ali_2)
            }
            2 -> {      // texto
                binding.textoTV.text = getString(R.string.dialogo1_2)
                binding.bocadilloIV.setImageResource(R.drawable.bocadillo_2)
                        // ali
                binding.personaje1IV.setImageResource(R.drawable.ali_1)
            }
            3 -> {      // texto
                binding.textoTV.text = getString(R.string.dialogo1_3)
                binding.bocadilloIV.setImageResource(R.drawable.bocadillo_1)
                        // ali
                binding.personaje1IV.setImageResource(R.drawable.ali_3)
            }
            4 -> {      // texto
                binding.textoTV.text = getString(R.string.dialogo1_4)
                binding.bocadilloIV.setImageResource(R.drawable.bocadillo_2)
                        // ali
                binding.personaje1IV.setImageResource(R.drawable.ali_1)
            }
            5 -> {      // texto
                binding.textoTV.text = getString(R.string.dialogo1_5)
                binding.bocadilloIV.setImageResource(R.drawable.bocadillo_1)
                        // ali
                binding.personaje1IV.setImageResource(R.drawable.ali_2)
            }
            6 -> {      // texto
                binding.textoTV.text = getString(R.string.dialogo1_6)
                binding.bocadilloIV.setImageResource(R.drawable.bocadillo_2)
                        // ali
                binding.personaje1IV.setImageResource(R.drawable.ali_1)
            }
            7 -> {      // texto
                binding.textoTV.text = getString(R.string.dialogo1_7)
                binding.bocadilloIV.setImageResource(R.drawable.bocadillo_1)
                        // ali
                binding.personaje1IV.setImageResource(R.drawable.ali_3)
            }
            8 -> {      // texto
                binding.textoTV.text = getString(R.string.dialogo1_8)
                binding.bocadilloIV.setImageResource(R.drawable.bocadillo_2)
                        // ali
                binding.personaje1IV.setImageResource(R.drawable.ali_1)
                        // siguiente visible
                binding.siguienteBT.visibility = View.VISIBLE
            }
        }
    }

    private fun dialogo_2(line: Int){
        when(line) {
            0 -> {      // invisibles
                binding.siguienteBT.visibility = View.INVISIBLE
                binding.bocadilloIV.visibility = View.INVISIBLE
                binding.textoTV.text = ""
                // imagenes
                binding.fondoIV.setImageResource(R.drawable.fondo_2)
                binding.personaje1IV.setImageResource(R.drawable.ali_1)
                binding.personaje2IV.setImageResource(R.drawable.perso_2)
            }
            1 -> {      // bocadillo visible
                binding.bocadilloIV.visibility = View.VISIBLE
                binding.textoTV.visibility = View.VISIBLE
                // texto
                binding.textoTV.text = getString(R.string.dialogo2_1)
                binding.bocadilloIV.setImageResource(R.drawable.bocadillo_1)
                // ali
                binding.personaje1IV.setImageResource(R.drawable.ali_2)
            }
            2 -> {      // texto
                binding.textoTV.text = getString(R.string.dialogo2_2)
                binding.bocadilloIV.setImageResource(R.drawable.bocadillo_2)
                // ali
                binding.personaje1IV.setImageResource(R.drawable.ali_1)
            }
            3 -> {      // texto
                binding.textoTV.text = getString(R.string.dialogo2_3)
                binding.bocadilloIV.setImageResource(R.drawable.bocadillo_1)
                // ali
                binding.personaje1IV.setImageResource(R.drawable.ali_3)
            }
            4 -> {      // texto
                binding.textoTV.text = getString(R.string.dialogo2_4)
                binding.bocadilloIV.setImageResource(R.drawable.bocadillo_2)
                // ali
                binding.personaje1IV.setImageResource(R.drawable.ali_1)
            }
            5 -> {      // texto
                binding.textoTV.text = getString(R.string.dialogo2_5)
                binding.bocadilloIV.setImageResource(R.drawable.bocadillo_2)
                // ali
                binding.personaje1IV.setImageResource(R.drawable.ali_1)
            }
            6 -> {      // texto
                binding.textoTV.text = getString(R.string.dialogo2_6)
                binding.bocadilloIV.setImageResource(R.drawable.bocadillo_2)
                // ali
                binding.personaje1IV.setImageResource(R.drawable.ali_1)
            }
            7 -> {      // texto
                binding.textoTV.text = getString(R.string.dialogo2_7)
                binding.bocadilloIV.setImageResource(R.drawable.bocadillo_2)
                // ali
                binding.personaje1IV.setImageResource(R.drawable.ali_1)
            }
            8 -> {      // texto
                binding.textoTV.text = getString(R.string.dialogo2_8)
                binding.bocadilloIV.setImageResource(R.drawable.bocadillo_2)
                // ali
                binding.personaje1IV.setImageResource(R.drawable.ali_1)
            }
            9 -> {      // texto
                binding.textoTV.text = getString(R.string.dialogo2_9)
                binding.bocadilloIV.setImageResource(R.drawable.bocadillo_1)
                // ali
                binding.personaje1IV.setImageResource(R.drawable.ali_2)
            }
            10 -> {      // texto
                binding.textoTV.text = getString(R.string.dialogo2_10)
                binding.bocadilloIV.setImageResource(R.drawable.bocadillo_2)
                // ali
                binding.personaje1IV.setImageResource(R.drawable.ali_1)
                // siguiente visible
                binding.siguienteBT.visibility = View.VISIBLE
            }
        }
    }

    private fun dialogo_3(line: Int){
        when(line){
            0 -> {      // invisibles
                binding.siguienteBT.visibility = View.INVISIBLE
                binding.bocadilloIV.visibility = View.INVISIBLE
                binding.textoTV.text = ""
                // imagenes
                binding.fondoIV.setImageResource(R.drawable.fondo_3)
                binding.personaje1IV.setImageResource(R.drawable.ali_1)
                binding.personaje2IV.setImageResource(R.drawable.perso_3)
            }
            1 -> {      // bocadillo visible
                binding.bocadilloIV.visibility = View.VISIBLE
                binding.textoTV.visibility = View.VISIBLE
                // texto
                binding.textoTV.text = getString(R.string.dialogo3_1)
                binding.bocadilloIV.setImageResource(R.drawable.bocadillo_2)
                // ali
                binding.personaje1IV.setImageResource(R.drawable.ali_1)
            }
            2 -> {      // texto
                binding.textoTV.text = getString(R.string.dialogo3_2)
                binding.bocadilloIV.setImageResource(R.drawable.bocadillo_1)
                // ali
                binding.personaje1IV.setImageResource(R.drawable.ali_2)
            }
            3 -> {      // texto
                binding.textoTV.text = getString(R.string.dialogo3_3)
                binding.bocadilloIV.setImageResource(R.drawable.bocadillo_2)
                // ali
                binding.personaje1IV.setImageResource(R.drawable.ali_1)
            }
            4 -> {      // texto
                binding.textoTV.text = getString(R.string.dialogo3_4)
                binding.bocadilloIV.setImageResource(R.drawable.bocadillo_2)
                // ali
                binding.personaje1IV.setImageResource(R.drawable.ali_1)
            }
            5 -> {      // texto
                binding.textoTV.text = getString(R.string.dialogo3_5)
                binding.bocadilloIV.setImageResource(R.drawable.bocadillo_1)
                // ali
                binding.personaje1IV.setImageResource(R.drawable.ali_2)
            }
            6 -> {      // texto
                binding.textoTV.text = getString(R.string.dialogo3_6)
                binding.bocadilloIV.setImageResource(R.drawable.bocadillo_2)
                // ali
                binding.personaje1IV.setImageResource(R.drawable.ali_1)
                // siguiente visible
                binding.siguienteBT.visibility = View.VISIBLE
            }
        }
    }
    private fun dialogo_4(line: Int){
        when(line){
            0 -> {      // invisibles
                binding.siguienteBT.visibility = View.INVISIBLE
                binding.bocadilloIV.visibility = View.INVISIBLE
                binding.textoTV.text = ""
                // imagenes
                binding.fondoIV.setImageResource(R.drawable.fondo_4)
                binding.personaje1IV.setImageResource(R.drawable.ali_1)
                binding.personaje2IV.setImageResource(R.drawable.perso_4)
            }
            1 -> {      // bocadillo visible
                binding.bocadilloIV.visibility = View.VISIBLE
                binding.textoTV.visibility = View.VISIBLE
                // texto
                binding.textoTV.text = getString(R.string.dialogo4_1)
                binding.bocadilloIV.setImageResource(R.drawable.bocadillo_1)
                // ali
                binding.personaje1IV.setImageResource(R.drawable.ali_1)
            }
            2 -> {      // texto
                binding.textoTV.text = getString(R.string.dialogo4_2)
                binding.bocadilloIV.setImageResource(R.drawable.bocadillo_2)
                // ali
                binding.personaje1IV.setImageResource(R.drawable.ali_1)
            }
            3 -> {      // texto
                binding.textoTV.text = getString(R.string.dialogo4_3)
                binding.bocadilloIV.setImageResource(R.drawable.bocadillo_1)
                // ali
                binding.personaje1IV.setImageResource(R.drawable.ali_3)
            }
            4 -> {      // texto
                binding.textoTV.text = getString(R.string.dialogo4_4)
                binding.bocadilloIV.setImageResource(R.drawable.bocadillo_2)
                // ali
                binding.personaje1IV.setImageResource(R.drawable.ali_1)
            }
            5 -> {      // texto
                binding.textoTV.text = getString(R.string.dialogo4_5)
                binding.bocadilloIV.setImageResource(R.drawable.bocadillo_1)
                // ali
                binding.personaje1IV.setImageResource(R.drawable.ali_2)
            }
            6 -> {      // texto
                binding.textoTV.text = getString(R.string.dialogo4_6)
                binding.bocadilloIV.setImageResource(R.drawable.bocadillo_2)
                // ali
                binding.personaje1IV.setImageResource(R.drawable.ali_1)
            }
            7 -> {      // texto
                binding.textoTV.text = getString(R.string.dialogo4_7)
                binding.bocadilloIV.setImageResource(R.drawable.bocadillo_1)
                // ali
                binding.personaje1IV.setImageResource(R.drawable.ali_2)
            }
            8 -> {      // texto
                binding.textoTV.text = getString(R.string.dialogo4_8)
                binding.bocadilloIV.setImageResource(R.drawable.bocadillo_2)
                // ali
                binding.personaje1IV.setImageResource(R.drawable.ali_1)
                // siguiente visible
                binding.siguienteBT.visibility = View.VISIBLE
            }
        }
    }

    private fun dialogo_5(line: Int){
        when(line){
            0 -> {      // invisibles
                binding.siguienteBT.visibility = View.INVISIBLE
                binding.bocadilloIV.visibility = View.INVISIBLE
                binding.textoTV.text = ""
                // imagenes
                binding.fondoIV.setImageResource(R.drawable.fondo_5)
                binding.personaje1IV.setImageResource(R.drawable.ali_1)
                binding.personaje2IV.setImageResource(R.drawable.perso_5)
            }
            8 -> {      // texto
                binding.textoTV.text = getString(R.string.dialogo5_8)
                binding.bocadilloIV.setImageResource(R.drawable.bocadillo_2)
                // ali
                binding.personaje1IV.setImageResource(R.drawable.ali_1)
                // siguiente visible
                binding.siguienteBT.visibility = View.VISIBLE
            }
        }
    }

    private fun dialogo_6(line: Int){
        when(line){
            0 -> {      // invisibles
                binding.siguienteBT.visibility = View.INVISIBLE
                binding.bocadilloIV.visibility = View.INVISIBLE
                binding.textoTV.text = ""
                // imagenes
                binding.fondoIV.setImageResource(R.drawable.fondo_6)
                binding.personaje1IV.setImageResource(R.drawable.ali_1)
                binding.personaje2IV.setImageResource(R.drawable.perso_6)
            }
            6 -> {      // texto
                binding.textoTV.text = getString(R.string.dialogo6_6)
                binding.bocadilloIV.setImageResource(R.drawable.bocadillo_2)
                // ali
                binding.personaje1IV.setImageResource(R.drawable.ali_1)
                // siguiente visible
                binding.siguienteBT.visibility = View.VISIBLE
            }
        }
    }

    private fun dialogo_7(line: Int){
        when(line){
            0 -> {      // invisibles
                binding.siguienteBT.visibility = View.INVISIBLE
                binding.bocadilloIV.visibility = View.INVISIBLE
                binding.textoTV.text = ""
                // imagenes
                binding.fondoIV.setImageResource(R.drawable.fondo_7)
                binding.personaje1IV.setImageResource(R.drawable.ali_1)
                binding.personaje2IV.setImageResource(R.drawable.perso_7)
            }
            5 -> {      // texto
                binding.textoTV.text = getString(R.string.dialogo7_5)
                binding.bocadilloIV.setImageResource(R.drawable.bocadillo_2)
                // ali
                binding.personaje1IV.setImageResource(R.drawable.ali_1)
                // siguiente visible
                binding.siguienteBT.visibility = View.VISIBLE
            }
        }
    }

    private fun dialogo_8(line: Int){
        when(line){
            0 -> {      // invisibles
                binding.siguienteBT.visibility = View.INVISIBLE
                binding.bocadilloIV.visibility = View.INVISIBLE
                binding.textoTV.text = ""
                binding.personaje2IV.visibility = View.INVISIBLE
                // imagenes
                binding.fondoIV.setImageResource(R.drawable.fondo_0)
                binding.personaje1IV.setImageResource(R.drawable.ali_1)
            }
            2 -> {      // texto
                binding.textoTV.text = getString(R.string.dialogo8_2)
                binding.bocadilloIV.setImageResource(R.drawable.bocadillo_1)
                // ali
                binding.personaje1IV.setImageResource(R.drawable.ali_1)
                // siguiente visible
                binding.siguienteBT.visibility = View.VISIBLE
            }
        }
    }
}