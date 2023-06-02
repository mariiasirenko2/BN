package com.example.bn

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearSnapHelper
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.SnapHelper
import com.google.android.material.floatingactionbutton.FloatingActionButton
import java.time.LocalDate
import java.time.LocalDateTime

class ProfileActivity : AppCompatActivity() {
    private lateinit var servicePreviewList: ArrayList<ServicePreview>
    private lateinit var recyclerViewService: RecyclerView
    private lateinit var servicePreviewAdapter: ServicePreviewAdapter

    private lateinit var recyclerViewMaster: RecyclerView
    private lateinit var masterList: ArrayList<Master>
    private lateinit var masterAdapter: MasterAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        val flag = intent.getIntExtra("profile", -1)

        if (flag == 0)
            initServicePreview()
        if (flag == 1)
            initMaster()


        val floatingButton: FloatingActionButton = findViewById(R.id.floatingActionButton)
        floatingButton.setOnClickListener {
            onBackPressed()
        }

    }

    private fun initMaster() {
        recyclerViewMaster = findViewById(R.id.recyclerView)
        recyclerViewMaster.setHasFixedSize(true)
        recyclerViewMaster.layoutManager =
            LinearLayoutManager(this, RecyclerView.VERTICAL, false)

        masterList = ArrayList()
        addMasterDataToList()
        masterAdapter = MasterAdapter(masterList)
        recyclerViewMaster.adapter = masterAdapter


        masterAdapter.onItemClick = {
            val intent = Intent(this, SlotActivity::class.java)
            startActivity(intent)
        }
    }

    private fun addMasterDataToList() {
        masterList.add(Master(1, "Vanilla", R.drawable.woman_1))
        masterList.add(Master(2, "Cinnamon", R.drawable.woman_2))
        masterList.add(Master(3, "Cake", R.drawable.woman_3))
        masterList.add(Master(4, "Lawanda", R.drawable.woman_4))
        masterList.add(Master(5, "Cherry", R.drawable.woman_5))
        masterList.add(Master(6, "Cardamon", R.drawable.male_1))
    }

    private fun initServicePreview() {
        recyclerViewService = findViewById(R.id.recyclerView)
        recyclerViewService.setHasFixedSize(true)
        recyclerViewService.layoutManager =
            LinearLayoutManager(this, RecyclerView.VERTICAL, false)

        val snapHelper: SnapHelper = LinearSnapHelper()
        snapHelper.attachToRecyclerView(recyclerViewService)

        servicePreviewList = ArrayList()
        addServicePreviewDataToList()
        servicePreviewAdapter = ServicePreviewAdapter(servicePreviewList)
        recyclerViewService.adapter = servicePreviewAdapter

        servicePreviewAdapter.onItemClick = {
            val intent = Intent(this, SlotActivity::class.java)
            startActivity(intent)
        }
    }

    private fun addServicePreviewDataToList() {
        servicePreviewList.add(ServicePreview(1, "Manicure", R.drawable.manicure))
        servicePreviewList.add(ServicePreview(2, "Haircut", R.drawable.haircut))
        servicePreviewList.add(ServicePreview(3, "Massage", R.drawable.masage))
        servicePreviewList.add(ServicePreview(4, "Makeup", R.drawable.makeup))

    }

}