package network

import com.squareup.moshi.Json


data class PlaceWithID (
    val candidates: List<Candidate>,
    val status: String
)

data class Candidate (
    @Json(name = "place_id")
    val placeID: String
)
