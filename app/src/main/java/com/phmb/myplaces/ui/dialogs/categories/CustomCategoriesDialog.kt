package com.phmb.myplaces.ui.dialogs.categories

import android.app.Activity
import android.app.Dialog
import android.os.Bundle
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.dialog_categories_recyclerview.recycler_view
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.dialog_categories_recyclerview.cancel
import android.view.View
import android.view.Window
import com.phmb.myplaces.R

class CustomCategoriesDialog(var activity: Activity, internal var adapter: RecyclerView.Adapter<*>) : Dialog(activity),
    View.OnClickListener {

    private var recyclerView: RecyclerView? = null
    private var mLayoutManager: RecyclerView.LayoutManager? = null

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        setContentView(R.layout.dialog_categories_recyclerview)

        recyclerView = recycler_view
        mLayoutManager = LinearLayoutManager(activity)
        recyclerView?.layoutManager = mLayoutManager
        recyclerView?.adapter = adapter

        cancel.setOnClickListener(this)
    }

    override fun onClick(v: View) {

        when (v.id) {
            R.id.cancel -> dismiss()
            else -> {
            }
        }

        dismiss()
    }
}