package com.example.bn

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.bn.dto.ServiceDto
import com.example.bn.dto.ServiceListDto
import com.example.bn.dto.ServicePreview

class ServicePreviewAdapter(private val servicePreviewList: ServiceListDto) :
    RecyclerView.Adapter<ServicePreviewAdapter.ServicePreviewViewHolder>() {
    var onItemClick: ((ServiceDto) -> Unit)? = null

    class ServicePreviewViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val serviceImageView: LinearLayout = itemView.findViewById(R.id.service_card_bg)
        val serviceName: TextView = itemView.findViewById(R.id.name)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ServicePreviewViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.service_card, parent, false)
        return ServicePreviewViewHolder(view)
    }

    override fun getItemCount(): Int {
        return servicePreviewList.services.size
    }

    override fun onBindViewHolder(holder: ServicePreviewViewHolder, position: Int) {
        val servicePreview = servicePreviewList.services[position]
        holder.serviceImageView.setBackgroundResource(R.drawable.manicure)
        holder.serviceName.text = servicePreview.name
        holder.itemView.setOnClickListener {
            onItemClick?.invoke(servicePreview)
        }
    }

}