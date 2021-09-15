package com.dentag.animalstorage.repository

import com.dentag.animalstorage.room.Animal

interface AnimalRepository {
    suspend fun putAnimal(animal: Animal)
    suspend fun removeAnimal(animal: Animal)
    suspend fun updateAnimal(animal: Animal)
    suspend fun getAllOnce(): List<Animal>
}
