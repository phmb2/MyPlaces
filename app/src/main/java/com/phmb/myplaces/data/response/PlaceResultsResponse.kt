package com.phmb.myplaces.data.response

import com.squareup.moshi.Json

data class PlaceResultsResponse(
    @Json(name = "place_details")
    val placeDetailResponses: List<PlaceDetailsResponse>
)