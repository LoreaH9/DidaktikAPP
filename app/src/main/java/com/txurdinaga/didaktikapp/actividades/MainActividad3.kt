package com.txurdinaga.didaktikapp.actividades

import android.content.ClipData
import android.content.ClipDescription
import android.content.Intent
import android.graphics.Canvas
import android.graphics.Point
import android.graphics.drawable.Drawable
import android.os.Build
import android.os.Bundle
import android.view.DragEvent
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import com.txurdinaga.didaktikapp.*
import com.txurdinaga.didaktikapp.activities.MainContrasena
import com.txurdinaga.didaktikapp.activities.MainMenu
import com.txurdinaga.didaktikapp.databinding.FragmentActividad3Binding
import com.txurdinaga.didaktikapp.databinding.LayoutActividadBinding

class MainActividad3 : AppCompatActivity(){
    private lateinit var binding: LayoutActividadBinding
    private lateinit var binding3: FragmentActividad3Binding
    private var seleccionada : ImageView? = null
    private lateinit var img_list:List<Int>
    private lateinit var pieza_norand_list:MutableList<ImageView>
    private lateinit var pieza_list:MutableList<ImageView>
    private lateinit var puzzle_list:List<ImageView>

    var set = 3

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        SharedPrefs.idioma.aldatu(SharedPrefs.idioma.idioma, resources)
        binding = LayoutActividadBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.fondoIV.setImageResource(ActividadesProvider.actividad[set].fondo)
        binding.explicacionTV.text = getString(ActividadesProvider.actividad[set].explicacion)
        binding.verBT.visibility = View.INVISIBLE

        binding3 = FragmentActividad3Binding.inflate(layoutInflater)
        binding.fragFL.addView(binding3.root)

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

        puzzle_list = listOf(
            binding3.hueco1IV, binding3.hueco2IV, binding3.hueco3IV, binding3.hueco4IV, binding3.hueco5IV, binding3.hueco6IV
        )
        puzzle_list.forEach{
            it.setOnDragListener(dragListener)
        }

        pieza_norand_list = mutableListOf(
            binding3.pieza1IV, binding3.pieza2IV, binding3.pieza3IV, binding3.pieza4IV, binding3.pieza5IV, binding3.pieza6IV
        )

        pieza_list = mutableListOf(
            binding3.pieza1IV, binding3.pieza2IV, binding3.pieza3IV, binding3.pieza4IV, binding3.pieza5IV, binding3.pieza6IV
        )
        pieza_list.forEach{
            it.setOnLongClickListener(longClickListener)
        }

        for (i in pieza_list.indices) {
            pieza_list[i] = pieza_norand_list[(0 until pieza_norand_list.size).random()]
            pieza_norand_list.remove(pieza_list[i])
        }

        img_list = listOf(
            R.drawable.pieza1, R.drawable.pieza2, R.drawable.pieza3, R.drawable.pieza4, R.drawable.pieza5, R.drawable.pieza6
        )
        img_list.forEach {
            pieza_list[img_list.indexOf(it)].setImageResource(it)
        }

    }

    private class MyDragShadowBuilder(val v: View) : View.DragShadowBuilder(v) {
        override fun onProvideShadowMetrics(size: Point, touch: Point) {
            size.set(view.width, view.height)
            touch.set(view.width / 2, view.height / 2)
        }
        override fun onDrawShadow(canvas: Canvas) {
            v.draw(canvas)
        }
    }

    private val longClickListener = View.OnLongClickListener { v ->
        val item = ClipData.Item(v.tag as? CharSequence)
        seleccionada = v as ImageView

        val dragData = ClipData(
            v.tag as? CharSequence,
            arrayOf(ClipDescription.MIMETYPE_TEXT_PLAIN),
            item
        )

        val myShadow = MyDragShadowBuilder(v)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            v.startDragAndDrop(dragData, myShadow,null,0)
        } else {
            v.startDrag(dragData, myShadow,null,0)
        }

        true
    }

    private val dragListener = View.OnDragListener { v, event ->
        val receiverView: ImageView = v as ImageView

        when (event.action) {
            DragEvent.ACTION_DRAG_STARTED -> {
                true
            }

            DragEvent.ACTION_DRAG_ENTERED -> {
                if(event.clipDescription.label == receiverView.tag as? String) {

                } else {

                }
                v.invalidate()
                true
            }

            DragEvent.ACTION_DRAG_LOCATION ->
                true

            DragEvent.ACTION_DRAG_EXITED -> {
                if(event.clipDescription.label == receiverView.tag as? String) {
                    v.invalidate()
                }
                true
            }

            DragEvent.ACTION_DROP -> {
                if (comparar(seleccionada, receiverView)){
                    seleccionada?.isEnabled = false
                    seleccionada?.alpha = 0F
                    receiverView.alpha = 1F
                    if(comprobacionFinal3(pieza_list)) {
                        binding.terminarActividadBT.visibility = View.VISIBLE
                    }
                }

                true
            }

            DragEvent.ACTION_DRAG_ENDED -> {

                true
            }

            else -> {
                false
            }
        }
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

    fun comparar(piez: ImageView?, puzz:ImageView) :Boolean {
        return piez == pieza_list[puzzle_list.indexOf(puzz)]
    }

    fun comprobacionFinal3(list: List<ImageView>) :Boolean {
        var r = true
        for(i in list.indices)
            if(list[i].isEnabled)
                r = false
        return r
    }

}