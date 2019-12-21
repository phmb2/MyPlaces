package com.phmb.myplaces.ui.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.phmb.myplaces.R
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.fragment_list.*

class ListFragment : Fragment() {

    private lateinit var listViewModel: ListViewModel
    private lateinit var recyclerView: RecyclerView
    //private lateinit var adapter: ListPlacesAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        listViewModel = ViewModelProviders.of(this).get(ListViewModel::class.java)

        val root = inflater.inflate(R.layout.fragment_list, container, false)

        listViewModel.placesLiveData.observe(this, Observer {
            it?.let { places ->
                with(recyclerPlaces) {

                    recyclerView.layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)

                    recyclerView.setHasFixedSize(true)

                    recyclerView.adapter = ListPlacesAdapter(places) { place ->
                        //val intent = PlaceDetailsActivity.getStartIntent(this@ListFragment, place.name, place.address)
                        //this@ListFragment.startActivity(intent)
                    }
                }
            }

        })

        listViewModel.viewFlipperLiveData.observe(this, Observer {
            it?.let { viewFlipper ->
                viewFlipperPlaces.displayedChild = viewFlipper.first

                viewFlipper.second?.let { errorMessageResId ->
                    textViewError.text = getString(errorMessageResId)
                }
            }
        })

        listViewModel.getPlaces()

        /*listViewModel.text.observe(this, Observer {
            textView.text = it
        })*/
        return root
    }

}