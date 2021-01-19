package com.test.fooddelivery.model

data class Food(
    val id: String,
    val name: String,
    val image: String,
    val value: Int,
    val ingredients: String,
    val dimensions: String
) {
    fun getValueUSD() = "$value usd"
}