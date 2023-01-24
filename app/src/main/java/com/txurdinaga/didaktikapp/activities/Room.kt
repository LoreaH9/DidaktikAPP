package com.txurdinaga.didaktikapp.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.txurdinaga.didaktikapp.databinding.ActivityRoomBinding
import com.txurdinaga.didaktikapp.room.DataBaseRoomApp
import com.txurdinaga.didaktikapp.room.UsuarioEntity

class Room : AppCompatActivity() {
    private lateinit var binding : ActivityRoomBinding
    private lateinit var userList : List<UsuarioEntity>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRoomBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btInsertar.setOnClickListener {
            val usuario=UsuarioEntity(binding.tvnombre.text.toString(),0,0)
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