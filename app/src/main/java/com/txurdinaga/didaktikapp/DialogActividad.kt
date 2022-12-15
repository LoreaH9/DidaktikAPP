package com.txurdinaga.didaktikapp

import android.app.AlertDialog
import android.app.Dialog
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.DialogFragment
import com.txurdinaga.didaktikapp.databinding.DialogActividadBinding
import com.txurdinaga.didaktikapp.databinding.DialogNombreBinding

class DialogActividad: DialogFragment() {

    private lateinit var binding: DialogActividadBinding

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {

        return activity?.let {
            val builder = AlertDialog.Builder(it)
            // Get the layout inflater
            val inflater = requireActivity().layoutInflater;

            // Inflate and set the layout for the dialog
            // Pass null as the parent view because its going in the dialog layout
            binding = DialogActividadBinding.inflate(layoutInflater)
            builder.setView(inflater.inflate(R.layout.dialog_nombre, null))
                // Add action buttons
                .setPositiveButton("Jugar",
                    DialogInterface.OnClickListener { dialog, id ->
                        startActivity(
                            Intent(requireContext(),MainDialogo::class.java)
                        )
                    })
            builder.create()
        } ?: throw IllegalStateException("Activity cannot be null")
    }


}