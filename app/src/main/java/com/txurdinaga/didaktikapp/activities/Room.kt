package com.txurdinaga.didaktikapp.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.txurdinaga.didaktikapp.databinding.ActivityRoomBinding
import com.txurdinaga.didaktikapp.room.DataBaseRoomApp
import com.txurdinaga.didaktikapp.room.Juego
import com.txurdinaga.didaktikapp.room.Usuario

class Room : AppCompatActivity() {
    private lateinit var binding : ActivityRoomBinding
    private lateinit var userList : List<Usuario>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRoomBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btInsertar.setOnClickListener {
            val usuario=Usuario(binding.tvnombre.text.toString(),0,0, Juego(1,"Actividad 1") )
            DataBaseRoomApp.DataBase.usuarioDao.addUser(usuario)
        }

        binding.btListar.setOnClickListener {
            binding.tvasignaturas.text=""
            userList = DataBaseRoomApp.DataBase.usuarioDao.selectAllUsers()
            for (user in userList){
                binding.tvasignaturas.append("${user.nombre}, ${user.profesor}\n")
            }
        }
    }
}