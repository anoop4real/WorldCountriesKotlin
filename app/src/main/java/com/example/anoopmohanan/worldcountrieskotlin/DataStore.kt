package com.example.anoopmohanan.worldcountrieskotlin

import java.util.*

/**
 * Created by anoopmohanan on 28/11/17.
 */


object DataStore{

    private  var isFilterApplied = false
    private  var filteredArray   = arrayListOf<Country>()
    var normalArray     = arrayListOf<Country>()

    fun preparedata() {
        for (countryCode in Locale.getISOCountries()) {
            val locale = Locale("",countryCode)
            var countryName: String? = locale.displayCountry
            if (countryName == null) {
                countryName = "UnIdentified"
            }
            val simpleCountry = Country(countryName,countryCode)
            normalArray.add(simpleCountry)
        }
        normalArray = ArrayList(normalArray.sortedWith(compareBy { it.countryName }))
    }
}