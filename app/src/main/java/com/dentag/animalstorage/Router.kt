package com.dentag.animalstorage

import com.dentag.animalstorage.room.Animal

interface Router {
    fun goMainScreen()
    fun goNewAnimalScreen()
    fun goNewAnimalScreen(animal: Animal)
    fun goSettingsScreen()
    fun goBack()
}
