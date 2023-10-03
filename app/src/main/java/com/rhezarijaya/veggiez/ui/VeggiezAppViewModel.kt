package com.rhezarijaya.veggiez.ui

import androidx.lifecycle.ViewModel
import com.rhezarijaya.veggiez.data.VegetableRepository

class VeggiezAppViewModel(private val vegetableRepository: VegetableRepository) : ViewModel() {
    fun addFavorite(id: String) = vegetableRepository.addFavorite(id)

    fun isOnFavorite(id: String) = vegetableRepository.isOnFavorite(id)

    fun removeFavorite(id: String) = vegetableRepository.removeFavorite(id)
}