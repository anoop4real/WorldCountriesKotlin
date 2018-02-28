package com.example.anoopmohanan.worldcountrieskotlin

import android.content.DialogInterface
import android.graphics.drawable.PictureDrawable
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.Toast
import com.ahmadrosid.svgloader.SvgLoader
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions


import kotlinx.android.synthetic.main.activity_detail.*
import kotlinx.android.synthetic.main.content_detail.*

class DetailActivity : AppCompatActivity(), OnMapReadyCallback {


    var location:LatLng = LatLng(-33.862, 151.21)
    val ZOOM_LEVEL = 5f
    private var mMap: GoogleMap? = null

    override fun onMapReady(googleMap: GoogleMap?) {

        googleMap ?: return
        mMap = googleMap
        with(googleMap) {
            moveCamera(com.google.android.gms.maps.CameraUpdateFactory.newLatLngZoom(location, ZOOM_LEVEL))
            addMarker(com.google.android.gms.maps.model.MarkerOptions().position(location))
            googleMap.uiSettings.setAllGesturesEnabled(true)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        setSupportActionBar(toolbar)

        val intent = intent
        val countryCode = intent.getStringExtra("CountryName")
        println("Country code is"+ countryCode)

        val mapFragment : SupportMapFragment? =
                supportFragmentManager.findFragmentById(R.id.mapFragment) as? SupportMapFragment
        mapFragment?.getMapAsync(this)
        DataStore.getCountryDataWith(countryCode,{result ->
            if(result!= null){

                when (result.status){

                    Result.Status.ERROR->{
                            Toast.makeText(this,"Error:"+ result.exception?.message, Toast.LENGTH_LONG).show()
                    }
                    Result.Status.SUCCESS ->{
                        val countryData = result.data
                        this.areaViewVal.text  = countryData?.area.toString()
                        this.nameVal.text = countryData?.name
                        this.nativeNameVal.text = countryData?.nativeName
                        this.regionViewVal.text = countryData?.region
                        this.capitalViewVal.text = countryData?.capital
                        this.location =  LatLng(countryData?.latlng?.get(0)!!, countryData?.latlng?.get(1)!!)
                        mMap?.moveCamera(com.google.android.gms.maps.CameraUpdateFactory.newLatLngZoom(location, ZOOM_LEVEL))
                        mMap?.addMarker(com.google.android.gms.maps.model.MarkerOptions().position(location))

                        SvgLoader.pluck()
                                .with(this)
                                .load(countryData?.flag,this.imageView)

                    }
                }

            }
        })
                        
//        DataStore.getCountryDataWith(countryCode,{countryData ->         this.areaViewVal.text  = countryData?.area.toString()
//            this.nameVal.text = countryData?.name
//            this.nativeNameVal.text = countryData?.nativeName
//            this.regionViewVal.text = countryData?.region
//            this.capitalViewVal.text = countryData?.capital})


    }
    fun goBack(view: View){

        showAlertDialogWith("Do you really want to go back")

    }

    fun loadImageFrom(url: String){



    }

    private fun navigateBack(){

        this.finish()
    }

    private fun showAlertDialogWith(message: String){

        val alert = AlertDialog.Builder(this)
                .setTitle("Warning")
                .setMessage(message)
                .setPositiveButton("Yes",DialogInterface.OnClickListener { _ , i ->
                        navigateBack()
                })
                .setNegativeButton("No",DialogInterface.OnClickListener { dialogInterface, i ->

                        dialogInterface.dismiss()
                })

        alert.show()
    }

}
