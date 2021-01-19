package com.test.fooddelivery.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.test.fooddelivery.R
import com.test.fooddelivery.model.Food
import com.google.android.material.button.MaterialButton

class FoodListAdapter(private var dataSet: List<Food>, private val listener: OrderClickListener) :
    RecyclerView.Adapter<FoodListAdapter.ViewHolder>() {

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.food_row, viewGroup, false)
        return ViewHolder(view)
    }

    fun updateList(dataSet: List<Food>){
        this.dataSet = dataSet
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        viewHolder.bind(dataSet[position], listener)
    }

    override fun getItemCount() = dataSet.size

    class ViewHolder(view: View) :  RecyclerView.ViewHolder(view) {

        private val imageView: ImageView = view.findViewById(R.id.image)
        private val nameView: TextView = view.findViewById(R.id.name)
        private val ingredientsView: TextView = view.findViewById(R.id.ingredients)
        private val dimensionsView: TextView = view.findViewById(R.id.dimensions)
        private val orderView: MaterialButton = view.findViewById(R.id.order)

        fun bind(food: Food, listener: OrderClickListener) {
            with(food) {
                nameView.text = name
                ingredientsView.text = ingredients
                dimensionsView.text = dimensions
                orderView.text = getValueUSD()
                Glide.with(imageView.context).load(image).into(imageView)
                orderView.setOnClickListener {
                    listener.onOrderClick(this)
                }
            }

        }
    }

    abstract class OrderClickListener {
        abstract fun onOrderClick(food: Food)
    }
}
