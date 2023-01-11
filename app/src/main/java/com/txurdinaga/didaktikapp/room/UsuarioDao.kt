package com.txurdinaga.didaktikapp.room

import androidx.room.*
import androidx.room.OnConflictStrategy.REPLACE
import java.nio.charset.CodingErrorAction.REPLACE

@Dao
interface UsuarioDao {
    @Query("SELECT * FROM Usuario ")
    fun selectAllUsers(): List<Usuario>

    @Query("SELECT * FROM Usuario WHERE nombre=:nombre")
    fun selectUsersByName(nombre: String): Usuario

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addUser(usuario: Usuario)

    @Delete
    fun deleteUser(usuario: Usuario)

}
