package com.test.fooddelivery.data

import com.test.fooddelivery.model.Food
import io.reactivex.Observable


interface FoodRepository {
    fun getFoodList(): Observable<List<Food>>
}