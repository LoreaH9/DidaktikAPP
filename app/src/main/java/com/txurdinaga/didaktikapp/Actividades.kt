package com.txurdinaga.didaktikapp

import android.content.Context
import android.content.DialogInterface
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageButton
import androidx.appcompat.app.AlertDialog
import com.txurdinaga.didaktikapp.databinding.FragmentActividad1Binding
import com.txurdinaga.didaktikapp.databinding.FragmentActividad2Binding
import com.txurdinaga.didaktikapp.databinding.FragmentActividad3Binding
import com.txurdinaga.didaktikapp.databinding.FragmentActividad4Binding
import com.txurdinaga.didaktikapp.databinding.FragmentActividad5Binding
import com.txurdinaga.didaktikapp.databinding.FragmentActividad6Binding
import com.txurdinaga.didaktikapp.databinding.FragmentActividad7Binding
import com.txurdinaga.didaktikapp.databinding.LayoutActividadBinding

interface Actividades {

    fun run1(binding: LayoutActividadBinding, layoutInflater: LayoutInflater) {
        var binding1 = FragmentActividad1Binding.inflate(layoutInflater)
        binding.containerVW.layoutResource = R.layout.fragment_actividad_1
        binding.containerVW.inflate()
    }

    fun run2(binding: LayoutActividadBinding, layoutInflater: LayoutInflater) {
        binding.containerVW.layoutResource = R.layout.fragment_actividad_2
        binding.containerVW.inflate()
    }

    fun run3(binding: LayoutActividadBinding, layoutInflater: LayoutInflater) {
        var binding3 = FragmentActividad3Binding.inflate(layoutInflater)
        binding.containerVW.layoutResource = R.layout.fragment_actividad_3
        binding.containerVW.inflate()
    }

    fun run4(binding: LayoutActividadBinding, layoutInflater: LayoutInflater) {
        var binding4 = FragmentActividad4Binding.inflate(layoutInflater)
        binding.containerVW.layoutResource = R.layout.fragment_actividad_4
        binding.containerVW.inflate()
    }

    fun run5(binding: LayoutActividadBinding, layoutInflater: LayoutInflater) {
        var binding5 = FragmentActividad5Binding.inflate(layoutInflater)
        binding.containerVW.layoutResource = R.layout.fragment_actividad_5
        binding.containerVW.inflate()
    }

    fun run6(binding: LayoutActividadBinding, layoutInflater: LayoutInflater) {
        var binding6 = FragmentActividad6Binding.inflate(layoutInflater)
        binding.containerVW.layoutResource = R.layout.fragment_actividad_6
        binding.containerVW.inflate()
    }

    fun run7(binding: LayoutActividadBinding, layoutInflater: LayoutInflater) {
        var binding7 = FragmentActividad7Binding.inflate(layoutInflater)
        binding.containerVW.layoutResource = R.layout.fragment_actividad_7
        binding.containerVW.inflate()
    }

}