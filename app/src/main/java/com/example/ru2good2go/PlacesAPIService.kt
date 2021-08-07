package com.example.ru2good2go

import android.content.res.Resources
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET

private val BASE_URL = "https://maps.googleapis.com/maps/api/place/findplacefromtext/json?key=" + Resources.getSystem().getString(R.string.google_services_api_key)

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    //.addCallAdapterFactory(CoroutineCallAdapterFactory())
    .baseUrl(BASE_URL)
    .build()

interface PlacesAPIService {
    @GET("&input=tacoria&inputtype=textquery") //using tacoria for now just as a test
    suspend fun getPlace(): List<PlaceWithID>
}

object PlacesAPI {
    val retrofitService : PlacesAPIService by lazy {
        retrofit.create(PlacesAPIService::class.java)
    }
}