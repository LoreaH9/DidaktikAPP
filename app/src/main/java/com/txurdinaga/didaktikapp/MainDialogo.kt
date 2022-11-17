package com.txurdinaga.didaktikapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.txurdinaga.didaktikapp.databinding.LayoutDialogoBinding
import java.lang.Thread.currentThread
import kotlin.concurrent.thread

class MainDialogo : AppCompatActivity() {
    private lateinit var binding: LayoutDialogoBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = LayoutDialogoBinding.inflate(layoutInflater)
        setContentView(binding.root)
        var dialogo = 0
        when(dialogo){
            0 -> {Dialogo0()}
            1 -> {Dialogo1()}
        }
    }
    private fun wait4Click(ms: Long, thr: Thread){
        thr.stop()
        binding.fondoIV.setOnClickListener{
            thr.resume()
        }
        while(!thr.isAlive){}
    }

    private fun Dialogo0(){
        thread {
            binding.fondoIV.setImageResource(R.drawable.nave_alien_0)
            binding.personaje1IV.setImageResource(R.drawable.ali_1)
            binding.personaje2IV.visibility = View.INVISIBLE
            binding.bocadilloIV.setImageResource(R.drawable.bocadillo_1)
            wait4Click(2000, currentThread())
            runOnUiThread {
                binding.bocadilloIV.visibility = View.VISIBLE
                binding.textoTV.visibility = View.VISIBLE
                binding.textoTV.text = getString(R.string.dialogo0_1)
                binding.personaje1IV.setImageResource(R.drawable.ali_2)
            }
            wait4Click(2000, currentThread())
            runOnUiThread {
                binding.textoTV.text = getString(R.string.dialogo0_2)
                binding.personaje1IV.setImageResource(R.drawable.ali_3)
            }
            wait4Click(2000, currentThread())
            runOnUiThread {
                binding.textoTV.text = getString(R.string.dialogo0_3)
            }
            wait4Click(2000, currentThread())
            runOnUiThread {
                binding.textoTV.text = getString(R.string.dialogo0_4)
                binding.personaje1IV.setImageResource(R.drawable.ali_2)
            }
        }


    }

    private fun Dialogo1(){
        binding.fondoIV.setImageResource(R.drawable.nave_frontal)
        binding.personaje1IV.setImageResource(R.drawable.ali_2)
        binding.personaje2IV.visibility = View.INVISIBLE
        binding.bocadilloIV.setImageResource(R.drawable.bocadillo_1)
    }

}