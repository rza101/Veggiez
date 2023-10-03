package com.rhezarijaya.veggiez.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.rhezarijaya.veggiez.data.VegetableRepository
import com.rhezarijaya.veggiez.di.Injection
import com.rhezarijaya.veggiez.ui.screen.detail.DetailViewModel
import com.rhezarijaya.veggiez.ui.screen.favorite.FavoriteViewModel
import com.rhezarijaya.veggiez.ui.screen.main.MainViewModel

class ViewModelFactory private constructor(private val vegetableRepository: VegetableRepository) :
    ViewModelProvider.NewInstanceFactory() {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(VeggiezAppViewModel::class.java)) {
            return VeggiezAppViewModel(vegetableRepository) as T
        } else if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            return MainViewModel(vegetableRepository) as T
        } else if (modelClass.isAssignableFrom(DetailViewModel::class.java)) {
            return DetailViewModel(vegetableRepository) as T
        } else if (modelClass.isAssignableFrom(FavoriteViewModel::class.java)) {
            return FavoriteViewModel(vegetableRepository) as T
        }

        throw IllegalArgumentException("Viewmodel class invalid")
    }

    companion object {
        private var instance: ViewModelFactory? = null

        fun getInstance(): ViewModelFactory = instance ?: synchronized(this) {
            instance = ViewModelFactory(Injection.provideVegetableRepository())
            return instance as ViewModelFactory
        }
    }
}