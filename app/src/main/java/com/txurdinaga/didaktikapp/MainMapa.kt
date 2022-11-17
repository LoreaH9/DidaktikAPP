package com.txurdinaga.didaktikapp

import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView
import com.txurdinaga.didaktikapp.databinding.ActivityMainMapaBinding
import androidx.appcompat.widget.Toolbar;


class MainMapa : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {
    private var drawerLayout: DrawerLayout? = null
    lateinit var binding: ActivityMainMapaBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainMapaBinding.inflate(layoutInflater)
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
                .replace(R.id.fragment_container, FragmentInformacion()).commit()
            navigationView.setCheckedItem(R.id.nav_informacion)
        }
    }



    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.nav_profesor -> supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, FragmentProfesor()).commit()
            R.id.nav_idioma -> supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, FragmentIdioma()).commit()
            R.id.nav_informacion -> supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, FragmentInformacion()).commit()
            R.id.nav_desconectar -> Toast.makeText(this, "Logout!", Toast.LENGTH_SHORT).show()
        }
        drawerLayout!!.closeDrawer(GravityCompat.START)
        return true
    }

    override fun onBackPressed() {
        if (drawerLayout!!.isDrawerOpen(GravityCompat.START)) {
            drawerLayout!!.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }
}