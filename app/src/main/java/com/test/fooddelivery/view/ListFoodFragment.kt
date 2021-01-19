package com.test.fooddelivery.view

import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.os.Bundle
import android.view.View
import androidx.core.widget.ContentLoadingProgressBar
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.airbnb.mvrx.*
import com.test.fooddelivery.R
import com.test.fooddelivery.model.Food
import com.google.android.material.floatingactionbutton.FloatingActionButton

class ListFoodFragment : Fragment(R.layout.list_fragment), MavericksView {
    /** This ViewModel will be shared across FlowFragmentA and FlowFragmentB. */
    private val viewModel: ShareFoodViewModel by activityViewModel()
    private lateinit var list: RecyclerView
    private lateinit var fab: FloatingActionButton
    private lateinit var loader: ContentLoadingProgressBar
    private val listAdapter =
        FoodListAdapter(emptyList(), object : FoodListAdapter.OrderClickListener() {
            override fun onOrderClick(food: Food) {
                viewModel.addFood(food)
            }
        })

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        list = view.findViewById(R.id.list)
        list.layoutManager = LinearLayoutManager(context)
        list.adapter = listAdapter

        fab = view.findViewById(R.id.fab)

        loader = view.findViewById(R.id.loader)

        fab.setOnClickListener {
            findNavController().navigate(R.id.action_fragment_a_to_fragment_b)
        }
    }

    override fun invalidate() = withState(viewModel) { state ->
        fab?.setImageBitmap(textAsBitmap("${state.foodPicked.size}", 40f, Color.WHITE))
        when (state.foodList) {
            is Loading -> {
                loader.show()
            }
            is Success -> {
                loader.hide()
                listAdapter.updateList(state.foodList.invoke())
            }
        }
    }

    //method to convert your text to image
    private fun textAsBitmap(text: String?, textSize: Float, textColor: Int): Bitmap? {
        val paint = Paint(Paint.ANTI_ALIAS_FLAG)
        paint.textSize = textSize
        paint.color = textColor
        paint.textAlign = Paint.Align.LEFT
        val baseline: Float = -paint.ascent() // ascent() is negative
        val width = (paint.measureText(text) + 0.0f).toInt() // round
        val height = (baseline + paint.descent() + 0.0f).toInt()
        val image = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888)
        val canvas = Canvas(image)
        canvas.drawText(text, 0F, baseline, paint)
        return image
    }

}

