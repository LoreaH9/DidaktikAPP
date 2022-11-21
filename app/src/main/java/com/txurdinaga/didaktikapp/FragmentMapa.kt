package com.txurdinaga.didaktikapp

import android.Manifest
import android.annotation.SuppressLint
import android.content.Intent
import android.content.pm.PackageManager
import android.content.pm.SharedLibraryInfo
import android.location.Location
import androidx.fragment.app.Fragment
import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.View.inflate
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.LinearLayout
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.setFragmentResult
import androidx.core.os.bundleOf
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions
import com.txurdinaga.didaktikapp.Constantes.paradas
import com.txurdinaga.didaktikapp.databinding.DialogProfesorBinding.inflate
import com.txurdinaga.didaktikapp.databinding.FragmentMapaBinding
import com.txurdinaga.didaktikapp.databinding.LayoutInicioBinding.inflate

@Suppress("DEPRECATION")
class FragmentMapa : Fragment() {

    lateinit var ubicacion:LatLng
    lateinit var binding: FragmentMapaBinding
    private lateinit var fusedLocation: FusedLocationProviderClient
    lateinit var googleMap: GoogleMap
    var marcadores:ArrayList<Marker> = arrayListOf()

    @SuppressLint("MissingPermission")
    private val callback = OnMapReadyCallback { googleMap ->
        if (!SharedPrefs.modolibre.modo || SharedPrefs.tipousu.tipo == "alumno") {
            SharedPrefs.puntopartida.Partida = "0"
            cambiarMarcador(SharedPrefs.puntopartida.Partida.toInt())
        }
        paradas.forEach {
            val marcador = googleMap.addMarker(MarkerOptions().position(it))
            if (marcador != null) marcadores.add(marcador)
        }
        if(!SharedPrefs.modolibre.modo) {
            googleMap.isMyLocationEnabled = true
            googleMap.uiSettings.isMyLocationButtonEnabled = false
            googleMap.uiSettings.isCompassEnabled = false
            fusedLocation.lastLocation.addOnSuccessListener {
                if (it != null) {
                    ubicacion = LatLng(it.latitude, it.longitude)
                    googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(ubicacion, 15f))
                }
            }
        }
        googleMap.setOnMyLocationChangeListener {
            ubicacion= LatLng(it.latitude, it.longitude)
            googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(ubicacion, 17f))
            val distancia=FloatArray(3)

            //Distancia con las paradas
            if (SharedPrefs.puntopartida.Partida == "0"){
                Location.distanceBetween(ubicacion.latitude, ubicacion.longitude, paradas[SharedPrefs.puntopartida.Partida.toInt()].latitude, paradas[SharedPrefs.puntopartida.Partida.toInt()].longitude,distancia)
            }else if (SharedPrefs.puntopartida.Partida.toInt() in 1..7){
                Location.distanceBetween(ubicacion.latitude, ubicacion.longitude, paradas[SharedPrefs.puntopartida.Partida.toInt()-1].latitude, paradas[SharedPrefs.puntopartida.Partida.toInt()-1].longitude,distancia)
            }

            //Distancia con CIFP Txurdinaga LHII
            if (distancia[0]<50){
                setFragmentResult("mapa", bundleOf("rango" to "yes"))
            }else{
                setFragmentResult("mapa", bundleOf("rango" to "no"))
            }
        }
        if(SharedPrefs.modolibre.modo){
            ubicacion = LatLng(43.321841, -3.019356)
            googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(ubicacion, 15f))
            googleMap.setOnMarkerClickListener { marker ->
                //Genera un mensaje "Prueba: "+mX .Donde X es la id del marcador
                println("Prueba: "+marker.id)
                setFragmentResult("libre", bundleOf("punto" to marker.id.substring(1,2).toInt()))
                true
            }
        }
    }

    @SuppressLint("MissingPermission")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = FragmentMapaBinding.inflate(layoutInflater)

        binding.UbicacionButton.setOnClickListener {

            if(!SharedPrefs.modolibre.modo) {
                fusedLocation.lastLocation.addOnSuccessListener {
                    ubicacion = LatLng(it.latitude, it.longitude)
                    googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(ubicacion, 15f))
                }
            }
        }
        fusedLocation = LocationServices.getFusedLocationProviderClient(requireActivity())

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val mapFragment = childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment?
        mapFragment?.getMapAsync(callback)
    }

    fun cambiarMarcador(posicion:Int){
        marcadores.forEach {
            when {
                marcadores.indexOf(it)<(posicion-1) -> {
                    it.setIcon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN))
                }
                marcadores.indexOf(it)==(posicion-1) -> {
                    it.setIcon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_ORANGE))
                }
                marcadores.indexOf(it)>(posicion-1) -> {
                    it.setIcon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED))
                }
            }
        }
    }


}