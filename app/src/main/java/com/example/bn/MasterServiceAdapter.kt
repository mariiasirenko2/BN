package com.example.bn

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.bn.dto.MasterServiceArrayDto
import com.example.bn.dto.MasterServiceElemDto

class MasterServiceAdapter(private val masterServiceArray: MasterServiceArrayDto) :
    RecyclerView.Adapter<MasterServiceAdapter.MasterServiceViewHolder>() {
    var onItemClick: ((MasterServiceElemDto) -> Unit)? = null

    class MasterServiceViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val price: TextView = itemView.findViewById(R.id.price)
        val avgTime: TextView = itemView.findViewById(R.id.avgTime)
        val tag_state_description: TextView = itemView.findViewById(R.id.tag_state_description)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MasterServiceViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.service_personal_card, parent, false)
        return MasterServiceViewHolder(view)
    }

    override fun getItemCount(): Int {
        return masterServiceArray.services.size
    }

    override fun onBindViewHolder(holder: MasterServiceViewHolder, position: Int) {
        val masterService = masterServiceArray.services[position]
        holder.price.text = masterService.price.toString()
        holder.avgTime.text = masterService.averageTime.toString()
        holder.tag_state_description.text = masterService.description

        holder.itemView.setOnClickListener {
            onItemClick?.invoke(masterService)
        }
    }

}