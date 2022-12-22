package com.txurdinaga.didaktikapp.actividades

import android.content.ClipData
import android.content.ClipDescription
import android.content.DialogInterface
import android.content.Intent
import android.graphics.Canvas
import android.graphics.Point
import android.os.Build
import android.os.Bundle
import android.view.DragEvent
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import com.txurdinaga.didaktikapp.*
import com.txurdinaga.didaktikapp.activities.MainContrasena
import com.txurdinaga.didaktikapp.activities.MainDialogo
import com.txurdinaga.didaktikapp.activities.MainMenu
import com.txurdinaga.didaktikapp.databinding.FragmentActividad7Binding
import com.txurdinaga.didaktikapp.databinding.LayoutActividadBinding

class MainActividad7 : AppCompatActivity(){
    private lateinit var binding: LayoutActividadBinding
    private lateinit var binding7: FragmentActividad7Binding
    private var seleccionada : ImageView? = null
    private lateinit var foto_list:List<ImageView>
    private lateinit var text_list:List<TextView>

    var set = 7

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        SharedPrefs.idioma.aldatu(SharedPrefs.idioma.idioma, resources)
        binding = LayoutActividadBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.fondoIV.setImageResource(ActividadesProvider.actividad[set].fondo)
        binding.explicacionTV.text = getString(ActividadesProvider.actividad[set].explicacion)
        binding.verBT.visibility = View.INVISIBLE

        binding7 = FragmentActividad7Binding.inflate(layoutInflater)
        binding.fragFL.addView(binding7.root)

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

        foto_list = listOf(
            binding7.foto71, binding7.foto72, binding7.foto73, binding7.foto74, binding7.foto75,
            binding7.foto76, binding7.foto77, binding7.foto78, binding7.foto79
        )
        foto_list.forEach{
            it.setOnLongClickListener(longClickListener)
        }

        text_list = listOf(
                binding7.text71, binding7.text72, binding7.text73, binding7.text74, binding7.text75,
                binding7.text76, binding7.text77, binding7.text78, binding7.text79
        )
        text_list.forEach{
            it.setOnDragListener(dragListener)
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
        val receiverView: TextView = v as TextView

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
                    receiverView.setBackgroundResource(R.drawable.style_redondeado_editext_v)
                    seleccionada?.isEnabled = false
                    seleccionada?.alpha = 0.65F
                    if(comprobacionFinal7(foto_list)) {
                        binding.terminarActividadBT.visibility = View.VISIBLE
                    }
                }else
                    receiverView.setBackgroundResource(R.drawable.style_redondeado_editext)

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
            .setView(R.layout.dialog_enhorabuena7)
            .setPositiveButton(getString(R.string.continuar),
                DialogInterface.OnClickListener { _, _ ->
                    if(SharedPrefs.puntopartida.partida.toInt() < set && !SharedPrefs.modolibre.modo) {
                        SharedPrefs.puntopartida.partida = "$set"
                    }
                    if(!SharedPrefs.modolibre.modo) {
                        startActivity(Intent(this, MainDialogo::class.java)
                                .putExtra("set", 8))
                    } else {
                        startActivity(Intent(this, MainMenu::class.java))
                    }
                })
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

    fun comparar(img: ImageView?, txt:TextView) :Boolean {
        return img == foto_list[text_list.indexOf(txt)]
    }

    fun comprobacionFinal7(list: List<ImageView>) :Boolean {
        var r = true
        for(i in list.indices)
            if(list[i].isEnabled)
                r = false
        return r
    }

}