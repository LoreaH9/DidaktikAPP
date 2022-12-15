package com.txurdinaga.didaktikapp.room

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(entities = [Usuario::class], version = 1)
@TypeConverters(JuegoTypeConverter::class)
abstract class UsuarioDb: RoomDatabase() {
    abstract val usuarioDao: UsuarioDao

    companion object {
        const val DATABASE_NAME = "usuarios"
    }
}