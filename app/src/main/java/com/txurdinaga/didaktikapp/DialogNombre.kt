package com.txurdinaga.didaktikapp

import android.app.AlertDialog
import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.DialogFragment
import com.txurdinaga.didaktikapp.databinding.DialogNombreBinding

class DialogNombre : DialogFragment() {

    private lateinit var binding: DialogNombreBinding

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return activity?.let {
            val builder = AlertDialog.Builder(it)
            // Get the layout inflater
            val inflater = requireActivity().layoutInflater
            // Inflate and set the layout for the dialog
            // Pass null as the parent view because its going in the dialog layout
            binding = DialogNombreBinding.inflate(layoutInflater)
            builder.setView(inflater.inflate(R.layout.dialog_nombre, null))
                // Add action buttons
                .setPositiveButton("Aceptar",
                    DialogInterface.OnClickListener { _, _ ->
                        var nombre:String = "${binding.nombreUsuarioET.text}"
                        Log.i("i", "$nombre +++")
                        SharedPrefs.users.user = "nombre"
                        Log.i("i", "shared ${SharedPrefs.users.user}")
                    })
                .setTitle("¿CUAL ES TU NOMBRE?")
            builder.create()
        } ?: throw IllegalStateException("Activity cannot be null")
    }

}