package com.example.anoopmohanan.worldcountrieskotlin.Database

import android.arch.persistence.room.Delete
import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query
import android.arch.persistence.room.OnConflictStrategy




@Dao
interface CountryDAO {
    @get:Query("SELECT * FROM countries")
    val all: List<CountryDBItem>

    @Query("SELECT * FROM countries WHERE country_name LIKE :name")
    fun loadAllStartsWith(name: String): List<CountryDBItem>

    @Query("SELECT * FROM countries WHERE capital_name LIKE :name")
    fun findByName(name: String): CountryDBItem

    @Insert
    fun insert(countries: ArrayList<CountryDBItem>)

    @Delete
    fun delete(country: CountryDBItem)
}
