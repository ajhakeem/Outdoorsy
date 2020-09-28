package com.example.outdoorsy.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.outdoorsy.R
import com.example.outdoorsy.domain.RentalDomainModel
import kotlinx.android.synthetic.main.item_rental.view.*

class ListingsAdapter(
    private val feedList: List<RentalDomainModel>,
    private val onBottomReachedListener: OnBottomReachedListener
): RecyclerView.Adapter<ListingsAdapter.ListingViewHolder>() {

    var items: MutableList<RentalDomainModel> = this.feedList.toMutableList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListingViewHolder {
        return ListingViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_rental, parent, false)
        )
    }

    override fun getItemCount(): Int = items.size

    fun updateItems(newList: List<RentalDomainModel>) {
        items = newList.toMutableList()
        notifyDataSetChanged()
    }

    fun addItems(addList: List<RentalDomainModel>) {
        items.addAll(items.size, addList)
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: ListingViewHolder, position: Int) {
        if (position > 1 && position == items.size - 1) {
            onBottomReachedListener.onBottomReached(position)
        }

        val listing = items[position]

        if (listing.imageUrl.isNotEmpty()) {
            Glide.with(holder.itemView)
                .load(listing.imageUrl)
                .placeholder(R.drawable.ic_launcher_background)
                .into(holder.ivImage)
        }

        holder.tvName.text = listing.name
    }

    class ListingViewHolder(view: View): RecyclerView.ViewHolder(view) {
        val tvName: AppCompatTextView = view.tvName
        val ivImage: AppCompatImageView = view.ivImg
    }

}