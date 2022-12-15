package com.txurdinaga.didaktikapp.room

import androidx.room.TypeConverter
import org.json.JSONObject
import java.util.*

class JuegoTypeConverter {
    @TypeConverter
    fun fromSource(source: Juego): String {
        return JSONObject().apply {
            put("id", source.id)
            put("name", source.name)
        }.toString()
    }

    @TypeConverter
    fun toSource(source: String): Juego {
        val json = JSONObject(source)
        return Juego(json.get("id"), json.getString("name"))
    }
}