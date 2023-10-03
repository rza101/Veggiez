package com.rhezarijaya.veggiez.di

import com.rhezarijaya.veggiez.data.VegetableRepository

object Injection {
    fun provideVegetableRepository() = VegetableRepository.getInstance()
}