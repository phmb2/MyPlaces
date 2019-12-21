package com.phmb.myplaces.ui.list

import com.phmb.myplaces.R
import com.phmb.myplaces.data.model.Place
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.View
import kotlinx.android.synthetic.main.item_place.view.*

class ListPlacesAdapter(private val places: List<Place>, val onItemClickListener: ((place: Place) -> Unit)) : RecyclerView.Adapter<ListPlacesAdapter.PlacesViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, view: Int): PlacesViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_place, parent, false)
        return PlacesViewHolder(itemView, onItemClickListener)
    }

    override fun getItemCount() = places.count()

    override fun onBindViewHolder(viewHolder: PlacesViewHolder, position: Int) {
        viewHolder.bindView(places[position])
    }

    class PlacesViewHolder(itemView: View, private val onItemClickListener: ((place: Place) -> Unit)) : RecyclerView.ViewHolder(itemView) {

        private val name = itemView.textName
        private val address = itemView.textAddress
        private val zipcode = itemView.textZipcode
        private val phone = itemView.textPhone

        fun bindView(place: Place) {
            name.text = place.name
            address.text = place.address
            zipcode.text = place.zipcode
            phone.text = place.phone

            itemView.setOnClickListener {
                onItemClickListener.invoke(place)
            }
        }
    }
}