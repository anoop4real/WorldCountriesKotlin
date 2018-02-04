package com.example.anoopmohanan.worldcountrieskotlin

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*

/**
 * Created by anoopmohanan on 28/11/17.
 */


object DataStore{

    private  var isFilterApplied = false
    private  var filteredArray   = arrayListOf<Country>()
    var normalArray     = arrayListOf<Country>()
    var countryData:CountryData? = null

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

    /**
     * Function to retrieve the country info by taking in the country code
     */
    fun getCountryDataWith(countryCode: String, responseCallback: (Result<CountryData>) -> Unit){

        // Create the API service from the factory class
        val apiService = ApiInterface.ApiServiceFactory.create()
        apiService.getCountryData(countryCode).enqueue(object : Callback<CountryData> {
            override fun onResponse(call: Call<CountryData>, response: Response<CountryData>) {
                val countryInfo = response.body()
                countryData = countryInfo
                println("Request Success")
                print("CountryData is" + countryInfo)
                println("Country Name is" + countryInfo!!.name)
                responseCallback(Result.success(countryData))
            }

            override fun onFailure(call: Call<CountryData>, t: Throwable) {
                //Handle failure
                responseCallback(Result.error(Exception("Unable to fetch data")))
                println("Request failed")
            }
        }

        )

    }
}

//interface Movable{
//
//     fun startEngine()
//}
//
//class Car( val color: String, var numberOfWheels: Int):Movable{
//    override fun startEngine() {
//    }
//
//
//}
//class Bike( val color: String, var numberOfWheels: Int):Movable{
//    override fun startEngine() {
//    }
//
//}
