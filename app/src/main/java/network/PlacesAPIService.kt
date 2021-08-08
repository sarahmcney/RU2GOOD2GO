package network

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.*

private const val BASE_URL = "https://maps.googleapis.com/maps/api/place/findplacefromtext/"

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private val retrofit = Retrofit.Builder()
    .baseUrl(BASE_URL)
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .addCallAdapterFactory(CoroutineCallAdapterFactory())
    .build()

interface PlacesAPIService {
    @GET("json")
    fun getPlace(@Query("key") key: String, @Query("input") input: String?, @Query("inputtype") inputType: String?): PlaceWithID
}



object PlacesAPI {
    val retrofitService = retrofit.create(PlacesAPIService::class.java)
}