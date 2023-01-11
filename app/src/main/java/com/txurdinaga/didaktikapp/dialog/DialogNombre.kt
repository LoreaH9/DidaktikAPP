package com.txurdinaga.didaktikapp.dialog

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.DialogFragment
import com.txurdinaga.didaktikapp.SharedPrefs
import com.txurdinaga.didaktikapp.databinding.DialogNombreBinding
import com.txurdinaga.didaktikapp.room.DataBaseRoomApp
import com.txurdinaga.didaktikapp.room.Juego
import com.txurdinaga.didaktikapp.room.Usuario

class DialogNombre : DialogFragment() {

    private lateinit var binding: DialogNombreBinding

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {

        return activity?.let {
            SharedPrefs.idioma.aldatu(SharedPrefs.idioma.idioma, resources)
            val builder = AlertDialog.Builder(it)
            binding = DialogNombreBinding.inflate(layoutInflater)
            builder.setView(binding.root)
                .setPositiveButton("Aceptar"
                ) { _, _ ->
                    SharedPrefs.users.user = binding.nombreUsuarioET.text.toString()
                    val usuario = DataBaseRoomApp.DataBase.usuarioDao.selectUsersByName(SharedPrefs.users.user)
                    if(usuario==null){
                        val juego = Juego(1,"Actividad 1")
                        DataBaseRoomApp.DataBase.usuarioDao.addUser(Usuario(SharedPrefs.users.user,1,2,
                        juego))
                        SharedPrefs.puntopartida.partida = "0"
                    }else{
                        SharedPrefs.puntopartida.partida = usuario.juegosCompletados.id as String
                    }
                }
                .setTitle("¿CUAL ES TU NOMBRE?")
                .setCancelable(false)
            builder.create()
        } ?: throw IllegalStateException("Activity cannot be null")
    }


}