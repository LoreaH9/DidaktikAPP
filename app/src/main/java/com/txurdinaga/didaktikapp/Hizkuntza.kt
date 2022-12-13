package com.txurdinaga.didaktikapp

import android.content.res.Resources
import java.util.*

class Hizkuntza {

    companion object{
        var hizkuntza="eu"

        fun aldatu(hizkuntza_berria: String, resources: Resources) {
            hizkuntza = hizkuntza_berria
            val locale = Locale(hizkuntza)
            Locale.setDefault(locale)
            val resources = resources
            val configuration = resources.getConfiguration()
            configuration.setLocale(locale)
            resources.updateConfiguration(configuration, resources.getDisplayMetrics())
        }
    }
}