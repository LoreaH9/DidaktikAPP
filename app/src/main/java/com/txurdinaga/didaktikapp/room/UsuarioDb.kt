package com.txurdinaga.didaktikapp.room

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(entities = [UsuarioEntity::class], version = 1)
abstract class UsuarioDb: RoomDatabase() {
    abstract val usuarioDao: UsuarioDao

    companion object {
        const val DATABASE_NAME = "usuarios"
    }
}