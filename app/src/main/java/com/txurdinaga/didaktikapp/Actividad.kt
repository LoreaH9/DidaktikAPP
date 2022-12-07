package com.txurdinaga.didaktikapp

data class Actividad(
    var set: Int,               // el escenario que es, del 0 al 8
    var perso: Int,             // el personaje2, con quien ali va a conversar
    var fondo: Int,             // el fondo sobre el que se desarrolla la actividad
    var dialogo: List<Linea>,   // las lineas del dialogo, con su texto y quien lo dice (true=ali, false=personaje2)
    var pistas: List<Int>,      // las 3 pistas para descifrar la contraseña
    var enunciado: Int,         // el enunciado para preguntar la contraseña
    var contrasena: Int,        // la contraseña correcta
    var explicacion: Int,       // la explicacion del juego
    var enhorabuena: Int,       // el texto que se les muestra para dar la enhorabuena(solo en 2,4,5,6,7)
    var main: Class<*>?         // la clase en la que se encuentra la actividad
)

class Linea(
    var texto: Int,             // el texto que dice el personaje en esta linea
    var bocadillo: Int,         // la imagen del bocadillo que se usara en esta linea
    var ali: Int                // la imagen de ali que se usara en esta linea
)