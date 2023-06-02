package com.example.bn

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView

class SlotAdapter(private val slotList: List<SlotData>) :
    RecyclerView.Adapter<SlotAdapter.SlotViewHolder>() {

    private var onItemClick: ((SlotData) -> Unit)? = null

    class SlotViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val card: CardView = itemView.findViewById(R.id.cardView)
        val date: TextView = itemView.findViewById(R.id.date)
        val statusText: TextView = itemView.findViewById(R.id.statustext)
        val statusBg: LinearLayout = itemView.findViewById(R.id.statusBg)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SlotAdapter.SlotViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.slot_card, parent, false)
        return SlotAdapter.SlotViewHolder(view)
    }

    override fun getItemCount(): Int {
        return slotList.size
    }

    override fun onBindViewHolder(holder: SlotViewHolder, position: Int) {
        val slot = slotList[position]
        holder.date.text = "23/2/23"


        holder.statusText.text = slot.status.name
        holder.statusBg.setBackgroundResource(getStatusColor(slot.status))

        holder.itemView.setOnClickListener {
            onItemClick?.invoke(slot)
        }
    }

    private fun getStatusColor(status: Status): Int {
        return when (status) {
            Status.AVAILABLE -> R.color.available
            Status.PENDING -> R.color.pending
            Status.BOOKED -> R.color.booked
            Status.SKIPPED -> R.color.skipped
            Status.ATTENDED -> R.color.attended
            Status.CANCELED -> R.color.canceled
        }
    }

}