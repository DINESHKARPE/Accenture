package com.assignment.adapter


import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.assignment.R
import com.assignment.adapter.holder.MainActivityViewHolder
import com.assignment.model.Row


class MainActivityListAdapter(
    private var repositoryList: MutableList<Row>?,
    private val mContext: Context
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainActivityViewHolder {
        val itemView: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.repository_item_layout, parent, false)

        return MainActivityViewHolder(
            itemView,
            mContext
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is MainActivityViewHolder -> {
                repositoryList?.get(position)?.let { holder.bind(it) }


            }
        }
    }

    override fun getItemCount(): Int {
        return repositoryList!!.size
    }

}
