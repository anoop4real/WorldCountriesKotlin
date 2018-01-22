package com.example.anoopmohanan.worldcountrieskotlin

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initialize()

//        for (country in arr){
//
//            println("Country is $country.countryName")
//
//        }

    }
    fun initialize(){

        DataStore.preparedata()

        if (DataStore.normalArray.isEmpty()){
            Log.d("Error","Fatal Error Datasource not ready")
            return
        }
        val countryList = countryListView
        var layoutManager = LinearLayoutManager(this)
        countryList.layoutManager = layoutManager
        countryList.addItemDecoration(DividerItemDecoration(this,DividerItemDecoration.VERTICAL))
        val adapter = CountryDataAdapter(DataStore.normalArray)
        countryList.adapter = adapter
    }


}
