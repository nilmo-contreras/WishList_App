package com.example.wishlist

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

interface OnItemClickListener {
    fun onItemClick(item: TextView)
}

class ItemAdapter (private val items: MutableList<Item>, private val itemClickListener: OnItemClickListener ) : RecyclerView.Adapter<ItemAdapter.ViewHolder>(){
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val nameTv: TextView
        val priceTv: TextView
        val urlTv: TextView

        init {
            nameTv = itemView.findViewById(R.id.nameTv)
            priceTv = itemView.findViewById(R.id.priceTv)
            urlTv = itemView.findViewById(R.id.urlTv)
        }

        fun bind( url: TextView, clickListener: OnItemClickListener ) {
            itemView.setOnClickListener { clickListener.onItemClick(url) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val context = parent.context
        val inflater = LayoutInflater.from(context)
        val contactView = inflater.inflate(R.layout.item_item, parent, false)

        return ViewHolder(contactView)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items[position]

        holder.nameTv.text = item.name
        holder.priceTv.text = item.price
        holder.urlTv.text = item.url
        holder.bind(holder.urlTv, itemClickListener)
    }
}
