package com.txurdinaga.didaktikapp.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.txurdinaga.didaktikapp.R
import com.txurdinaga.didaktikapp.SharedPrefs


class FragmentInformacion : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        SharedPrefs.idioma.aldatu(SharedPrefs.idioma.idioma, resources)
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_informacion, container, false)
    }
}