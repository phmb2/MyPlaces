package com.phmb.myplaces.ui.radar

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.bumptech.glide.Glide
import com.phmb.myplaces.R
import de.hdodenhof.circleimageview.CircleImageView


class RadarFragment : Fragment() {

    private lateinit var radarViewModel: RadarViewModel

    companion object {

        const val ARG_ID = "data_id"

        fun newInstance(id: String): RadarFragment {
            val fragment = RadarFragment()

            val bundle = Bundle().apply {
                putString(ARG_ID, id)
            }

            fragment.arguments = bundle

            return fragment
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        //val userId = arguments?.getString(ARG_ID)
        val userId = 1344295755751424
        Log.d("RadarFragment","Id: ${userId}")

        radarViewModel = ViewModelProviders.of(this).get(RadarViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_radar, container, false)

        val imageViewProfile: CircleImageView = root.findViewById(R.id.image_profile)

        val image_url = "https://graph.facebook.com/${userId}/picture?type=normal";

        Glide.with(this).load(image_url).into(imageViewProfile)

        return root
    }
}