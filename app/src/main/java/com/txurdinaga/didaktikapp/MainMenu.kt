package com.txurdinaga.didaktikapp

import android.content.DialogInterface
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView
import androidx.appcompat.widget.Toolbar;
import com.txurdinaga.didaktikapp.databinding.LayoutMenuBinding


class MainMenu : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {
    private var drawerLayout: DrawerLayout? = null
    lateinit var binding: LayoutMenuBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = LayoutMenuBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val toolbar: Toolbar = binding.toolbar //Ignore red line errors
        setSupportActionBar(toolbar)
        drawerLayout = binding.drawerLayout
        val navigationView = binding.navView
        navigationView.setNavigationItemSelectedListener(this)
        var toggle: ActionBarDrawerToggle? =
            ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.open_nav,
                R.string.close_nav)
        if (toggle != null) {
            drawerLayout!!.addDrawerListener(toggle)
        }

        toggle?.syncState()

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, FragmentMapa()).commit()
            navigationView.setCheckedItem(R.id.nav_mapa)
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.nav_mapa ->supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, FragmentMapa()).commit()
            R.id.nav_profesor ->
                showBasicDialog()
            R.id.nav_idioma ->
                showBasicDialog()
            R.id.nav_informacion -> supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, FragmentInformacion()).commit()
            R.id.nav_desconectar -> Toast.makeText(this, "Logout!", Toast.LENGTH_SHORT).show()
        }
        drawerLayout!!.closeDrawer(GravityCompat.START)
        return true
    }

    private fun showBasicDialog() {
        AlertDialog.Builder(this)
            .setTitle("Login profesor")
            .setPositiveButton("Aceptar",
                DialogInterface.OnClickListener { dialog, id ->
                })
            .setNegativeButton("Cancelar",
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