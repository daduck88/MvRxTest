package com.test.fooddelivery.view

import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.airbnb.mvrx.MavericksView
import com.airbnb.mvrx.activityViewModel
import com.airbnb.mvrx.withState
import com.test.fooddelivery.R
import com.test.fooddelivery.model.Food

class CartFragment : Fragment(R.layout.cart_fragment),
    MavericksView {
    private val viewModel: ShareFoodViewModel by activityViewModel()
    private lateinit var totalView: TextView
    private val listAdapter =
        CartListAdapter(
            emptyList(),
            object :
                CartListAdapter.DeleteClickListener() {
                override fun onDeleteClick(food: Food) {
                    viewModel.deleteFood(food)
                }
            })


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val list = view.findViewById<RecyclerView>(
            R.id.list
        )
        totalView = view.findViewById(R.id.total)
        list.isNestedScrollingEnabled = false
        list.setHasFixedSize(false)
        list.layoutManager =
            LinearLayoutManager(context)
        list.adapter = listAdapter
    }

    override fun invalidate() = withState(viewModel) { state ->
        val cartItems = state.foodPicked.toList()
        listAdapter.updateList(cartItems)
        var total = 0
        cartItems.forEach { total += it.value }
        totalView.text = "$total usd"
    }
}