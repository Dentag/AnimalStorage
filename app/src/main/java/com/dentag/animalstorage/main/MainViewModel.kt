package com.dentag.animalstorage.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dentag.animalstorage.Router
import com.dentag.animalstorage.repository.AnimalRepoImplementation
import com.dentag.animalstorage.repository.AnimalRepository
import com.dentag.animalstorage.room.Animal
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel(
    private val animalRepository: AnimalRepository = AnimalRepoImplementation(),
    private var router: Router? = null,
) : ViewModel() {

    private val _singleData = MutableLiveData<List<Animal>>()
    val singleData: LiveData<List<Animal>> = _singleData

    fun setupRouter(r: Router) {
        router = r
    }

    fun getOnce() {
        viewModelScope.launch {
            launch(Dispatchers.IO) {
                _singleData.postValue(animalRepository.getAllOnce())
            }
        }
    }

    fun removeAnimal(animal: Animal) {
        viewModelScope.launch {
            animalRepository.removeAnimal(animal)
        }
    }

    fun goSettingsScreen() {
        router?.goSettingsScreen()
    }

    fun newAnimal() {
        router?.goNewAnimalScreen()
    }

    fun updateAnimal(animal: Animal) {
        router?.goNewAnimalScreen(animal)
    }

    override fun onCleared() {
        super.onCleared()
        router = null
    }
}
