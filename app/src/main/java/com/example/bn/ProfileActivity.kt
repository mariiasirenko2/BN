package com.example.bn

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearSnapHelper
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.SnapHelper
import com.example.bn.api.SessionManager
import com.example.bn.dto.MasterServiceArrayDto
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.coroutines.launch

class ProfileActivity : AppCompatActivity() {
    private var masterServiceList: MasterServiceArrayDto = MasterServiceArrayDto()
    private lateinit var recyclerViewService: RecyclerView
    private lateinit var masterServiceAdapter: MasterServiceAdapter

    private val sessionManager = SessionManager.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        val flag = intent.getIntExtra("profile", -1)
        val cardId = intent.getLongExtra("cardId", 0)

        if (flag == 0)
            initServicePreview(cardId)
        if (flag == 1)
            Toast.makeText(this, "TBD in BeautyNote 1.1", Toast.LENGTH_SHORT).show()


        val floatingButton: FloatingActionButton = findViewById(R.id.floatingActionButton)
        floatingButton.setOnClickListener {
            onBackPressed()
        }

    }

    private fun initServicePreview(cardId: Long) {
        recyclerViewService = findViewById(R.id.recyclerView)
        recyclerViewService.setHasFixedSize(true)
        recyclerViewService.layoutManager =
            LinearLayoutManager(this, RecyclerView.VERTICAL, false)

        val snapHelper: SnapHelper = LinearSnapHelper()
        snapHelper.attachToRecyclerView(recyclerViewService)

        sessionManager.getCoroutineScope().launch {
            performGetAllMasterServices(cardId)
            masterServiceAdapter = MasterServiceAdapter(masterServiceList)
            recyclerViewService.adapter = masterServiceAdapter

            masterServiceAdapter.onItemClick={
                val intent = Intent(this@ProfileActivity,SlotActivity::class.java)
                startActivity(intent)
            }
        }
    }

    private suspend fun performGetAllMasterServices(masterId: Long) {
        try {
            val response = sessionManager.getUserApi().getMasterServicesByMasterId(sessionManager.getToken(),
                masterId)
            if (response.isSuccessful) {
                masterServiceList = response.body()!!
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