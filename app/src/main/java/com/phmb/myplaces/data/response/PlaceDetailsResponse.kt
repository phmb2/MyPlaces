package com.phmb.myplaces.data.response

import com.phmb.myplaces.data.model.Place
import com.squareup.moshi.Json

data class PlaceDetailsResponse(
    @Json(name = "name")
    val name: String,
    @Json(name = "address")
    val address: String,
    @Json(name = "zipcode")
    val zipcode: String,
    @Json(name = "phone")
    val phone: String
    ) {

    fun getPlaceModel() = Place(
        name = this.name,
        address = this.address,
        zipcode = this.zipcode,
        phone = this.phone
    )
}