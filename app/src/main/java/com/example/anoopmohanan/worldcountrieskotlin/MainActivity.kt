package com.example.anoopmohanan.worldcountrieskotlin


import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import com.example.anoopmohanan.worldcountrieskotlin.Database.CountryDBItem
import kotlinx.android.synthetic.main.activity_main.*
import android.os.AsyncTask
import com.example.anoopmohanan.worldcountrieskotlin.Database.CountryDAO
import com.example.anoopmohanan.worldcountrieskotlin.Database.CountryDataBase


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

        var db = DataStore.getDataBase(applicationContext)
        DataStore.preparedata()

        PopulateDbAsync(db!!).execute()

        val currentDBPath = getDatabasePath("country_database").absolutePath
        println("DBPath is " + currentDBPath)
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

    private class PopulateDbAsync internal constructor(db: CountryDataBase) : AsyncTask<Void, Void, Void>() {

        private val mDao: CountryDAO

        init {
            mDao = db.countryDao()
        }

        override fun doInBackground(vararg params: Void): Void? {
            val arr = DataStore.normalArray
            val arr1 = ArrayList<CountryDBItem>()
            for (country1 in arr){
                val country = CountryDBItem()
                country.countryName = country1.countryName
                country.capitalName = country1.countryName
                country.nativeName  = country1.countryName
                country.populationCount = 146644.0
                arr1.add(country)
            }

            mDao.insert(arr1)

            val list = mDao.all
            return null
        }
    }

}
