package com.example.bn

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.bn.api.SessionManager
import com.example.bn.dto.PublicSlotsMapDto
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.coroutines.launch

class SlotActivity : AppCompatActivity() {

    private lateinit var recyclerViewSlot: RecyclerView
    private lateinit var slotDataAdapter: SlotMasterAdapter
    private var slotMap: PublicSlotsMapDto = PublicSlotsMapDto()
    private val sessionManager = SessionManager.getInstance()

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

        sessionManager.getCoroutineScope().launch {
            performGetMasterSlots()
            slotDataAdapter = SlotMasterAdapter(slotMap)
            recyclerViewSlot.adapter = slotDataAdapter

        }

    }
    private suspend fun performGetMasterSlots() {
        try {

            val response = sessionManager.getUserApi().getSlotsForRange(sessionManager.getToken())
            if (response.isSuccessful) {
                 slotMap = response.body()!!

                Toast.makeText(this, "GotMasterService", Toast.LENGTH_SHORT).show()
            } else {
                val errorBody = response.errorBody()?.string()
                println("Error: $errorBody")
                Toast.makeText(this, "Error: $errorBody", Toast.LENGTH_SHORT).show()
            }
        } catch (e: Exception) {
            println("Error: ${e.message}")
            Toast.makeText(this, "Error: ${e.message}", Toast.LENGTH_SHORT).show()
        }}

}