package com.txurdinaga.didaktikapp

import android.content.ClipData
import android.content.ClipDescription
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Point
import android.media.ImageReader
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.DragEvent
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

import com.txurdinaga.didaktikapp.databinding.FragmentActividad72Binding

class Actividad7 :AppCompatActivity(){
    private lateinit var binding :FragmentActividad72Binding
    private var seleccionada : ImageView? = null
    private lateinit var listaimg:List<ImageView>
    private lateinit var listatxt:List<TextView>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = FragmentActividad72Binding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.albertImg.setOnLongClickListener(longClickListener)
        binding.cascoviejoImg .setOnLongClickListener(longClickListener)
        binding.fiestavirgenImg .setOnLongClickListener(longClickListener)
        binding.puentebzkImg .setOnLongClickListener(longClickListener)
        binding.juanantonioImg .setOnLongClickListener(longClickListener)
        binding.salazarImg .setOnLongClickListener(longClickListener)
        binding.sanroqueImg .setOnLongClickListener(longClickListener)
        binding.stamariImg .setOnLongClickListener(longClickListener)
        binding.victorImg .setOnLongClickListener(longClickListener)

        binding.TVLugar1 .setOnDragListener(dragListener)
        binding.TVLugar2 .setOnDragListener(dragListener)
        binding.TVLugar3 .setOnDragListener(dragListener)
        binding.TVLugar4 .setOnDragListener(dragListener)
        binding.TVLugar5 .setOnDragListener(dragListener)
        binding.TVLugar6 .setOnDragListener(dragListener)
        binding.TVLugar7 .setOnDragListener(dragListener)
        binding.TVLugar8 .setOnDragListener(dragListener)
        binding.TVLugar9 .setOnDragListener(dragListener)

        listaimg = listOf(binding.juanantonioImg, binding.salazarImg,binding.stamariImg,binding.fiestavirgenImg,binding.albertImg,binding.sanroqueImg,binding.cascoviejoImg,binding.puentebzkImg,binding.victorImg)
        listatxt = listOf(binding.TVLugar1, binding.TVLugar2,binding.TVLugar3,binding.TVLugar4,binding.TVLugar5,binding.TVLugar6,binding.TVLugar7,binding.TVLugar8,binding.TVLugar9)


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
                binding.statusTextView.text = "Estas arrastrando una figura"
                true
            }

            DragEvent.ACTION_DRAG_ENTERED -> {
                if(event.clipDescription.label == receiverView.tag as? String) {

                    binding.statusTextView.text = "Imagen Correcta!"
                } else {
                    receiverView.setTextColor(Color.RED)
                    binding.statusTextView.text = "No Imagen Incorrecta!"
                }
                v.invalidate()
                true
            }

            DragEvent.ACTION_DRAG_LOCATION ->
                true

            DragEvent.ACTION_DRAG_EXITED -> {
                if(event.clipDescription.label == receiverView.tag as? String) {
                    binding.statusTextView.text = "Casi la tenias!"
                    v.invalidate()
                }
                true
            }

            DragEvent.ACTION_DROP -> {
                binding.statusTextView.text = "Soltaste la imagen!"
                if (comparar(seleccionada, receiverView)){
                    receiverView.setTextColor(Color.GREEN)
                }else
                    receiverView.setTextColor(Color.RED)


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


    fun comparar(img: ImageView?, txt:TextView):Boolean{
        return img == listaimg[listatxt.indexOf(txt)]
    }


    /*
    LE PASAS A COMPARAR(LA IMAGEN Q SE ESTA DRAGEANDO, EL TEXT DONDE SE ESTA SOLTANDO)
    SACAS LA POSICION DEL TEXTVIEW EN LA LISTA (CON INDEX OF O ALGO ASSI)
    GUARDAS LA POSICION EN UNA VARIABLE Y COMPARAS --> listaimg[posicion] == IMAGEN DRAG
    Y YA PONES COLORES Y TAL
     */

}