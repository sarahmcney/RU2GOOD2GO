package com.example.ru2good2go

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.DataBindingUtil.*
import com.example.ru2good2go.databinding.FragmentRestaurantCardBinding


class RestaurantCard : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding: FragmentRestaurantCardBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_restaurant_card, container, false)
        return binding.root
    }
}