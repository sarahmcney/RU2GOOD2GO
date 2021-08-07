package com.example.ru2good2go

import com.squareup.moshi.Json

data class PlaceWithID(
    @Json(name="place_id") val placeID: String
)