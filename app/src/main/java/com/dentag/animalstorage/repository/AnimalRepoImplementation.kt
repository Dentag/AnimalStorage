package com.dentag.animalstorage.repository

import android.content.Context
import com.dentag.App
import com.dentag.animalstorage.R
import com.dentag.animalstorage.cursor.AnimalSQLiteOpenHelper
import com.dentag.animalstorage.room.Animal
import com.dentag.animalstorage.room.AnimalDatabase

class AnimalRepoImplementation(
    private val database: AnimalDatabase = App.instance.database,
    private val sql: AnimalSQLiteOpenHelper = AnimalSQLiteOpenHelper(),
    private val prefs: PreferenceRepository = PreferenceRepositoryImplementation(),
    private val context: Context = App.instance
) : AnimalRepository {

    override suspend fun putAnimal(animal: Animal) {
        if (prefs.checkIfRoom()) {
            database.animalDao.insert(animal)
        } else {
            sql.add(animal)
        }
    }

    override suspend fun removeAnimal(animal: Animal) {
        if (prefs.checkIfRoom()) {
            database.animalDao.delete(animal)
        } else {
            sql.remove(animal)
        }
    }

    override suspend fun updateAnimal(animal: Animal) {
        if (prefs.checkIfRoom()) {
            database.animalDao.update(animal)
        } else {
            sql.update(animal)
        }
    }

    override suspend fun getAllOnce(): List<Animal> {
        val result = if (prefs.checkIfRoom()) {
            database.animalDao.getAllOnce()
        } else {
            sql.getListOfAnimals()
        }

        return when (prefs.getSortBy()) {
            context.getString(R.string.nameField) -> result.sortedBy { it.name }
            context.getString(R.string.ageField) -> result.sortedBy { it.age }
            context.getString(R.string.breedField) -> result.sortedBy { it.breed }
            else -> throw Exception("No filter found")
        }
    }
}
