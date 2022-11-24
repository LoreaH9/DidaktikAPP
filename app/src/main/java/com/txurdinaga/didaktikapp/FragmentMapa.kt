package com.txurdinaga.didaktikapp

import android.annotation.SuppressLint
import android.content.DialogInterface
import android.location.Location
import androidx.fragment.app.Fragment
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.core.os.bundleOf
import androidx.fragment.app.setFragmentResult
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
import com.txurdinaga.didaktikapp.Constantes.Zunzunegui
import com.txurdinaga.didaktikapp.Constantes.nombre_paradas
import com.txurdinaga.didaktikapp.Constantes.paradas
import com.txurdinaga.didaktikapp.databinding.FragmentMapaBinding

@Suppress("DEPRECATION")
class FragmentMapa : Fragment() {

    lateinit var ubicacion:LatLng
    lateinit var binding: FragmentMapaBinding
    private lateinit var fusedLocation: FusedLocationProviderClient
    lateinit var googleMap: GoogleMap
    var marcadores:ArrayList<Marker> = arrayListOf()

    @SuppressLint("MissingPermission")
    private val callback = OnMapReadyCallback { googleMap ->
        paradas.forEachIndexed { index, it ->
            val marcador = googleMap.addMarker(MarkerOptions().position(it).title(nombre_paradas[index]))
            if (marcador != null) marcadores.add(marcador)
        }

        if (SharedPrefs.tipousu.tipo == "alumno") {
            googleMap.isMyLocationEnabled = true
            googleMap.uiSettings.isMyLocationButtonEnabled = false
            googleMap.uiSettings.isCompassEnabled = false

            SharedPrefs.puntopartida.Partida = "2" //se pone la partida por la que va el alumno
            cambiarMarcador(SharedPrefs.puntopartida.Partida.toInt()) // cambia el color del marcador dependiendo por cual vaya

            fusedLocation.lastLocation.addOnSuccessListener {
                if (it != null) {
                    ubicacion = LatLng(it.latitude, it.longitude)
                    googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(ubicacion, 15.5f))
                }
            }
        }

        if(SharedPrefs.modolibre.modo){
            //ubicacion = LatLng(43.321841, -3.019356)
            googleMap.setOnMarkerClickListener { marker ->
                //Genera un mensaje "Prueba: "+mX .Donde X es la id del marcador
                println("Prueba: "+marker.id)
                setFragmentResult("libre", bundleOf("punto" to marker.id.substring(1,2).toInt()))
                true
            }
        }

        googleMap.setOnMyLocationChangeListener {
            ubicacion= LatLng(it.latitude, it.longitude)
            //googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(ubicacion, 17f))
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

        if(SharedPrefs.tipousu.tipo=="profesor"){
            googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(Zunzunegui, 15f))
            googleMap.setOnMarkerClickListener { marker ->
                //Genera un mensaje "Prueba: "+mX .Donde X es la id del marcador
                println("Prueba: "+marker.id)
                setFragmentResult("libre", bundleOf("punto" to marker.id.substring(1,2).toInt()))
                true
            }
        }
        googleMap.setOnMarkerClickListener{
            Log.i("1",it.toString())
            println("Prueba:" + it.id)
            when (it.id) {
                "m0" -> showActivityDialog(it,requireContext().resources.getString(R.string.error),requireContext().resources.getString(R.string.error))
                "m1" -> showActivityDialog(it,requireContext().resources.getString(R.string.error),requireContext().resources.getString(R.string.error))
                "m2" -> showActivityDialog(it,requireContext().resources.getString(R.string.error),requireContext().resources.getString(R.string.error))
                "m3" -> showActivityDialog(it,requireContext().resources.getString(R.string.error),requireContext().resources.getString(R.string.error))
                "m4" -> showActivityDialog(it,requireContext().resources.getString(R.string.error),requireContext().resources.getString(R.string.error))
                "m5" -> showActivityDialog(it,requireContext().resources.getString(R.string.error),requireContext().resources.getString(R.string.error))
                "m6" -> showActivityDialog(it,requireContext().resources.getString(R.string.error),requireContext().resources.getString(R.string.error))
                else -> {showErrorDialog(it,"Error","Error")}
            }
           // showErrorDialog(it, requireContext().resources.getString(R.string.error) , requireContext().resources.getString(R.string.motivo_error1) )
        }
    }

    fun showActivityDialog(marker: Marker, title: String, message:String): Boolean {
        AlertDialog.Builder(requireContext())
            .setTitle(title)
            .setMessage(message)
            .setPositiveButton(R.string.si,
                DialogInterface.OnClickListener { dialog, id ->
                })
            .setNegativeButton(R.string.no,
                DialogInterface.OnClickListener { _, id ->
                })
            .setCancelable(false)
            .create()
            .show()
        return true
    }

    fun showErrorDialog(marker: Marker, title: String, message:String): Boolean {
        AlertDialog.Builder(requireContext())
            .setTitle(title)
            .setMessage(message)
            .setPositiveButton(R.string.si,
                DialogInterface.OnClickListener { dialog, id ->
                })
            .setNegativeButton(R.string.no,
                DialogInterface.OnClickListener { _, id ->
                })
            .setCancelable(false)
            .create()
            .show()
        return false
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