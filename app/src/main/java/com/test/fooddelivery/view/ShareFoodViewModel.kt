package com.test.fooddelivery.view

import com.airbnb.mvrx.*
import com.test.fooddelivery.FoodApplication
import com.test.fooddelivery.data.FoodRepository
import com.test.fooddelivery.model.Food

data class ShareFoodState(
    val foodList: Async<List<Food>> = Uninitialized,
    @PersistState val foodPicked: MutableSet<Food> = mutableSetOf()
) : MavericksState

class ShareFoodViewModel(
    state: ShareFoodState,
    foodRepository: FoodRepository
) : BaseMvRxViewModel<ShareFoodState>(state) {
    fun addFood(newFood: Food) =
        setState { copy(foodPicked = foodPicked.plus(newFood) as MutableSet<Food>) }

    fun deleteFood(deleteFood: Food) =
        setState { copy(foodPicked = foodPicked.minus(deleteFood) as MutableSet<Food>) }

    init {
        setState {
            copy(foodList = Loading())
        }
        foodRepository.getFoodList().execute { copy(foodList = it) }
    }

    companion object :
        MavericksViewModelFactory<ShareFoodViewModel, ShareFoodState> {
        override fun create(
            viewModelContext: ViewModelContext,
            state: ShareFoodState
        ): ShareFoodViewModel? {
            val foodRepository = viewModelContext.app<FoodApplication>().foodRepository
            return ShareFoodViewModel(
                state,
                foodRepository
            )
        }
    }
}