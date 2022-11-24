package com.txurdinaga.didaktikapp

import android.app.Dialog
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment

class DialogActividad: DialogFragment() {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return activity?.let {
            val builder = AlertDialog.Builder(it)
            // Get the layout inflater
            val inflater = requireActivity().layoutInflater;

            // Inflate and set the layout for the dialog
            // Pass null as the parent view because its going in the dialog layout
            builder.setView(inflater.inflate(R.layout.dialog_actividad, null))
                // Add action buttons
                .setPositiveButton("JUGAR",
                    DialogInterface.OnClickListener { dialog, id ->
                        Log.i("1","holaagmsdgs")
                        val intent= Intent(requireContext(),MainDialogo::class.java)
                        startActivity(intent)
                    })

            builder.create()
        } ?: throw IllegalStateException("Activity cannot be null")

    }

}
