package com.test.fooddelivery.data.local

import com.test.fooddelivery.data.FoodRepository
import com.test.fooddelivery.model.Food
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.TimeUnit


class FoodRepositoryMock() : FoodRepository {
    override fun getFoodList() = Observable.fromCallable<List<Food>> {
        listOf(
            Food(
                "0",
                "Deluxe",
                "https://loremflickr.com/g/400/400/pizza",
                24,
                "Chicken, ham, peperoni, tomato, sauce, spicy chorizo and mozzarella",
                "150 grams, 35 cmt"
            ),
            Food(
                "1",
                "Hawaiian",
                "https://loremflickr.com/g/401/401/pizza",
                20,
                "Ham, pineapple, tomato and mozzarella",
                "150 grams, 35 cmt"
            ),
            Food(
                "2",
                "Pepperoni",
                "https://loremflickr.com/g/402/402/pizza",
                18,
                "Pepperoni, tomato and mozzarella",
                "150 grams, 35 cmt"
            ),
            Food(
                "3",
                "The Egoist",
                "https://loremflickr.com/g/400/400/sushi",
                32,
                "Fila classic, maci spice-salmon, two sushi salmon, two guangala salami",
                "480 grams, 18 pieces"
            )
        )
    }
        .delaySubscription(2, TimeUnit.SECONDS)
        .subscribeOn(Schedulers.io())
}

