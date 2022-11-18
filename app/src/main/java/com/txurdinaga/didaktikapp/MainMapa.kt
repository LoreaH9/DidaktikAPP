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



class MainMapa: AppCompatActivity(), OnMapReadyCallback, NavigationView.OnNavigationItemSelectedListener{
    private lateinit var fusedLocation: FusedLocationProviderClient
    private lateinit var mMap: GoogleMap
    private lateinit var binding: LayoutMapaBinding
    private var ultimomarcador: Marker?=null
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
    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
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
    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap

        /*

            if (ActivityCompat.checkSelfPermission(
                    this, android.Manifest.permission.ACCESS_FINE_LOCATION)
                !=PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                    this, android.Manifest.permission.ACCESS_COARSE_LOCATION)
                !=PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(
                    this, arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION,
                        android.Manifest.permission.ACCESS_COARSE_LOCATION),
                    1)
                return }

            mMap.isMyLocationEnabled=true
            mMap.uiSettings.isCompassEnabled=true
            mMap.uiSettings.isZoomControlsEnabled=true


    //posición en tiempo real
            fusedLocation.lastLocation.addOnSuccessListener {location->
                if (location!=null){
                    val ubicacion=LatLng(location.latitude, location.longitude)
                    mMap.addMarker(MarkerOptions().position(ubicacion).title("ubicación"))
                                //mMap.moveCamera(CameraUpdateFactory.newLatLng(ubicacion))
                                //si queremos movimiento más lento de la camara: animate
                                //y en vez de newLatLng podemos poner con zoom
                                mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(ubicacion,6f))
                }
            }

            mMap.setOnMapLongClickListener {
                //marcador en la posición
                val markerOptions=MarkerOptions().position(it)
                mMap.addMarker(markerOptions)

                if (ultimomarcador!=null)
                    ultimomarcador!!.remove()
                ultimomarcador= mMap.addMarker(markerOptions)
                mMap.addMarker(markerOptions)
                //desplazo la camara
                mMap.animateCamera(CameraUpdateFactory.newLatLng(it))
            }

*/
            // Add a marker in Sydney and move the camera
            val BasilicaStaMaria = LatLng(43.319806, -3.016722)
            mMap.addMarker(
                MarkerOptions()
                    .position(BasilicaStaMaria)
                    .title("Basílica de Santa María")
                    .snippet("43.319806,-3.016722")
                    )
            mMap.moveCamera(CameraUpdateFactory.newLatLng(BasilicaStaMaria))

            val camerapos= CameraPosition.builder()
                .target(BasilicaStaMaria)
                .zoom(17f)
                .bearing(90f)
                .tilt(30f)
                .build()
            mMap.animateCamera(CameraUpdateFactory.newCameraPosition(camerapos))


        val PlazaSRoque = LatLng(43.3179121, -3.020576198034128)
        mMap.addMarker(
            MarkerOptions()
                .position(PlazaSRoque)
                .title("Plaza de San Roque")
                .snippet("43.3179121,-3.020576198034128")
        )
        mMap.moveCamera(CameraUpdateFactory.newLatLng(PlazaSRoque))


        val TorreSalazar = LatLng(43.32024476997707, -3.0171418190002446)
        mMap.addMarker(
            MarkerOptions()
                .position(TorreSalazar)
                .title("Torre de Salazar")
                .snippet("43.32024476997707,3.0171418190002446")
        )
        mMap.moveCamera(CameraUpdateFactory.newLatLng(TorreSalazar))



        val Mercado = LatLng( 43.32164439841235, -3.0177814777832044)
        mMap.addMarker(
            MarkerOptions()
                .position(Mercado)
                .title("Mercado de Portugalete")
                .snippet("43.32164439841235,-3.0177814777832044")
        )
        mMap.moveCamera(CameraUpdateFactory.newLatLng(Mercado))




        val Cascoviejo = LatLng(  43.3207146004226, -3.0178300457686014)
        mMap.addMarker(
            MarkerOptions()
                .position(Cascoviejo)
                .title("Calle de Víctor Chávarri (Casco Viejo)")
                .snippet("43.3207146004226,-3.0178300457686014")
        )
        mMap.moveCamera(CameraUpdateFactory.newLatLng(Cascoviejo))




        val Zunzunegui = LatLng(  43.32254620046105, -3.0185475097471284)
        mMap.addMarker(
            MarkerOptions()
                .position(Zunzunegui)
                .title("Busto de Zunzunegui")
                .snippet("43.32254620046105,-3.0185475097471284")
        )
        mMap.moveCamera(CameraUpdateFactory.newLatLng(Zunzunegui))


        val puente = LatLng(  43.323242893062826, -3.017147915508491)
        mMap.addMarker(
            MarkerOptions()
                .position(puente)
                .title("Puente Colgante")
                .snippet("43.323242893062826,-3.017147915508491")
        )
        mMap.moveCamera(CameraUpdateFactory.newLatLng(puente))


/*
        if (ActivityCompat.checkSelfPermission(
                this, android.Manifest.permission.ACCESS_FINE_LOCATION)
            != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                this, android.Manifest.permission.ACCESS_COARSE_LOCATION)
            != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(
                this, arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION,
                    android.Manifest.permission.ACCESS_COARSE_LOCATION),
                1)
            return }


        mMap.isMyLocationEnabled=true
        mMap.uiSettings.isZoomControlsEnabled=true
        mMap.uiSettings.isCompassEnabled=true
        //posición en tiempo real
        fusedLocation.lastLocation.addOnSuccessListener {location->
            if (location!=null){
                var ubicacion=LatLng(location.latitude, location.longitude)
                mMap.addMarker(MarkerOptions().position(ubicacion))
                mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(ubicacion,6f))}
        }
        mMap.setOnMapLongClickListener {
            //marcador en la posición
            var markerOptions=MarkerOptions().position(it)
            //cambiar icono
            markerOptions.icon(
                BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_CYAN) )
            markerOptions.title(obtener_direccion(it))
            //borrar el último marcador
            if (ultimomarcador!=null)
                ultimomarcador!!.remove()
            ultimomarcador= mMap.addMarker(markerOptions)
            mMap.addMarker(markerOptions)
            //desplazo la camara
            mMap.animateCamera(CameraUpdateFactory.newLatLng(it))

        }*/
    }


    fun obtener_direccion(latLng: LatLng):String{
        //variable para recoger la localización, usa la clase geocoder
        val geocoder= Geocoder(this)
        //me va a devolver una lista de direcciones posibles y nos quedamos con la primera
        var direcciones: List<Address>?
        val direccion1: Address
        var texto=""
        try{
            direcciones=geocoder.getFromLocation(latLng.latitude,latLng.longitude,1)
            if(direcciones!=null && direcciones.isNotEmpty())
            { direccion1=direcciones[0]
                //si la dirección tiene varias líneas
                if (direccion1.maxAddressLineIndex>0)
                { for (i in 0..direccion1.maxAddressLineIndex)
                    texto += direccion1.getAddressLine(i)+ "\n" }
                //si hay principal y secundario
                else{ texto += direccion1.thoroughfare + ", "+
                        direccion1.subThoroughfare + "\n" } }
        }catch (e:Exception){
            texto="No se encuentra la dirección"
        }
        return texto
    }
}