package com.txurdinaga.didaktikapp

import android.annotation.SuppressLint
import android.app.ProgressDialog.show
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.location.Location
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.appcompat.app.AlertDialog
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
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

        //DialogNombre().show(parentFragmentManager, "LoginDialog")


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
            println("Prueba:" + it.id)
            when (it.id) {
                "m0" -> showActivityDialog(it,requireContext().resources.getString(R.string.actividad_1),requireContext().resources.getString(R.string.titulo_actividad_1),1)
                "m1" -> showActivityDialog(it,requireContext().resources.getString(R.string.actividad_2),requireContext().resources.getString(R.string.titulo_actividad_2),2)
                "m2" -> showActivityDialog(it,requireContext().resources.getString(R.string.actividad_3),requireContext().resources.getString(R.string.titulo_actividad_3),3)
                "m3" -> showActivityDialog(it,requireContext().resources.getString(R.string.actividad_4),requireContext().resources.getString(R.string.titulo_actividad_4),4)
                "m4" -> showActivityDialog(it,requireContext().resources.getString(R.string.actividad_5),requireContext().resources.getString(R.string.titulo_actividad_5),5)
                "m5" -> showActivityDialog(it,requireContext().resources.getString(R.string.actividad_6),requireContext().resources.getString(R.string.titulo_actividad_6),6)
                "m6" -> showActivityDialog(it,requireContext().resources.getString(R.string.actividad_7),requireContext().resources.getString(R.string.titulo_actividad_7),7)
                else -> {showErrorDialog(it,"Error","Error")}
            }
           // showErrorDialog(it, requireContext().resources.getString(R.string.error) , requireContext().resources.getString(R.string.motivo_error1) )
        }

        binding.UbicacionButton.setOnClickListener {
            googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(ubicacion, 15.5f))
        }
    }

    fun showActivityDialog(marker: Marker, title: String, message:String, set: Int): Boolean {
        val image = ImageView(requireContext())
        image.setImageResource(R.drawable.usuarios4)
        AlertDialog.Builder(requireContext())
            .setView(image)
            .setTitle(title)
            .setMessage(message)
            .setPositiveButton(R.string.jugar,
                DialogInterface.OnClickListener { dialog, id ->
                    startActivity(Intent(requireContext(),MainDialogo::class.java)
                        .putExtra("set", set)
                    )
                })
            .setCancelable(true)
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
       // DialogInicio()
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

/*
    fun DialogInicio(){
        AlertDialog.Builder(requireContext())
            .setTitle("USUARIO")
            .setMessage("INSERTA TU NOMBRE DE USUARIO")
            .setPositiveButton("ENTRAR",
                DialogInterface.OnClickListener { dialog, id ->
                })

            .setNeutralButton("SOY PROFESOR",
                DialogInterface.OnClickListener { _, id ->
                })
            .setCancelable(false)
            .create()
            .show()
    }
*/

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