package com.phmb.myplaces.data.response

import com.squareup.moshi.Json

data class PlaceBodyResponse(
    @Json(name = "results")
    val placeResults: List<PlaceResultsResponse>
)