package com.dentag.animalstorage

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.dentag.animalstorage.databinding.ActivityMainBinding
import com.dentag.animalstorage.main.MainFragment
import com.dentag.animalstorage.newanimal.NewAnimalFragment
import com.dentag.animalstorage.room.Animal

class MainActivity : AppCompatActivity(), Router {

    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        goMainScreen()
    }

    override fun goMainScreen() {
        supportFragmentManager.beginTransaction()
            .replace(binding.fragmentContainer.id, MainFragment())
            .commit()
    }

    override fun goNewAnimalScreen() {
        supportFragmentManager.beginTransaction()
            .replace(binding.fragmentContainer.id, NewAnimalFragment())
            .addToBackStack(null)
            .commit()
    }

    override fun goNewAnimalScreen(animal: Animal) {
        supportFragmentManager.beginTransaction()
            .replace(binding.fragmentContainer.id, NewAnimalFragment.newInstance(animal))
            .addToBackStack(null)
            .commit()
    }

    override fun goSettingsScreen() {
        supportFragmentManager.beginTransaction()
            .replace(binding.fragmentContainer.id, SettingsFragment())
            .addToBackStack(null)
            .commit()
    }

    override fun goBack() {
        supportFragmentManager.popBackStack()
    }
}
