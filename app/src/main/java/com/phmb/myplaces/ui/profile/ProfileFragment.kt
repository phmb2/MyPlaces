package com.phmb.myplaces.ui.profile

import android.os.Bundle
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

class ProfileFragment : Fragment() {

    //private lateinit var profileViewModel: ProfileViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        //val id = arguments?.getString("data_id")
        val userId = 1344295755751424
        val username = arguments?.getString("data_id")

        val root = inflater.inflate(R.layout.fragment_profile, container, false)
        val imageViewProfile: CircleImageView = root.findViewById(R.id.image_profile)
        val textViewName: TextView = root.findViewById(R.id.text_profile)

        val image_url = "https://graph.facebook.com/${userId}/picture?type=normal";

        Glide.with(this).load(image_url).into(imageViewProfile)
        //textViewName.setText(username);

        /*profileViewModel = ViewModelProviders.of(this).get(ProfileViewModel::class.java)
        profileViewModel.text.observe(this, Observer {
            textViewName.text = it

        })*/

        return root
    }
}