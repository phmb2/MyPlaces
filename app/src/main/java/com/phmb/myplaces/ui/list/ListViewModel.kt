package com.phmb.myplaces.ui.list

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.phmb.myplaces.data.model.Place
import com.phmb.myplaces.data.service.ApiService
import com.phmb.myplaces.data.response.PlaceBodyResponse
import com.phmb.myplaces.R
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Call

class ListViewModel : ViewModel() {

    val placesLiveData: MutableLiveData<List<Place>> = MutableLiveData()
    val viewFlipperLiveData: MutableLiveData<Pair<Int, Int?>> = MutableLiveData()

    fun getPlaces() {

        val type = "restaurant";

        ApiService.service.getNearbyPlaces(type, "", 1000).enqueue(object: Callback<PlaceBodyResponse> {
            override fun onResponse(call: Call<PlaceBodyResponse>, response: Response<PlaceBodyResponse>) {
                when {
                    response.isSuccessful -> {
                        val places: MutableList<Place> = mutableListOf()

                        response.body()?.let { placeBodyResponse ->
                            for (result in placeBodyResponse.placeResults) {
                                val place = result.placeDetailResponses[0].getPlaceModel()
                                places.add(place)
                            }
                        }

                        placesLiveData.value = places
                        viewFlipperLiveData.value = Pair(VIEW_PLACES, null)
                    }
                    response.code() == 401 -> viewFlipperLiveData.value = Pair(VIEW_ERROR, R.string.error_401)
                    else -> viewFlipperLiveData.value = Pair(VIEW_ERROR, R.string.error_400_generic)
                }
            }

            override fun onFailure(call: Call<PlaceBodyResponse>, t: Throwable) {
                viewFlipperLiveData.value = Pair(VIEW_ERROR, R.string.error_500_generic)
            }
        })
    }

    companion object {
        private const val VIEW_PLACES = 1
        private const val VIEW_ERROR = 2
    }

}