package com.example.bn

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.bn.dto.SlotStatus
import com.example.bn.dto.SlotsMapDto
import java.text.SimpleDateFormat
import java.time.ZoneOffset
import java.time.format.DateTimeFormatter
import java.util.Locale

class SlotClientAdapter (private val slotList: SlotsMapDto) :
    RecyclerView.Adapter<SlotClientAdapter.SlotViewHolder>() {

    class SlotViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val date: TextView = itemView.findViewById(R.id.date)
        val statusText: TextView = itemView.findViewById(R.id.statustext)
        val statusBg: LinearLayout = itemView.findViewById(R.id.statusBg)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): SlotClientAdapter.SlotViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.slot_card_client, parent, false)
        return SlotClientAdapter.SlotViewHolder(view)
    }

    override fun getItemCount(): Int {
        return slotList.slots.size
    }

    override fun onBindViewHolder(holder: SlotViewHolder, position: Int) {
        val slot = slotList.slots[position]
        val dateFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd")
        val fromDate = slot.from.toInstant().atZone(ZoneOffset.UTC).toLocalDate().format(dateFormat)
        val timeFormat = SimpleDateFormat("HH:mm", Locale.getDefault())
        val fromTime = timeFormat.format(slot.from)
        val toTime = timeFormat.format(slot.to)
        ("$fromDate: $fromTime - $toTime").also { holder.date.text = it }

        holder.statusText.text = slot.status.name
        holder.statusBg.setBackgroundResource(getStatusColor(slot.status))

        val requestButton = holder.itemView.findViewById<Button>(R.id.button2)
        requestButton.setOnClickListener{
            val context = holder.itemView.context
            Toast.makeText(context, "Send Request", Toast.LENGTH_SHORT).show()
        }

    }

    private fun getStatusColor(status: SlotStatus): Int {
        return when (status) {
            SlotStatus.AVAILABLE -> R.color.available
            SlotStatus.PENDING -> R.color.pending
            SlotStatus.BOOKED -> R.color.booked
            SlotStatus.SKIPPED -> R.color.skipped
            SlotStatus.ATTENDED -> R.color.attended
            SlotStatus.CANCELED -> R.color.canceled
        }
    }
}
