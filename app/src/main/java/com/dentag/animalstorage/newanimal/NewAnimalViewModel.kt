package com.dentag.animalstorage.newanimal

import android.database.sqlite.SQLiteConstraintException
import androidx.lifecycle.ViewModel
import com.dentag.animalstorage.Router
import com.dentag.animalstorage.repository.AnimalRepoImplementation
import com.dentag.animalstorage.repository.AnimalRepository
import com.dentag.animalstorage.room.Animal
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch

class NewAnimalViewModel(
    private val repository: AnimalRepository = AnimalRepoImplementation(),
    private var router: Router? = null
) : ViewModel() {

    private val newAnimalScope = CoroutineScope(
        Dispatchers.Main +
            SupervisorJob()
    )

    fun setRouter(r: Router) {
        router = r
    }

    fun putAnimal(name: String, age: String, breed: String) {
        val animal = Animal(name, age.toInt(), breed)
        newAnimalScope.launch {
            try {
                repository.putAnimal(animal)
            } catch (e: SQLiteConstraintException) {
                repository.updateAnimal(animal)
            } finally {
                router?.goBack()
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        newAnimalScope.cancel()
        router = null
    }
}
