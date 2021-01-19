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

class CartListAdapter(private var dataSet: List<Food>, private val listener: DeleteClickListener) :
    RecyclerView.Adapter<CartListAdapter.ViewHolder>() {

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.cart_row, viewGroup, false)
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
        private val valueView: TextView = view.findViewById(R.id.value)
        private val deleteView: MaterialButton = view.findViewById(R.id.delete)

        fun bind(food: Food, listener: DeleteClickListener) {
            with(food) {
                nameView.text = name
                valueView.text = getValueUSD()
                Glide.with(imageView.context).load(image).into(imageView)
                deleteView.setOnClickListener {
                    listener.onDeleteClick(this)
                }
            }

        }
    }

    abstract class DeleteClickListener {
        abstract fun onDeleteClick(food: Food)
    }
}
