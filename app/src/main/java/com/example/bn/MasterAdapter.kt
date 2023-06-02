package com.example.bn

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class MasterAdapter(private val masterList: List<Master>) :
    RecyclerView.Adapter<MasterAdapter.MasterViewHolder>() {

    var onItemClick: ((Master) -> Unit)? = null

    class MasterViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val masterImageView: ImageView = itemView.findViewById(R.id.avatar)
        val masterName: TextView = itemView.findViewById(R.id.name)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MasterViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.avatar_card, parent, false)
        return MasterViewHolder(view)
    }

    override fun getItemCount(): Int {
        return masterList.size
    }

    override fun onBindViewHolder(holder: MasterViewHolder, position: Int) {
        val master = masterList[position]
        holder.masterImageView.setImageResource(master.masterImage)
        holder.masterName.text = master.masterName

        holder.itemView.setOnClickListener {
            onItemClick?.invoke(master)
        }
    }

}