package com.example.anoopmohanan.worldcountrieskotlin.Database

import android.arch.persistence.room.RoomDatabase
import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.content.Context


//@Database(entities = arrayOf(CountryDBItem::class), version = 1)
//abstract class CountryDataBase : RoomDatabase() {
//    abstract fun countryDao(): CountryDAO
//}


@Database(entities = arrayOf(CountryDBItem::class), version = 1)
abstract class CountryDataBase : RoomDatabase() {

    abstract fun countryDao(): CountryDAO

    companion object {

        private var INSTANCE: CountryDataBase? = null


        internal fun getDatabase(context: Context): CountryDataBase {
            if (INSTANCE == null) {
                synchronized(CountryDataBase::class.java) {
                    if (INSTANCE == null) {
                        INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                                CountryDataBase::class.java, "country_database")
                                .build()

                    }
                }
            }
            return INSTANCE!!
        }
    }

}

