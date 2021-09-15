package com.dentag.animalstorage.newanimal

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.dentag.animalstorage.Router
import com.dentag.animalstorage.databinding.FragmentNewAnimalBinding
import com.dentag.animalstorage.room.Animal

class NewAnimalFragment : Fragment() {

    private var _binding: FragmentNewAnimalBinding? = null
    private val binding get() = _binding!!
    private var savedAnimal: Animal? = null

    private val viewModel by lazy {
        ViewModelProvider(this).get(NewAnimalViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = FragmentNewAnimalBinding.inflate(inflater).also {
        _binding = it
    }.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.setRouter(requireActivity() as Router)
        with(binding) {
            savedAnimal?.let {
                nameInputLayout.editText?.setText(savedAnimal?.name)
                ageInputLayout.editText?.setText(savedAnimal?.age.toString())
                breedInputLayout.editText?.setText(savedAnimal?.breed)
            }
            addOrUpdateAnimalBtn.setOnClickListener {
                val name = nameInputLayout.editText?.text.toString()
                var age = ageInputLayout.editText?.text.toString()
                if (age.isBlank()) {
                    age = "0"
                }
                val breed = breedInputLayout.editText?.text.toString()
                viewModel.putAnimal(name, age, breed)
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    companion object {
        fun newInstance(animal: Animal): NewAnimalFragment {
            val fragment = NewAnimalFragment()
            fragment.savedAnimal = animal
            return fragment
        }
    }
}
