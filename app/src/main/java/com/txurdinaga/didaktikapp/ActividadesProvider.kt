package com.txurdinaga.didaktikapp

import com.txurdinaga.didaktikapp.actividades.*

class ActividadesProvider {
    companion object {
        val actividad: List<Actividad> = listOf(
            Actividad(
                set = 0,
                perso = R.drawable.vacio,
                fondo = R.drawable.fondo_0,
                dialogo =  listOf(
                    Linea(R.string.vacio, R.drawable.bocadillo_1, R.drawable.ali_1),
                    Linea(R.string.dialogo0_1, R.drawable.bocadillo_1, R.drawable.ali_2),
                    Linea(R.string.dialogo0_2, R.drawable.bocadillo_1, R.drawable.ali_3),
                    Linea(R.string.dialogo0_3, R.drawable.bocadillo_1, R.drawable.ali_3),
                    Linea(R.string.dialogo0_4, R.drawable.bocadillo_1, R.drawable.ali_3),
                    Linea(R.string.dialogo0_5, R.drawable.bocadillo_1, R.drawable.ali_2)
                ),
                pistas = listOf(),
                enunciado = R.string.vacio,
                contrasena = R.string.vacio,
                explicacion = R.string.vacio,
                enhorabuena = R.string.vacio,
                main = null,
                layout = 0,
                nombre = 0
            ),
            Actividad(
                set = 1,
                perso = R.drawable.perso_1,
                fondo = R.drawable.fondo_1,
                dialogo =  mutableListOf(
                    Linea(R.string.vacio, R.drawable.bocadillo_1, R.drawable.ali_1),
                    Linea(R.string.dialogo1_1, R.drawable.bocadillo_1, R.drawable.ali_2),
                    Linea(R.string.dialogo1_2, R.drawable.bocadillo_2, R.drawable.ali_1),
                    Linea(R.string.dialogo1_3, R.drawable.bocadillo_1, R.drawable.ali_3),
                    Linea(R.string.dialogo1_4, R.drawable.bocadillo_2, R.drawable.ali_1),
                    Linea(R.string.dialogo1_5, R.drawable.bocadillo_1, R.drawable.ali_2),
                    Linea(R.string.dialogo1_6, R.drawable.bocadillo_2, R.drawable.ali_1),
                    Linea(R.string.dialogo1_7, R.drawable.bocadillo_1, R.drawable.ali_3),
                    Linea(R.string.dialogo1_8, R.drawable.bocadillo_2, R.drawable.ali_1)
                ),
                pistas = listOf(
                    R.string.pista1_1,
                    R.string.pista1_2,
                    R.string.pista1_3),
                enunciado = R.string.enunciado1,
                contrasena = R.string.contrasena1,
                explicacion = R.string.explicacion1,
                enhorabuena = R.string.vacio,
                main = MainActividad1::class.java,
                layout = R.layout.fragment_actividad_1,
                nombre = R.string.titulo_actividad_1
            ),
            Actividad(
                set = 2,
                perso = R.drawable.perso_2,
                fondo = R.drawable.fondo_2,
                dialogo =  mutableListOf(
                    Linea(R.string.vacio, R.drawable.bocadillo_1, R.drawable.ali_1),
                    Linea(R.string.dialogo2_1, R.drawable.bocadillo_1, R.drawable.ali_2),
                    Linea(R.string.dialogo2_2, R.drawable.bocadillo_2, R.drawable.ali_1),
                    Linea(R.string.dialogo2_3, R.drawable.bocadillo_1, R.drawable.ali_3),
                    Linea(R.string.dialogo2_4, R.drawable.bocadillo_2, R.drawable.ali_1),
                    Linea(R.string.dialogo2_5, R.drawable.bocadillo_2, R.drawable.ali_1),
                    Linea(R.string.dialogo2_6, R.drawable.bocadillo_2, R.drawable.ali_1),
                    Linea(R.string.dialogo2_7, R.drawable.bocadillo_2, R.drawable.ali_1),
                    Linea(R.string.dialogo2_8, R.drawable.bocadillo_2, R.drawable.ali_1),
                    Linea(R.string.dialogo2_9, R.drawable.bocadillo_1, R.drawable.ali_2),
                    Linea(R.string.dialogo2_10, R.drawable.bocadillo_2, R.drawable.ali_1)
                ),
                pistas = listOf(
                    R.string.pista2_1,
                    R.string.pista2_2,
                    R.string.pista2_3),
                enunciado = R.string.enunciado2,
                contrasena = R.string.contrasena2,
                explicacion = R.string.explicacion2,
                enhorabuena = R.string.enhorabuena2,
                main = MainActividad2::class.java,
                layout = R.layout.fragment_actividad_2,
                nombre = R.string.titulo_actividad_2
            ),
            Actividad(
                set = 3,
                perso = R.drawable.perso_3,
                fondo = R.drawable.fondo_3,
                dialogo =  mutableListOf(
                    Linea(R.string.vacio, R.drawable.bocadillo_1, R.drawable.ali_1),
                    Linea(R.string.dialogo3_1, R.drawable.bocadillo_2, R.drawable.ali_1),
                    Linea(R.string.dialogo3_2, R.drawable.bocadillo_1, R.drawable.ali_2),
                    Linea(R.string.dialogo3_3, R.drawable.bocadillo_2, R.drawable.ali_1),
                    Linea(R.string.dialogo3_4, R.drawable.bocadillo_2, R.drawable.ali_1),
                    Linea(R.string.dialogo3_5, R.drawable.bocadillo_1, R.drawable.ali_2),
                    Linea(R.string.dialogo3_6, R.drawable.bocadillo_2, R.drawable.ali_1)
                ),
                pistas = listOf(
                    R.string.pista3_1,
                    R.string.pista3_2,
                    R.string.pista3_3),
                enunciado = R.string.enunciado3,
                contrasena = R.string.contrasena3,
                explicacion = R.string.explicacion3,
                enhorabuena = R.string.vacio,
                main = MainActividad3::class.java,
                layout = R.layout.fragment_actividad_3,
                nombre = R.string.titulo_actividad_3
            ),
            Actividad(
                set = 4,
                perso = R.drawable.perso_4,
                fondo = R.drawable.fondo_4,
                dialogo =  mutableListOf(
                    Linea(R.string.vacio, R.drawable.bocadillo_1, R.drawable.ali_1),
                    Linea(R.string.dialogo4_1, R.drawable.bocadillo_1, R.drawable.ali_1),
                    Linea(R.string.dialogo4_2, R.drawable.bocadillo_2, R.drawable.ali_1),
                    Linea(R.string.dialogo4_3, R.drawable.bocadillo_1, R.drawable.ali_3),
                    Linea(R.string.dialogo4_4, R.drawable.bocadillo_2, R.drawable.ali_1),
                    Linea(R.string.dialogo4_5, R.drawable.bocadillo_1, R.drawable.ali_2),
                    Linea(R.string.dialogo4_6, R.drawable.bocadillo_2, R.drawable.ali_1),
                    Linea(R.string.dialogo4_7, R.drawable.bocadillo_1, R.drawable.ali_2),
                    Linea(R.string.dialogo4_8, R.drawable.bocadillo_2, R.drawable.ali_1)
                ),
                pistas = listOf(
                    R.string.pista4_1,
                    R.string.pista4_2,
                    R.string.pista4_3),
                enunciado = R.string.enunciado4,
                contrasena = R.string.contrasena4,
                explicacion = R.string.explicacion4,
                enhorabuena = R.string.enhorabuena4,
                main = MainActividad4::class.java,
                layout = R.layout.fragment_actividad_4,
                nombre = R.string.titulo_actividad_4
            ),
            Actividad(
                set = 5,
                perso = R.drawable.perso_5,
                fondo = R.drawable.fondo_5,
                dialogo =  mutableListOf(
                    Linea(R.string.vacio, R.drawable.bocadillo_1, R.drawable.ali_1),
                    Linea(R.string.dialogo5_1, R.drawable.bocadillo_2, R.drawable.ali_1),
                    Linea(R.string.dialogo5_2, R.drawable.bocadillo_1, R.drawable.ali_3),
                    Linea(R.string.dialogo5_3, R.drawable.bocadillo_2, R.drawable.ali_1),
                    Linea(R.string.dialogo5_4, R.drawable.bocadillo_1, R.drawable.ali_3),
                    Linea(R.string.dialogo5_5, R.drawable.bocadillo_2, R.drawable.ali_1),
                    Linea(R.string.dialogo5_6, R.drawable.bocadillo_1, R.drawable.ali_2),
                    Linea(R.string.dialogo5_7, R.drawable.bocadillo_2, R.drawable.ali_1),
                    Linea(R.string.dialogo5_8, R.drawable.bocadillo_2, R.drawable.ali_1)
                ),
                pistas = listOf(
                    R.string.pista5_1,
                    R.string.pista5_2,
                    R.string.pista5_3),
                enunciado = R.string.enunciado5,
                contrasena = R.string.contrasena5,
                explicacion = R.string.explicacion5,
                enhorabuena = R.string.enhorabuena5,
                main = MainActividad5::class.java,
                layout = R.layout.fragment_actividad_5,
                nombre = R.string.titulo_actividad_5
            ),
            Actividad(
                set = 6,
                perso = R.drawable.perso_6,
                fondo = R.drawable.fondo_6,
                dialogo =  mutableListOf(
                    Linea(R.string.vacio, R.drawable.bocadillo_1, R.drawable.ali_1),
                    Linea(R.string.dialogo6_1, R.drawable.bocadillo_1, R.drawable.ali_1),
                    Linea(R.string.dialogo6_2, R.drawable.bocadillo_2, R.drawable.ali_1),
                    Linea(R.string.dialogo6_3, R.drawable.bocadillo_1, R.drawable.ali_1),
                    Linea(R.string.dialogo6_4, R.drawable.bocadillo_2, R.drawable.ali_1),
                    Linea(R.string.dialogo6_5, R.drawable.bocadillo_1, R.drawable.ali_3),
                    Linea(R.string.dialogo6_6, R.drawable.bocadillo_2, R.drawable.ali_1)
                ),
                pistas = listOf(
                    R.string.pista6_1,
                    R.string.pista6_2,
                    R.string.pista6_3),
                enunciado = R.string.enunciado6,
                contrasena = R.string.contrasena6,
                explicacion = R.string.explicacion6,
                enhorabuena = R.string.enhorabuena6,
                main = MainActividad6::class.java,
                layout = R.layout.fragment_actividad_6,
                nombre = R.string.titulo_actividad_6
            ),
            Actividad(
                set = 7,
                perso = R.drawable.perso_7,
                fondo = R.drawable.fondo_7,
                dialogo =  mutableListOf(
                    Linea(R.string.vacio, R.drawable.bocadillo_1, R.drawable.ali_1),
                    Linea(R.string.dialogo7_1, R.drawable.bocadillo_1, R.drawable.ali_1),
                    Linea(R.string.dialogo7_2, R.drawable.bocadillo_2, R.drawable.ali_1),
                    Linea(R.string.dialogo7_3, R.drawable.bocadillo_2, R.drawable.ali_1),
                    Linea(R.string.dialogo7_4, R.drawable.bocadillo_1, R.drawable.ali_3),
                    Linea(R.string.dialogo7_5, R.drawable.bocadillo_2, R.drawable.ali_1)
                ),
                pistas = listOf(
                    R.string.pista7_1,
                    R.string.pista7_2,
                    R.string.pista7_3),
                enunciado = R.string.enunciado7,
                contrasena = R.string.contrasena7,
                explicacion = R.string.explicacion7,
                enhorabuena = R.string.enhorabuena7,
                main = MainActividad7::class.java,
                layout = R.layout.fragment_actividad_7,
                nombre = R.string.titulo_actividad_7
            ),
            Actividad(
                set = 8,
                perso = R.drawable.vacio,
                fondo = R.drawable.fondo_0,
                dialogo =  mutableListOf(
                    Linea(R.string.vacio, R.drawable.bocadillo_1, R.drawable.ali_1),
                    Linea(R.string.dialogo8_1, R.drawable.bocadillo_1, R.drawable.ali_2),
                    Linea(R.string.dialogo8_2, R.drawable.bocadillo_1, R.drawable.ali_3)
                ),
                pistas = listOf(),
                enunciado = R.string.vacio,
                contrasena = R.string.vacio,
                explicacion = R.string.vacio,
                enhorabuena = R.string.vacio,
                main = null,
                layout = 0,
                nombre = 0
            )
        )
    }
}