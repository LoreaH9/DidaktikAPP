package com.txurdinaga.didaktikapp

import DialogLogin
import android.Manifest
import android.content.DialogInterface
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat
import com.txurdinaga.didaktikapp.databinding.LayoutMenuBinding
import kotlin.system.exitProcess


class MainMenu : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {
    private var drawerLayout: DrawerLayout? = null
    lateinit var binding: LayoutMenuBinding
    lateinit var menu: Menu

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = LayoutMenuBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val toolbar: Toolbar = binding.toolbar
        setSupportActionBar(toolbar)

        drawerLayout = binding.drawerLayout
        val navigationView = binding.navView
        menu = navigationView.menu

        //Inserta navbar con sus opciones
        navigationView.setNavigationItemSelectedListener(this)
        var toggle: ActionBarDrawerToggle? =ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.open_nav, R.string.close_nav)

        if (toggle != null) {
            drawerLayout!!.addDrawerListener(toggle)
        }

        toggle?.syncState()

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, FragmentMapa()).commit()
            navigationView.setCheckedItem(R.id.nav_mapa)
        }

        //En caso de no haber usuario pone el invitado por defecto
        if (SharedPrefs.users.user == ""){
            menu.findItem(R.id.nav_logout).isVisible = false
            navigationView.getHeaderView(0).findViewById<TextView>(R.id.headerApodo).text = getString(R.string.invitado)
            navigationView.getHeaderView(0).findViewById<TextView>(R.id.headerPunto).text = "0"
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.nav_mapa -> supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, FragmentMapa()).commit()
            R.id.nav_profesor ->
                DialogLogin().show(supportFragmentManager, "LoginDialog")
            R.id.nav_desconectar ->
                showCloseAppDialog()
            R.id.nav_home ->
                showHomeDialog()
             R.id.nav_idioma ->
                 showIdiomaDialog()
            R.id.nav_idioma ->
                showIdiomaDialog()
            R.id.nav_informacion ->
                acerca()
            }

        drawerLayout!!.closeDrawer(GravityCompat.START)
        return true
    }

    private fun showCloseAppDialog() {
        AlertDialog.Builder(this)
            .setTitle(R.string.salir)
            .setMessage(R.string.seguro_salir)
            .setPositiveButton(R.string.si,
                DialogInterface.OnClickListener { dialog, id ->
                    finishAffinity()
                    exitProcess(0)
                })
            .setNegativeButton(R.string.no,
                DialogInterface.OnClickListener { _, id ->
                })
            .setCancelable(false)
            .create()
            .show()
    }

    private fun showHomeDialog(){
        AlertDialog.Builder(this)
            .setTitle(R.string.salir)
            .setMessage(R.string.seguro_salir_home)
            .setPositiveButton(R.string.si,
                DialogInterface.OnClickListener { dialog, id ->
                    var intent= Intent(this,MainInicio::class.java)
                    startActivity(intent)
                })
            .setNegativeButton(R.string.no,
                DialogInterface.OnClickListener { _, id ->
                })
            .setCancelable(false)
            .create()
            .show()
    }

    private fun showIdiomaDialog(){
        AlertDialog.Builder(this)
            .setTitle("Cambiar Idioma")
            .setMessage(R.string.seguro_cambiar_idioma)
            .setPositiveButton(R.string.si,
                DialogInterface.OnClickListener { dialog, id ->

                })
            .setNegativeButton(R.string.no,
                DialogInterface.OnClickListener { _, id ->
                })
            .setCancelable(false)
            .create()
            .show()
    }

    override fun onBackPressed() {
        if (drawerLayout!!.isDrawerOpen(GravityCompat.START)) {
            drawerLayout!!.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    private fun acerca(){
        var intent= Intent(this,FragmentInformacion::class.java)
        startActivity(intent)
    }
}