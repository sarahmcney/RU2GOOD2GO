package com.example.ru2good2go

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.libraries.places.api.Places

class Feed : AppCompatActivity() {
    private val restaurantList = populateList() //temporary -- fills recyclerview with dummy data
    private val adapter = RestaurantListAdapter(restaurantList)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_feed_updated)

        Places.initialize(this, R.string.google_services_api_key.toString())
        val placesClient = Places.createClient(this)
        val recycler_view : RecyclerView = findViewById(R.id.recycler_view)
        recycler_view.adapter = adapter
        recycler_view.layoutManager = GridLayoutManager(this, 2, GridLayoutManager.HORIZONTAL, false)


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
            RestaurantListItem(image, "Tacoria", "New Brunswick, NJ")
        list += item

        //honeygrow
        image = R.mipmap.honeygrow_foreground
        item = RestaurantListItem(image, "Honeygrow", "New Brunswick, NJ")
        list += item

        //fritz's
        image = R.mipmap.fritzs_foreground
        item = RestaurantListItem(image, "Fritz's", "New Brunswick, NJ")
        list += item

        //halal guys
        image = R.mipmap.halalguys_foreground
        item = RestaurantListItem(image, "The Halal Guys", "New Brunswick, NJ")
        list += item

        //ramen nagomi
        image = R.mipmap.ramennagomi_foreground
        item = RestaurantListItem(image, "Ramen Nagomi", "New Brunswick, NJ")
        list += item

        //daniel's pizzeria
        image = R.mipmap.daniels_foreground
        item = RestaurantListItem(image, "Daniel's Pizzeria", "New Brunswick, NJ")
        list += item

        image = R.mipmap.daniels_foreground
        item = RestaurantListItem(image, "Daniel's Pizzeria", "New Brunswick, NJ")
        list += item
        return list
    }
}