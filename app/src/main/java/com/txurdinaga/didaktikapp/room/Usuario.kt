package com.txurdinaga.didaktikapp.room

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class Usuario(
    @PrimaryKey
    var nombre:String,
    var profesor: Int,
    var modoLibre: Int,
    var juegosCompletados: List<String>

)