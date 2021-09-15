package com.dentag.animalstorage.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.dentag.animalstorage.DATABASE_VERSION

@Database(entities = [Animal::class], version = DATABASE_VERSION)
abstract class AnimalDatabase : RoomDatabase() {
    abstract val animalDao: AnimalDao
}
