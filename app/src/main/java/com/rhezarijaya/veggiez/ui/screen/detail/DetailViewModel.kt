package com.rhezarijaya.veggiez.ui.screen.detail

import androidx.lifecycle.ViewModel
import com.rhezarijaya.veggiez.data.VegetableRepository

class DetailViewModel(private val vegetableRepository: VegetableRepository) : ViewModel() {
    fun getVegetableById(id: String) = vegetableRepository.getVegetableById(id)
}