package com.example.usingapi

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AlphaAnimation
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.usingapi.databinding.ApiRecyclerviewBinding
import com.squareup.picasso.Picasso

@Suppress("DEPRECATION")
class MyAdapter(val productArrayList: List<Product>) : RecyclerView.Adapter<MyAdapter.MyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding = ApiRecyclerviewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return productArrayList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        anim(holder.itemView)
        val currentItem = productArrayList[position]
        holder.binding.headingTitle.text = currentItem.title
        holder.binding.brand.text = currentItem.brand
        holder.binding.category.text = currentItem.category
        holder.binding.description.text = currentItem.description
        holder.binding.price.text = currentItem.price.toString()
        holder.binding.rating.rating = currentItem.rating.toFloat()

        // Change star color based on rating
        val ratingColor = when {
//            currentItem.rating >= 4 -> R.color.colorHighRating
            currentItem.rating >= 2 -> R.color.colorMediumRating
            else -> R.color.colorLowRating
        }
        holder.binding.rating.progressTintList = ContextCompat.getColorStateList(holder.binding.root.context, ratingColor)

        Picasso.get().load(currentItem.thumbnail).into(holder.binding.productImage) //use Image
    }


    fun anim(view: View){
        var animation= AlphaAnimation(0.0f,1.0f)
        animation.duration =1500
        view.startAnimation(animation)
    }

    class MyViewHolder(val binding: ApiRecyclerviewBinding) : RecyclerView.ViewHolder(binding.root)
}
