package com.rhezarijaya.veggiez.data.model

data class Vegetable(
    val id: String,
    val name: String,
    val description: String,
    val photoUrl: String?,
    var isFavorite: Boolean = false,
)
