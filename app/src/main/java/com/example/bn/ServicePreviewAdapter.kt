package com.example.bn

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ServicePreviewAdapter(private val servicePreviewList: List<ServicePreview>) :
    RecyclerView.Adapter<ServicePreviewAdapter.ServicePreviewViewHolder>() {
    var onItemClick: ((ServicePreview) -> Unit)? = null

    class ServicePreviewViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val serviceImageView: LinearLayout = itemView.findViewById(R.id.service_card_bg)
        val serviceName: TextView = itemView.findViewById(R.id.name)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ServicePreviewViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.service_card, parent, false)
        return ServicePreviewViewHolder(view)
    }

    override fun getItemCount(): Int {
        return servicePreviewList.size
    }

    override fun onBindViewHolder(holder: ServicePreviewViewHolder, position: Int) {
        val servicePreview = servicePreviewList[position]
        holder.serviceImageView.setBackgroundResource(servicePreview.serviceImage)
        holder.serviceName.text = servicePreview.serviceName
        holder.itemView.setOnClickListener {
            onItemClick?.invoke(servicePreview)
        }
    }

}