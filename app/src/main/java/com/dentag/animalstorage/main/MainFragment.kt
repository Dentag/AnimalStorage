package com.dentag.animalstorage.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.PopupMenu
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.dentag.animalstorage.R
import com.dentag.animalstorage.Router
import com.dentag.animalstorage.databinding.FragmentMainBinding
import com.dentag.animalstorage.room.Animal

class MainFragment : Fragment() {

    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!

    private val viewModel by lazy {
        ViewModelProvider(this).get(MainViewModel::class.java)
    }
    private var adapter: AnimalAdapter? = null

    private val animalClickListener = object : AnimalAdapter.AnimalClickListener {
        override fun onClick(animal: Animal, view: View) {
            showPopupMenu(animal, view)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = FragmentMainBinding.inflate(inflater).also {
        _binding = it
    }.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupNavigation()
        setupRecyclerView()

        viewModel.singleData.observe(viewLifecycleOwner) {
            if (it.isNullOrEmpty()) {
                onListEmpty()
            } else {
                onListFilled()
            }
            adapter?.submitList(it)
        }
    }

    override fun onResume() {
        super.onResume()
        viewModel.getOnce()
    }

    private fun setupRecyclerView() {
        val layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerView.layoutManager = layoutManager
        adapter = AnimalAdapter(animalClickListener)
        binding.recyclerView.adapter = adapter
    }

    private fun setupNavigation() {
        viewModel.setupRouter(requireActivity() as Router)
        binding.filterBtn.setOnClickListener {
            viewModel.goSettingsScreen()
        }
        binding.fab.setOnClickListener {
            viewModel.newAnimal()
        }
    }

    private fun showPopupMenu(animal: Animal, view: View) {
        val popupMenu = PopupMenu(requireContext(), view)
        popupMenu.inflate(R.menu.item_menu)

        popupMenu.setOnMenuItemClickListener {
            when (it.itemId) {
                R.id.onUpdateMenuItem -> {
                    viewModel.updateAnimal(animal)
                    viewModel.getOnce()
                    true
                }
                R.id.onRemoveMenuItem -> {
                    viewModel.removeAnimal(animal)
                    viewModel.getOnce()
                    true
                }
                else -> return@setOnMenuItemClickListener false
            }
        }
        popupMenu.show()
    }

    private fun onListEmpty() {
        binding.zero.visibility = View.VISIBLE
    }

    private fun onListFilled() {
        binding.zero.visibility = View.GONE
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
        adapter = null
    }
}
