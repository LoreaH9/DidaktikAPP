package com.txurdinaga.didaktikapp.fragments

import android.annotation.SuppressLint
import android.content.DialogInterface
import android.content.Intent
import android.location.Location
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.ImageView
import androidx.appcompat.app.AlertDialog
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
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
import com.txurdinaga.didaktikapp.ActividadesProvider
import com.txurdinaga.didaktikapp.Constantes.Zunzunegui
import com.txurdinaga.didaktikapp.Constantes.nombre_paradas
import com.txurdinaga.didaktikapp.Constantes.paradas
import com.txurdinaga.didaktikapp.activities.MainDialogo
import com.txurdinaga.didaktikapp.R
import com.txurdinaga.didaktikapp.SharedPrefs
import com.txurdinaga.didaktikapp.databinding.DialogActividad7Binding
import com.txurdinaga.didaktikapp.databinding.DialogActividadBinding
import com.txurdinaga.didaktikapp.databinding.FragmentMapaBinding
import com.txurdinaga.didaktikapp.dialog.DialogNombre

@Suppress("DEPRECATION")
class FragmentMapa : Fragment() {

    lateinit var ubicacion: LatLng
    lateinit var binding: FragmentMapaBinding
    private lateinit var fusedLocation: FusedLocationProviderClient
    lateinit var googleMap: GoogleMap
    var marcadores: ArrayList<Marker> = arrayListOf()

    @SuppressLint("MissingPermission")
    private val callback = OnMapReadyCallback { googleMap ->
        paradas.forEachIndexed { index, it ->
            val marcador = googleMap.addMarker(MarkerOptions().position(it).title(nombre_paradas[index]))
            if (marcador != null) marcadores.add(marcador)
        }
        SharedPrefs.idioma.aldatu(SharedPrefs.idioma.idioma, resources)
        googleMap.mapType = GoogleMap.MAP_TYPE_HYBRID

        if (SharedPrefs.tipousu.tipo == "alumno") {
            googleMap.isMyLocationEnabled = true
            googleMap.uiSettings.isMyLocationButtonEnabled = false
            googleMap.uiSettings.isCompassEnabled = false

            if(!SharedPrefs.modolibre.modo) {
                marcadores.forEach {
                    cambiarMarcador(it, SharedPrefs.puntopartida.partida.toInt()) // cambia el color del marcador dependiendo por cual vaya
                }
            } else {
                marcadores.forEach{
                    if(SharedPrefs.hecho_libre[marcadores.indexOf(it)]){
                        it.setIcon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN))
                    } else {
                        it.setIcon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED))
                    }

                }
            }

            fusedLocation.lastLocation.addOnSuccessListener {
                if (it != null) {
                    ubicacion = LatLng(it.latitude, it.longitude)
                    googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(Zunzunegui, 15.5f))
                    //googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(ubicacion, 15.5f))
                }
            }
        }

        if(SharedPrefs.modolibre.modo){
            googleMap.setOnMarkerClickListener { marker ->
                setFragmentResult("libre", bundleOf("punto" to marker.id.substring(1,2).toInt()))
                true
            }
        }
       // googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(Zunzunegui, 15.5f))
        googleMap.setOnMyLocationChangeListener {
            ubicacion= LatLng(it.latitude, it.longitude)
            //googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(ubicacion, 15.5f))
            //googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(ubicacion, 17f))
            val distancia=FloatArray(3)

            //Distancia con las paradas
            if (SharedPrefs.puntopartida.partida == "0"){
                Location.distanceBetween(ubicacion.latitude, ubicacion.longitude, paradas[SharedPrefs.puntopartida.partida.toInt()].latitude, paradas[SharedPrefs.puntopartida.partida.toInt()].longitude,distancia)
            } else if (SharedPrefs.puntopartida.partida.toInt() in 1..7){
                Location.distanceBetween(ubicacion.latitude, ubicacion.longitude, paradas[SharedPrefs.puntopartida.partida.toInt()-1].latitude, paradas[SharedPrefs.puntopartida.partida.toInt()-1].longitude,distancia)
            }

            //Distancia con CIFP Txurdinaga LHII
            if (distancia[0]<50){
                setFragmentResult("mapa", bundleOf("rango" to "yes"))
            }else{
                setFragmentResult("mapa", bundleOf("rango" to "no"))
            }
        }

        if(SharedPrefs.tipousu.tipo=="profesor"){
            googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(Zunzunegui, 15.5f))
            googleMap.setOnMarkerClickListener { marker ->
                setFragmentResult("libre", bundleOf("punto" to marker.id.substring(1,2).toInt()))
                true
            }
        }

        googleMap.setOnMarkerClickListener{
            when (it.id) {
                "m0" -> showActivityDialog(it,1)
                "m1" -> showActivityDialog(it,2)
                "m2" -> showActivityDialog(it,3)
                "m3" -> showActivityDialog(it,4)
                "m4" -> showActivityDialog(it,5)
                "m5" -> showActivityDialog(it,6)
                "m6" -> showActivityDialog(it,7)
                else -> {showErrorDialog("Error", "Error")}
            }
        }

        if(!SharedPrefs.modolibre.modo){
            binding.piezasTV.visibility = View.VISIBLE
            binding.piezasIV.visibility = View.VISIBLE
            binding.piezasTV.setText("${SharedPrefs.puntopartida.partida}/6")
            if(SharedPrefs.puntopartida.partida == "7")
                binding.piezasTV.setText("6/6")

        }

        binding.UbicacionButton.setOnClickListener {
            googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(ubicacion, 15.5f))
        }
        binding.LocationJButton.setOnClickListener {
            googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(Zunzunegui, 15.5f))
        }
    }

    fun showActivityDialog(marker:Marker, set: Int): Boolean {
        // val image = ImageView(requireContext())
        //image.setImageResource(R.drawable.usuarios4)
        AlertDialog.Builder(requireContext())
            .setView(getView(set))
            .setTitle("${getString(R.string.actividad)} $set")
            .setMessage(getString(ActividadesProvider.actividad[set].nombre))
            .setPositiveButton(R.string.jugar
            ) { _, _ ->
                if(SharedPrefs.modolibre.modo){
                    startActivity(Intent(requireContext(), MainDialogo::class.java)
                            .putExtra("set", set))
                } else {
                    if(SharedPrefs.puntopartida.partida.toInt()+1 >= set) {
                        startActivity(Intent(requireContext(), MainDialogo::class.java)
                            .putExtra("set", set))
                    } else {
                        AlertDialog.Builder(requireContext())
                            .setTitle(R.string.error)
                            .setMessage(R.string.motivo_error1)
                            .setPositiveButton(R.string.aceptar
                            ) { _, _ ->
                            }
                            .create()
                            .show()
                    }
                }
            }
            .setCancelable(true)
            .create()
            .show()
        return true
    }

    fun getView(set:Int): FrameLayout {
        if(!SharedPrefs.modolibre.modo && set==7){
            val bindingActividad7 : DialogActividad7Binding = DialogActividad7Binding.inflate(layoutInflater)
            val list = listOf(bindingActividad7.fondo7IV1, bindingActividad7.fondo7IV2, bindingActividad7.fondo7IV3,
                bindingActividad7.fondo7IV4, bindingActividad7.fondo7IV5, bindingActividad7.fondo7IV6)
            list.forEach { it.alpha = 0.6F }
            for(i in 0 until SharedPrefs.puntopartida.partida.toInt())
                list[i].alpha = 1F
            return bindingActividad7.root
        } else{
            val bindingActividad : DialogActividadBinding = DialogActividadBinding.inflate(layoutInflater)
            bindingActividad.img.setImageResource(ActividadesProvider.actividad[set].fondo)
            return bindingActividad.root
        }
    }

    fun showErrorDialog(title: String, message: String): Boolean {
        AlertDialog.Builder(requireContext())
            .setTitle(title)
            .setMessage(message)
            .setPositiveButton(R.string.si) { _, _ ->
            }
            .setNegativeButton(R.string.no
            ) { _, _ ->
            }
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
    ): View {
       // DialogInicio()
        binding = FragmentMapaBinding.inflate(layoutInflater)

        binding.UbicacionButton.setOnClickListener {
            if(!SharedPrefs.modolibre.modo) {
                fusedLocation.lastLocation.addOnSuccessListener {
                    ubicacion = LatLng(it.latitude, it.longitude)
                    googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(ubicacion, 15.5f))
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

    fun cambiarMarcador(marker:Marker, posicion:Int){
        if(marcadores.indexOf(marker) < posicion) {
            marker.setIcon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN))
        } else if(marcadores.indexOf(marker) == posicion){
            marker.setIcon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_CYAN))
        } else {
            marker.setIcon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED))
        }
        //it.isVisible = marcadores.indexOf(it) <= (posicion)
    }



}
