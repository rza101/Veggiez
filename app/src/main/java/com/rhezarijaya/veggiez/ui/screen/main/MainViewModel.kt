package com.rhezarijaya.veggiez.ui.screen.main

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.rhezarijaya.veggiez.data.VegetableRepository
import com.rhezarijaya.veggiez.data.model.Vegetable
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class MainViewModel(private val vegetableRepository: VegetableRepository) : ViewModel() {
    private val _vegetables = MutableStateFlow(vegetableRepository.getAllVegetables())
    val vegetables: StateFlow<List<Vegetable>>
        get() = _vegetables

    private val _searchQuery = mutableStateOf("")
    val searchQuery: State<String>
        get() = _searchQuery

    fun search(query: String?) {
        if (query.isNullOrEmpty()) {
            _vegetables.value = vegetableRepository.getAllVegetables()
            _searchQuery.value = ""
        } else {
            _vegetables.value = vegetableRepository.getAllVegetables(query)
            _searchQuery.value = query
        }
    }
}