package com.ua.natifet.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import com.ua.natifet.R
import com.ua.natifet.ui.GifItem

class GifAdapter : ListAdapter<GifItem, GifAdapter.GifViewHolder>(GifItemDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GifViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.fragment_list, parent, false)
        return GifViewHolder(view)
    }

    override fun onBindViewHolder(holder: GifViewHolder, position: Int) {
        val gifItem = getItem(position)
        Picasso.get().load(gifItem.images.original.url).into(holder.gifImageView)
    }

    inner class GifViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val gifImageView: ImageView = itemView.findViewById(R.id.gifView)
    }
}

class GifItemDiffCallback : DiffUtil.ItemCallback<GifItem>() {
    override fun areItemsTheSame(oldItem: GifItem, newItem: GifItem): Boolean {
        return oldItem.images.original.url == newItem.images.original.url
    }

    override fun areContentsTheSame(oldItem: GifItem, newItem: GifItem): Boolean {
        return oldItem == newItem
    }
}
