package com.example.anoopmohanan.worldcountrieskotlin

import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path


/**
 * Created by anoopmohanan on 22/01/18.
 */
    interface ApiInterface {

    @GET("/rest/v2/alpha/{code}")
    fun getCountryData(@Path("code") code: String): Call<CountryData>

    /**
     *
     */
    object ApiServiceFactory {
        fun create(): ApiInterface {
            val retrofit = Retrofit.Builder()
                    .addConverterFactory(GsonConverterFactory.create())
                    .baseUrl(Constants.baseURL)
                    .build()

            return retrofit.create(ApiInterface::class.java);
        }
    }

}