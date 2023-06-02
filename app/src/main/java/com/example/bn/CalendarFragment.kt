package com.example.bn

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.time.LocalDateTime


class CalendarFragment : Fragment() {
    private lateinit var recyclerViewSlot: RecyclerView
    private lateinit var slotList: ArrayList<SlotData>
    private lateinit var slotDataAdapter: SlotAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_calendar, container, false)
        initSlotPreview(root)
        return root

    }

    private fun initSlotPreview(root: View) {
        recyclerViewSlot = root.findViewById(R.id.recyclerView)
        recyclerViewSlot.setHasFixedSize(true)
        recyclerViewSlot.layoutManager =
            LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)

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