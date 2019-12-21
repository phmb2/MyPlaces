package com.phmb.myplaces.data.service

import com.phmb.myplaces.data.response.PlaceBodyResponse

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface PlacesServices {

    @GET("api/place/nearbysearch/json?radius=100&sensor=true&key=AIzaSyDOffv_H8HtXT7Ett42eQSdC8g-RBarTc8")
    fun getNearbyPlaces(@Query("type") type: String, @Query("location") location: String, @Query("radius") radius: Int): Call<PlaceBodyResponse>
}