package com.phmb.myplaces.data.service

import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

object ApiService {

    private fun initRetrofit(): Retrofit {

        val url = "https://maps.googleapis.com/maps/"

        return Retrofit.Builder()
            .baseUrl(url)
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
    }

    val service: PlacesServices = initRetrofit().create(PlacesServices::class.java)
}