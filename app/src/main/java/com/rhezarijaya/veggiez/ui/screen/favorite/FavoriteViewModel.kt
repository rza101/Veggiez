package com.rhezarijaya.veggiez.ui.screen.favorite

import androidx.lifecycle.ViewModel
import com.rhezarijaya.veggiez.data.VegetableRepository
import com.rhezarijaya.veggiez.data.model.Vegetable
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class FavoriteViewModel(private val vegetableRepository: VegetableRepository) : ViewModel() {
    val favorites: StateFlow<List<Vegetable>>
        get() = MutableStateFlow(vegetableRepository.getAllFavorites())
}