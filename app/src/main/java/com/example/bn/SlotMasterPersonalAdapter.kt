package com.example.bn

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.bn.api.SessionManager
import com.example.bn.dto.EditSlotRequestBody
import com.example.bn.dto.SlotStatus
import com.example.bn.dto.SlotsMapDto
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.time.ZoneOffset
import java.time.format.DateTimeFormatter
import java.util.Locale

class SlotMasterPersonalAdapter (private val slotList: SlotsMapDto) :
    RecyclerView.Adapter<SlotMasterPersonalAdapter.SlotViewHolder>() {
    private val sessionManager = SessionManager.getInstance()
    class SlotViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val date: TextView = itemView.findViewById(R.id.date)
        val statusText: TextView = itemView.findViewById(R.id.statustext)
        val statusBg: LinearLayout = itemView.findViewById(R.id.statusBg)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): SlotMasterPersonalAdapter.SlotViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.slot_card_master, parent, false)
        return SlotMasterPersonalAdapter.SlotViewHolder(view)
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

        val approveButton = holder.itemView.findViewById<Button>(R.id.approve)
        val denyButton = holder.itemView.findViewById<Button>(R.id.deny)

        if(slot.status.name != "PENDING"){
            approveButton.isEnabled = false
            denyButton.isEnabled = false
        }
        approveButton.setOnClickListener{
            val context = holder.itemView.context
            sessionManager.getCoroutineScope().launch {
            performEditSlot(context,slot.id,SlotStatus.BOOKED)}
            holder.statusText.text = "BOOKED"
            approveButton.isEnabled = false
            denyButton.isEnabled = false
            holder.statusBg.setBackgroundResource(getStatusColor(SlotStatus.BOOKED))


        }
        denyButton.setOnClickListener{
            val context = holder.itemView.context
            sessionManager.getCoroutineScope().launch {
                performEditSlot(context,slot.id,SlotStatus.AVAILABLE)}
            holder.statusText.text = "AVAILABLE"
            approveButton.isEnabled = false
            denyButton.isEnabled = false
            holder.statusBg.setBackgroundResource(getStatusColor(SlotStatus.BOOKED))


        }

    }

    private suspend fun performEditSlot(context: Context, slotId: Long, status: SlotStatus) {
        try {

            val response = sessionManager.getUserApi().editSlot(slotId,
                EditSlotRequestBody(status),sessionManager.getToken())
            if (response.isSuccessful) {
                Toast.makeText(context, "Send your response", Toast.LENGTH_SHORT).show()
            } else {
                val errorBody = response.errorBody()?.string()
                println("Error: $errorBody")
                Toast.makeText(context, "Error: $errorBody", Toast.LENGTH_SHORT).show()
            }
        } catch (e: Exception) {
            println("Error: ${e.message}")
            Toast.makeText(context, "Error: ${e.message}", Toast.LENGTH_SHORT).show()
        }}

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
