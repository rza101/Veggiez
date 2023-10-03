package com.rhezarijaya.veggiez.data

import com.rhezarijaya.veggiez.data.model.Vegetable
import com.rhezarijaya.veggiez.data.model.VegetableDummy

class VegetableRepository private constructor() {
    private val vegetableList = mutableListOf<Vegetable>()

    init {
        if (vegetableList.isEmpty()) {
            vegetableList.addAll(VegetableDummy.dummyData)
        }
    }

    fun getAllVegetables(query: String? = null): List<Vegetable> = query?.let { q ->
        vegetableList.filter { vegetable ->
            vegetable.name.contains(q, ignoreCase = true)
        }
    } ?: vegetableList

    fun getAllFavorites(): List<Vegetable> = vegetableList.filter { it.isFavorite }

    fun getVegetableById(id: String) = vegetableList.firstOrNull { it.id == id }

    fun addFavorite(id: String): Boolean {
        val idx = vegetableList.indexOfFirst { it.id == id && !it.isFavorite }

        return if (idx >= 0) {
            vegetableList[idx] = vegetableList[idx].copy(isFavorite = true)
            true
        } else {
            false
        }
    }

    fun isOnFavorite(id: String): Boolean =
        vegetableList.indexOfFirst { it.id == id && it.isFavorite } != -1

    fun removeFavorite(id: String): Boolean {
        val idx = vegetableList.indexOfFirst { it.id == id && it.isFavorite }

        return if (idx >= 0) {
            vegetableList[idx] = vegetableList[idx].copy(isFavorite = false)
            true
        } else {
            false
        }
    }

    companion object {
        private var instance: VegetableRepository? = null

        fun getInstance(): VegetableRepository = instance ?: synchronized(this) {
            instance = VegetableRepository()
            return instance as VegetableRepository
        }
    }
}