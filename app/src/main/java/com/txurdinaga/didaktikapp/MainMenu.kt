package com.txurdinaga.didaktikapp

import DialogRegistro
import android.Manifest
import android.content.DialogInterface
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.TextView
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

        //Comprueba los permisos de navegación
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED)
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),1)

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
        if (SharesPrefs.users.user == ""){
            menu.findItem(R.id.nav_logout).isVisible = false
            navigationView.getHeaderView(0).findViewById<TextView>(R.id.headerApodo).text = getString(R.string.invitado)
            navigationView.getHeaderView(0).findViewById<TextView>(R.id.headerPunto).text = ""
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.nav_mapa ->supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, FragmentMapa()).commit()
            R.id.nav_profesor ->
                DialogRegistro().show(supportFragmentManager, "MyCustomFragment")
            R.id.nav_idioma ->
                showBasicDialog()
            R.id.nav_informacion -> supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, FragmentInformacion()).commit()
            R.id.nav_desconectar ->
                showBasicDialog()
                }
        drawerLayout!!.closeDrawer(GravityCompat.START)
        return true
    }

    private fun showBasicDialog() {
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

    override fun onBackPressed() {
        if (drawerLayout!!.isDrawerOpen(GravityCompat.START)) {
            drawerLayout!!.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }
}