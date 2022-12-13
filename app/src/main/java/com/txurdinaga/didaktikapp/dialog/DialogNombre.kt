package com.txurdinaga.didaktikapp.dialog

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.DialogFragment
import com.txurdinaga.didaktikapp.SharedPrefs
import com.txurdinaga.didaktikapp.databinding.DialogNombreBinding
import com.txurdinaga.didaktikapp.room.DataBaseRoomApp
import com.txurdinaga.didaktikapp.room.Usuario

class DialogNombre : DialogFragment() {

    private lateinit var binding: DialogNombreBinding

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {

        return activity?.let {
            val builder = AlertDialog.Builder(it)
            binding = DialogNombreBinding.inflate(layoutInflater)
            builder.setView(binding.root)
                .setPositiveButton("Aceptar"
                ) { _, _ ->

                    SharedPrefs.users.user = binding.fas.text.toString()
                    val usuario = DataBaseRoomApp.DataBase.usuarioDao.selectUsersByName(SharedPrefs.users.user)
                    if(usuario==null){
                        DataBaseRoomApp.DataBase.usuarioDao.addUser(Usuario(SharedPrefs.users.user,1,2,
                            listOf<String>("Actividad 1")))
                        SharedPrefs.puntopartida.partida = "0"

                    }else{
                        SharedPrefs.puntopartida.partida = usuario.juegosCompletados.size.toString()

                    }


                }
                .setTitle("¿CUAL ES TU NOMBRE?")
            builder.create()
        } ?: throw IllegalStateException("Activity cannot be null")
    }


}