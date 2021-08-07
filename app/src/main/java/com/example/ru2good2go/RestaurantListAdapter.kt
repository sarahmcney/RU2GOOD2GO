package com.example.ru2good2go

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
//for recyclerview
class RestaurantListAdapter(private val restaurantList: List<RestaurantListItem>) : RecyclerView.Adapter<RestaurantListAdapter.RestaurantListViewHolder>() {
    class RestaurantListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val restaurantImage: ImageView = itemView.findViewById(R.id.restaurant_image)
        val restaurantName: TextView = itemView.findViewById(R.id.restaurant_name)
        val restaurantCategory: TextView = itemView.findViewById(R.id.restaurant_category)
        val restaurantDistance: TextView = itemView.findViewById(R.id.restaurant_distance)
        val restaurantHours: TextView = itemView.findViewById(R.id.restaurant_hours)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RestaurantListViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.offer_item, parent, false)
        return RestaurantListViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: RestaurantListViewHolder, position: Int) {
        val currentItem = restaurantList[position]

        holder.restaurantImage.setImageResource(currentItem.imageResource)
        holder.restaurantName.text = currentItem.name
        holder.restaurantCategory.text = currentItem.category
        holder.restaurantDistance.text = currentItem.distance
        holder.restaurantHours.text = currentItem.closingTime
    }

    override fun getItemCount() = restaurantList.size
}
