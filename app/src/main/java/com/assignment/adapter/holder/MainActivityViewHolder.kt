package com.assignment.adapter.holder


import android.content.Context
import android.view.View
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.RecyclerView
import com.assignment.GlideApp
import com.assignment.R
import com.assignment.model.Row
import com.bumptech.glide.request.RequestOptions


class MainActivityViewHolder(
    rootView: View,
    private val mContext: Context
) : RecyclerView.ViewHolder(rootView) {


    private val name: AppCompatTextView = rootView.findViewById(R.id.title_text)
    private val repositoryName: AppCompatTextView = rootView.findViewById(R.id.description)
    private val avatar : AppCompatImageView = rootView.findViewById(R.id.image)

    fun bind(item: Row) {

        name.text = item.title
        repositoryName.text = item.description

        GlideApp.with(mContext)
            .load(item.imageHref)
            .apply(RequestOptions.circleCropTransform())
            .into(avatar)




    }
}
