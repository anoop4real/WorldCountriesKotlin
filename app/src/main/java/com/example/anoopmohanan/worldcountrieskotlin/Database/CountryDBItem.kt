package com.example.anoopmohanan.worldcountrieskotlin.Database

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey





@Entity(tableName = "countries")
class CountryDBItem {
    @PrimaryKey(autoGenerate = true)
    var uid: Int = 0

    @ColumnInfo(name = "country_name")
    var countryName: String? = null

    @ColumnInfo(name = "capital_name")
    var capitalName: String? = null

    @ColumnInfo(name = "population_count")
    var populationCount: Double? = null

    @ColumnInfo(name = "native_name")
    var nativeName: String? = null

}
