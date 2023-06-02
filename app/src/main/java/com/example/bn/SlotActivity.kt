package com.example.bn

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearSnapHelper
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.SnapHelper
import com.google.android.material.floatingactionbutton.FloatingActionButton
import java.time.LocalDateTime

class SlotActivity : AppCompatActivity() {

    private lateinit var recyclerViewSlot: RecyclerView
    private lateinit var slotList: ArrayList<SlotData>
    private lateinit var slotDataAdapter: SlotAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        initSlotPreview()

        val floatingButton: FloatingActionButton = findViewById(R.id.floatingActionButton)
        floatingButton.setOnClickListener {
            onBackPressed()
        }

    }


    private fun initSlotPreview() {
        recyclerViewSlot = findViewById(R.id.recyclerView)
        recyclerViewSlot.setHasFixedSize(true)
        recyclerViewSlot.layoutManager =
            LinearLayoutManager(this, RecyclerView.VERTICAL, false)

        slotList = ArrayList()
        addSlotDataToList()
        slotDataAdapter = SlotAdapter(slotList)
        recyclerViewSlot.adapter = slotDataAdapter

    }

    private fun addSlotDataToList() {
        slotList.add(SlotData(1, 1, 1, LocalDateTime.now(), LocalDateTime.now(), Status.AVAILABLE))
        slotList.add(SlotData(2, 1, 1, LocalDateTime.now(), LocalDateTime.now(), Status.PENDING))
        slotList.add(SlotData(3, 1, 1, LocalDateTime.now(), LocalDateTime.now(), Status.BOOKED))
        slotList.add(SlotData(4, 1, 1, LocalDateTime.now(), LocalDateTime.now(), Status.SKIPPED))
        slotList.add(SlotData(5, 1, 1, LocalDateTime.now(), LocalDateTime.now(), Status.ATTENDED))
        slotList.add(SlotData(5, 1, 1, LocalDateTime.now(), LocalDateTime.now(), Status.CANCELED))


    }
}