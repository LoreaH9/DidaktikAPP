package com.txurdinaga.didaktikapp.room

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters

@Entity
class UsuarioEntity(
    @PrimaryKey
    var nombre:String,
    var profesor: Int,
    var modoLibre: Int,
    )