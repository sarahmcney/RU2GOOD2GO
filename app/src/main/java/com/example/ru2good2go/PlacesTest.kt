package com.example.ru2good2go

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.example.ru2good2go.databinding.ActivityPlacesTestBinding
import network.PlaceViewModel

/*i set up a new activity just to test the places api: the user enters a restaurant name and the
corresponding place ID should appear on screen. clicking the sign up textView at the bottom of the
sign in activity will start this activity. i did the network requests the same way we did during the
accelerator but i'm getting the following error and i'm not sure how to fix it: Phenotype API error.
Event # cphj@46c4bb3f, EventCode: 12*/
//once i figure out how to get place IDs i can delete this activity

class PlacesTest : AppCompatActivity() {
    private lateinit var binding: ActivityPlacesTestBinding
    private val viewModel: PlaceViewModel by lazy {
        ViewModelProvider(this).get(PlaceViewModel::class.java)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_places_test)
        binding = ActivityPlacesTestBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.lifecycleOwner = this
        binding.viewModel = viewModel

        binding.placeIDButton.setOnClickListener {
            val input = binding.placeEditText.text.toString()
            viewModel.showPlace(input)
        }
    }
}