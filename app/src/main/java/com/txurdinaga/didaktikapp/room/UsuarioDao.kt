package com.txurdinaga.didaktikapp.room

import androidx.room.*
import androidx.room.OnConflictStrategy.REPLACE
import java.nio.charset.CodingErrorAction.REPLACE

@Dao
interface UsuarioDao {
    @Query("SELECT * FROM UsuarioEntity ")
    fun selectAllUsers(): List<UsuarioEntity>

    @Query("SELECT * FROM UsuarioEntity WHERE nombre=:nombre")
    fun selectUsersByName(nombre: String): UsuarioEntity

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addUser(usuario: UsuarioEntity)

    @Delete
    fun deleteUser(usuario: UsuarioEntity)

}
