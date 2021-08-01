package com.example.ru2good2go

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class Feed : AppCompatActivity() {
    private val restaurantList = populateList() //temporary -- fills recyclerview with dummy data
    private val adapter = RestaurantListAdapter(restaurantList)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_feed)

        val recycler_view : RecyclerView = findViewById(R.id.recycler_view)
        recycler_view.adapter = adapter
        recycler_view.layoutManager = LinearLayoutManager(this)

        val browseLink : ImageView = findViewById(R.id.browse_icon)
        browseLink.setOnClickListener {
            val intent = Intent(this, BrowseRestaurants::class.java)
            startActivity(intent)
        }
    }

    private fun populateList(): ArrayList<RestaurantListItem> {
        //dummy data for now
        //also everything here has to be hard-coded until google places is working properly
        val list = ArrayList<RestaurantListItem>()

        //tacoria
        var image = R.mipmap.tacoria_foreground
        var item =
            RestaurantListItem(image, "Tacoria", "Mexican", "0.5 miles away", "Open til 9:00PM")
        list += item

        //honeygrow
        image = R.mipmap.honeygrow_foreground
        item = RestaurantListItem(image, "Honeygrow", "Salad", "0.7 miles away", "Open til 10:00PM")
        list += item

        //fritz's
        image = R.mipmap.fritzs_foreground
        item = RestaurantListItem(image, "Fritz's", "American", "1.2 miles away", "Open til 8:00PM")
        list += item

        //halal guys
        image = R.mipmap.halalguys_foreground
        item = RestaurantListItem(image, "The Halal Guys", "Halal", "0.8 miles away", "Open til 11:00PM")
        list += item

        //ramen nagomi
        image = R.mipmap.ramennagomi_foreground
        item = RestaurantListItem(image, "Ramen Nagomi", "Ramen", "2.3 miles away", "Open til 9:00PM")
        list += item

        //daniel's pizzeria
        image = R.mipmap.daniels_foreground
        item = RestaurantListItem(image, "Daniel's Pizzeria", "Pizza", "1.0 miles away", "Open til 1:00AM")
        list += item

        return list
    }
}