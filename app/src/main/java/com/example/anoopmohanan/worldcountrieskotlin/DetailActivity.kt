package com.example.anoopmohanan.worldcountrieskotlin

import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.view.View

import kotlinx.android.synthetic.main.activity_detail.*

class DetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        setSupportActionBar(toolbar)

        val intent = intent
        val countryCode = intent.getStringExtra("CountryName")
        println("Country code is"+ countryCode)

        DataStore.getCountryDataWith(countryCode)
    }
    fun goBack(view: View){

        this.finish()
    }

}
