package com.txurdinaga.didaktikapp

import androidx.fragment.app.Fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

class FragmentMapa : Fragment() {
    var BasilicaStaMaria = LatLng(43.319806, -3.016722)
    var PlazaSRoque = LatLng(43.3179121, -3.020576198034128)
    var TorreSalazar = LatLng(43.32024476997707, -3.0171418190002446)
    val Mercado = LatLng( 43.32164439841235, -3.0177814777832044)
    val Cascoviejo = LatLng(  43.3207146004226, -3.0178300457686014)
    val Zunzunegui = LatLng(  43.32254620046105, -3.0185475097471284)
    val puente = LatLng(  43.323242893062826, -3.017147915508491)

    private val callback = OnMapReadyCallback { googleMap ->

        googleMap.addMarker(MarkerOptions().position(BasilicaStaMaria).title("Basilica Sta Maria"))
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(BasilicaStaMaria,15.5f))

        googleMap.addMarker(MarkerOptions().position(PlazaSRoque).title("Plaza San Roque"))
        googleMap.moveCamera(CameraUpdateFactory.newLatLng(PlazaSRoque))

        googleMap.addMarker(MarkerOptions().position(TorreSalazar).title("Torre Salazar"))
        googleMap.moveCamera(CameraUpdateFactory.newLatLng(TorreSalazar))

        googleMap.addMarker(MarkerOptions().position(Mercado).title("Mercado"))
        googleMap.moveCamera(CameraUpdateFactory.newLatLng(Mercado))

        googleMap.addMarker(MarkerOptions().position(Cascoviejo).title("Casco Viejo"))
        googleMap.moveCamera(CameraUpdateFactory.newLatLng(Cascoviejo))

        googleMap.addMarker(MarkerOptions().position(Zunzunegui).title("Zunzunegui"))
        googleMap.moveCamera(CameraUpdateFactory.newLatLng(Zunzunegui))

        googleMap.addMarker(MarkerOptions().position(puente).title("Puente"))
        googleMap.moveCamera(CameraUpdateFactory.newLatLng(puente))
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        return inflater.inflate(R.layout.fragment_mapa, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val mapFragment = childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment?
        mapFragment?.getMapAsync(callback)
    }
}