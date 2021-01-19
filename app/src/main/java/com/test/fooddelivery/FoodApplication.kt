package com.test.fooddelivery

import android.app.Application
import com.airbnb.mvrx.Mavericks
import com.test.fooddelivery.data.FoodRepository
import com.test.fooddelivery.data.local.FoodRepositoryMock

class FoodApplication : Application() {
    val foodRepository: FoodRepository = FoodRepositoryMock()
    override fun onCreate() {
        super.onCreate()
        Mavericks.initialize(this)
    }
}
