package com.txurdinaga.didaktikapp

class Params {
    companion object {
        fun getFondo(set:Int): Int {
            return when (set) {
                1 -> R.drawable.fondo_1
                2 -> R.drawable.fondo_2
                3 -> R.drawable.fondo_3
                4 -> R.drawable.fondo_4
                5 -> R.drawable.fondo_5
                6 -> R.drawable.fondo_6
                7 -> R.drawable.fondo_7
                else -> 0
            }
        }

        fun getPersonaje(set:Int): Int {
            return when (set) {
                1 -> R.drawable.perso_1
                2 -> R.drawable.perso_2
                3 -> R.drawable.perso_3
                4 -> R.drawable.perso_4
                5 -> R.drawable.perso_5
                6 -> R.drawable.perso_6
                7 -> R.drawable.perso_7
                else -> 0
            }
        }

        fun getPistas(set: Int) : List<Int> {
            return when (set) {
                1 -> listOf(R.string.pista1_1, R.string.pista1_2, R.string.pista1_3)
                2 -> listOf(R.string.pista2_1, R.string.pista2_2, R.string.pista2_3)
                3 -> listOf(R.string.pista3_1, R.string.pista3_2, R.string.pista3_3)
                4 -> listOf(R.string.pista4_1, R.string.pista4_2, R.string.pista4_3)
                5 -> listOf(R.string.pista5_1, R.string.pista5_2, R.string.pista5_3)
                6 -> listOf(R.string.pista6_1, R.string.pista6_2, R.string.pista6_3)
                7 -> listOf(R.string.pista7_1, R.string.pista7_2, R.string.pista7_3)
                else -> listOf()
            }
        }

        fun getEnunciado(set:Int): Int {
            return when (set) {
                1 -> R.string.enunciado1
                2 -> R.string.enunciado2
                3 -> R.string.enunciado3
                4 -> R.string.enunciado4
                5 -> R.string.enunciado5
                6 -> R.string.enunciado6
                7 -> R.string.enunciado7
                else -> 0
            }
        }

        fun getContrasena(set:Int): Int {
            return when (set) {
                1 -> R.string.contrasena1
                2 -> R.string.contrasena2
                3 -> R.string.contrasena3
                4 -> R.string.contrasena4
                5 -> R.string.contrasena5
                6 -> R.string.contrasena6
                7 -> R.string.contrasena7
                else -> 0
            }
        }

        fun getExplicacion(set:Int): Int {
            return when (set) {
                1 -> R.string.explicacion1
                2 -> R.string.explicacion2
                3 -> R.string.explicacion3
                4 -> R.string.explicacion4
                5 -> R.string.explicacion5
                6 -> R.string.explicacion6
                7 -> R.string.explicacion7
                else -> 0
            }
        }

        fun getEnhorabuena(set:Int): Int {
            return when (set) {
                2 -> R.string.enhorabuena2
                4 -> R.string.enhorabuena4
                5 -> R.string.enhorabuena5
                6 -> R.string.enhorabuena6
                7 -> R.string.enhorabuena7
                else -> 0
            }
        }
    }
}