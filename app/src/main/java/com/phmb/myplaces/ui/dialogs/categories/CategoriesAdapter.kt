package com.phmb.myplaces.ui.dialogs.categories

import androidx.recyclerview.widget.RecyclerView
import android.view.ViewGroup
import android.view.View
import android.widget.TextView
import android.view.LayoutInflater
import com.phmb.myplaces.R
import kotlinx.android.synthetic.main.item_category.view.category_name

class CategoriesAdapter(private val mDataset: Array<String>, internal var recyclerViewItemClickListener: RecyclerViewItemClickListener
) : RecyclerView.Adapter<CategoriesAdapter.CategoriesViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, i: Int): CategoriesViewHolder {

        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_category, parent, false)

        return CategoriesViewHolder(view)
    }

    override fun onBindViewHolder(categoriesViewHolder: CategoriesViewHolder, i: Int) {
        categoriesViewHolder.mTextView.text = mDataset[i]
    }

    override fun getItemCount(): Int {
        return mDataset.size
    }

    inner class CategoriesViewHolder(v: View) : RecyclerView.ViewHolder(v), View.OnClickListener {

        var mTextView: TextView

        init {
            mTextView = v.category_name
            v.setOnClickListener(this)
        }

        override fun onClick(v: View) {
            recyclerViewItemClickListener.clickOnItem(mDataset[this.adapterPosition])
        }
    }

    interface RecyclerViewItemClickListener {
        fun clickOnItem(data: String)
    }
}