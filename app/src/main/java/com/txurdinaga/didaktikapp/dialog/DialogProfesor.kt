package com.txurdinaga.didaktikapp.dialog

import android.app.AlertDialog
import android.app.Dialog
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
            SharedPrefs.idioma.aldatu(SharedPrefs.idioma.idioma, resources)
            val builder = AlertDialog.Builder(it)
            binding = DialogProfesorBinding.inflate(layoutInflater)
            builder.setView(binding.root)
                // Add action buttons
                .setPositiveButton("aceptar"
                ) { _, _ ->
                    if (binding.nombreUsuario.text.toString() == usuarioProfesor &&
                        binding.contraUsuario.text.toString() == contraseniaProfesor
                    ) {
                        SharedPrefs.users.user = usuarioProfesor
                        SharedPrefs.tipousu.tipo = "profesor"
                        startActivity(Intent(context, MainMenu::class.java))

                        /*val juego = Juego(1,"Actividad 1")
                        DataBaseRoomApp.DataBase.usuarioDao.addUser(Usuario(SharedPrefs.users.user,1,2,
                            juego))
                        val userList = DataBaseRoomApp.DataBase.usuarioDao.selectAllUsers()
                        for (user in userList){
                            Toast.makeText(context, user.nombre, Toast.LENGTH_LONG).show()
                        }*/
                    } else {
                        Toast.makeText(context, R.string.incorrecto, Toast.LENGTH_LONG).show()
                    }

                }
                .setNegativeButton("cancelar"
                ) { _, _ ->
                    dialog?.cancel()
                }
            builder.create()
        } ?: throw IllegalStateException("Activity cannot be null")
    }
}
