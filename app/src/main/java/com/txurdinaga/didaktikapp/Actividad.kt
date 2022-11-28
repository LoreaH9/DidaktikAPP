package com.txurdinaga.didaktikapp

data class Actividad(
    var set: Int,               // el escenario que es, del 0 al 8
    var perso: Int,             // el personaje2, con quien ali va a conversar
    var fondo: Int,             // el fondo sobre el que se desarrolla la actividad
    var dialogo: List<Linea>,    // las lineas del dialogo, con su texto y quien lo dice (true=ali, false=personaje2)
    var pistas: List<Int>,      // las 3 pistas para descifrar la contraseña
    var enunciado: Int,         // el enunciado para preguntar la contraseña
    var contrasena: Int,        // la contraseña correcta
    var explicacion: Int,       // la explicacion del juego
    var enhorabuena: Int,        // el texto que se les muestra para dar la enhorabuena(solo en 2,4,5,6,7)
    var main: Class<*>?
)

class Linea(
    var texto: Int,
    var bocadillo: Int,
    var ali: Int)
