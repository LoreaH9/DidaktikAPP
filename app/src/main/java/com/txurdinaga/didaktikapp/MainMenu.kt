package com.txurdinaga.didaktikapp

import android.content.DialogInterface
import android.os.Bundle
import android.view.MenuItem
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView
import com.txurdinaga.didaktikapp.databinding.ActivityMainMapaBinding
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat
import com.google.android.material.snackbar.Snackbar


class MainMenu : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {
    private var drawerLayout: DrawerLayout? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = LayoutMapaBinding.inflate(layoutInflater)
        setContentView(binding.root)
        fusedLocation = LocationServices.getFusedLocationProviderClient(this)
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)

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
}