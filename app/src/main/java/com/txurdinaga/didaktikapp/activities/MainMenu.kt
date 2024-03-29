@file:Suppress("DEPRECATION")

package com.txurdinaga.didaktikapp.activities

import android.content.DialogInterface
import com.txurdinaga.didaktikapp.dialog.DialogProfesor
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.TextView
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView
import com.txurdinaga.didaktikapp.Constantes.modo
import com.txurdinaga.didaktikapp.R
import com.txurdinaga.didaktikapp.SharedPrefs
import com.txurdinaga.didaktikapp.databinding.LayoutMenuBinding
import com.txurdinaga.didaktikapp.fragments.FragmentInformacion
import com.txurdinaga.didaktikapp.fragments.FragmentMapa
import java.util.*
import kotlin.system.exitProcess


class MainMenu : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {
    private var drawerLayout: DrawerLayout? = null
    lateinit var binding: LayoutMenuBinding
    lateinit var menu: Menu

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        SharedPrefs.idioma.aldatu(SharedPrefs.idioma.idioma, resources)
        binding = LayoutMenuBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val toolbar = binding.toolbar
        setSupportActionBar(toolbar)

        drawerLayout = binding.drawerLayout
        val navigationView = binding.navView
        menu = navigationView.menu

        //Inserta navbar con sus opciones
        navigationView.setNavigationItemSelectedListener(this)
        val toggle =ActionBarDrawerToggle(this, drawerLayout, toolbar,
            R.string.open_nav,
            R.string.close_nav)

        drawerLayout!!.addDrawerListener(toggle)


        toggle.syncState()

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, FragmentMapa()).commit()
            navigationView.setCheckedItem(R.id.nav_mapa)
        }

        //En caso de no haber usuario pone el invitado por defecto
        if (SharedPrefs.users.user == ""){
            menu.findItem(R.id.nav_logout).isVisible = false
            SharedPrefs.users.user = getString(R.string.invitado)
            navigationView.getHeaderView(0).findViewById<TextView>(R.id.headerPunto).text = "0"
        }
        if(SharedPrefs.modolibre.modo) {
            navigationView.getHeaderView(0).findViewById<TextView>(R.id.headerApodo).text = "Modo libre"
            navigationView.getHeaderView(0).findViewById<TextView>(R.id.headerPunto).text = "-"
        } else
            navigationView.getHeaderView(0).findViewById<TextView>(R.id.headerPunto).text = SharedPrefs.puntopartida.partida
            navigationView.getHeaderView(0).findViewById<TextView>(R.id.headerApodo).text = SharedPrefs.users.user
    
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.nav_mapa -> supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, FragmentMapa()).commit()
            R.id.nav_profesor ->{
                DialogProfesor().show(supportFragmentManager, "LoginDialog")
            }
            R.id.nav_desconectar ->
                showCloseAppDialog()
            R.id.nav_home ->
                showHomeDialog()
             R.id.nav_idioma ->
                 showIdiomaDialog()
            R.id.nav_informacion ->supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, FragmentInformacion()).commit()
            R.id.nav_logout ->
                showLogOutDialog()
            R.id.nav_tema ->
                temaldatu()
            }
        drawerLayout!!.closeDrawer(GravityCompat.START)
        return true
    }

    private fun showLogOutDialog() {
        AlertDialog.Builder(this)
            .setTitle(R.string.cerrar_sesion)
            .setMessage(R.string.quieres_cerrar_sesion)
            .setPositiveButton(R.string.si
            ) { _, _ ->
                SharedPrefs.users.user = ""
                SharedPrefs.tipousu.tipo = "alumno"
                startActivity(Intent(this, MainInicio::class.java))
            }
            .setNegativeButton(R.string.no
            ) { _, _ ->
            }
            .setCancelable(false)
            .create()
            .show()
    }

    private fun temaldatu(){
        AlertDialog.Builder(this)
            .setTitle(R.string.cambiar_tema)
            .setMessage(R.string.quieres_cambiar_tema)
            .setPositiveButton(R.string.si
            ) { _, _ ->
                modo = if (modo == 0) {
                    setdaynight(1)
                    1
                } else {
                    setdaynight(0)
                    0
                }
                var intent = Intent(this, MainInicio::class.java)
                startActivity(intent)

            }
            .setNegativeButton(R.string.no
            ) { _, _ ->

            }
            .setCancelable(false)
            .create()
            .show()
        }



    private fun showCloseAppDialog() {
        AlertDialog.Builder(this)
            .setTitle(R.string.salir)
            .setMessage(R.string.seguro_salir)
            .setPositiveButton(R.string.si
            ) { _, _ ->
                finishAffinity()
                exitProcess(0)
            }
            .setNegativeButton(R.string.no
            ) { _, _ ->
            }
            .setCancelable(false)
            .create()
            .show()
    }

    private fun showHomeDialog(){
        AlertDialog.Builder(this)
            .setTitle(R.string.salir)
            .setMessage(R.string.seguro_salir_home)
            .setPositiveButton(R.string.si
            ) { _, _ ->
                startActivity(Intent(this, MainInicio::class.java))
            }
            .setNegativeButton(R.string.no
            ) { _, _ ->
            }
            .setCancelable(false)
            .create()
            .show()
    }

    private fun showIdiomaDialog(){
        if(SharedPrefs.idioma.idioma == "eu") {
            AlertDialog.Builder(this)
                .setTitle(R.string.titulo_cambiar_idioma)
                .setMessage(R.string.seguro_cambiar_idioma)
                .setPositiveButton(R.string.si
                ) { _, _ ->
                    SharedPrefs.idioma.aldatu("es", resources)
                    val intent = Intent(this, MainMenu::class.java)
                    startActivity(intent)
                }
                .setNegativeButton(R.string.no
                ) { _, _ ->
                }
                .setCancelable(false)
                .create()
                .show()

        } else {
            AlertDialog.Builder(this)
                .setTitle(R.string.titulo_cambiar_idioma)
                .setMessage(R.string.seguro_cambiar_idioma)
                .setPositiveButton(R.string.si
                ) { _, _ ->
                    SharedPrefs.idioma.aldatu("eu", resources)
                    val intent = Intent(this, MainMenu::class.java)
                    startActivity(intent)
                }
                .setNegativeButton(R.string.no
                ) { _, _ ->
                }
                .setCancelable(false)
                .create()
                .show()
        }
    }

    @Deprecated("Deprecated in Java")
    override fun onBackPressed() {
        if (drawerLayout!!.isDrawerOpen(GravityCompat.START))
            drawerLayout!!.closeDrawer(GravityCompat.START)
         else super.onBackPressed()
    }

    fun setdaynight(mode:Int){
        if (mode==1){
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
        }
        else{
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        }
    }


}
