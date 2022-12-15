package com.txurdinaga.didaktikapp.dialog

import android.app.AlertDialog
import android.app.Dialog
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import com.txurdinaga.didaktikapp.Constantes.contraseniaProfesor
import com.txurdinaga.didaktikapp.Constantes.usuarioProfesor
import com.txurdinaga.didaktikapp.activities.MainMenu
import com.txurdinaga.didaktikapp.R
import com.txurdinaga.didaktikapp.SharedPrefs
import com.txurdinaga.didaktikapp.databinding.DialogProfesorBinding


class DialogProfesor : DialogFragment() {
    private lateinit var binding: DialogProfesorBinding

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return activity?.let {
            val builder = AlertDialog.Builder(it)
            val inflater = requireActivity().layoutInflater;
            binding = DialogProfesorBinding.inflate(layoutInflater)
            builder.setView(binding.root)
                // Add action buttons
                .setPositiveButton("aceptar",
                    DialogInterface.OnClickListener { _, _ ->
                        if (binding.nombreUsuario.text.toString() == usuarioProfesor &&
                            binding.contraUsuario.text.toString() == contraseniaProfesor){
                            SharedPrefs.users.user = usuarioProfesor
                            SharedPrefs.tipousu.tipo="profesor"
                            startActivity(Intent(context, MainMenu::class.java))
                        }else{
                            Toast.makeText(context, R.string.incorrecto, Toast.LENGTH_LONG).show()
                        }
                    })
                .setNegativeButton("cancelar",
                    DialogInterface.OnClickListener { _, _ ->
                        dialog?.cancel()
                    })
            builder.create()
        } ?: throw IllegalStateException("Activity cannot be null")
    }
}
