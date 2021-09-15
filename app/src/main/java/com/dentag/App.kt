package com.dentag

import android.app.Application
import androidx.room.Room
import com.dentag.animalstorage.DATABASE_NAME
import com.dentag.animalstorage.room.AnimalDatabase

class App : Application() {
    lateinit var database: AnimalDatabase

    override fun onCreate() {
        super.onCreate()
        instance = this
        database =
            Room.databaseBuilder(applicationContext, AnimalDatabase::class.java, DATABASE_NAME)
                .build()
    }

    companion object {
        lateinit var instance: App
    }
}
