package com.example.bn

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearSnapHelper
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.SnapHelper

class HomeFragment : Fragment() {
    private lateinit var recyclerViewMaster: RecyclerView
    private lateinit var masterList: ArrayList<Master>
    private lateinit var masterAdapter: MasterAdapter

    private lateinit var recyclerViewService: RecyclerView
    private lateinit var servicePreviewList: ArrayList<ServicePreview>
    private lateinit var servicePreviewAdapter: ServicePreviewAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_home, container, false)
        initMaster(root)
        initServicePreview(root)
        return root
    }

    private fun initMaster(root: View) {
        recyclerViewMaster = root.findViewById(R.id.avatar_list)
        recyclerViewMaster.setHasFixedSize(true)
        recyclerViewMaster.layoutManager =
            LinearLayoutManager(requireActivity(), RecyclerView.HORIZONTAL, false)

        val snapHelper: SnapHelper = LinearSnapHelper()
        snapHelper.attachToRecyclerView(recyclerViewMaster)

        masterList = ArrayList()
        addMasterDataToList()
        masterAdapter = MasterAdapter(masterList)
        recyclerViewMaster.adapter = masterAdapter
    }

    private fun addMasterDataToList() {
        masterList.add(Master(1, "Vanilla", R.drawable.woman_1))
        masterList.add(Master(2, "Cinnamon", R.drawable.woman_2))
        masterList.add(Master(3, "Cake", R.drawable.woman_3))
        masterList.add(Master(4, "Lawanda", R.drawable.woman_4))
        masterList.add(Master(5, "Cherry", R.drawable.woman_5))
        masterList.add(Master(6, "Cardamon", R.drawable.male_1))
    }

    private fun initServicePreview(root: View) {
        recyclerViewService = root.findViewById(R.id.service_list)
        recyclerViewService.setHasFixedSize(true)
        recyclerViewService.layoutManager = LinearLayoutManager(requireActivity(), RecyclerView.VERTICAL, false)

        val snapHelper: SnapHelper = LinearSnapHelper()
        snapHelper.attachToRecyclerView(recyclerViewService)

        servicePreviewList = ArrayList()
        addServicePreviewDataToList()
        servicePreviewAdapter = ServicePreviewAdapter(servicePreviewList)
        recyclerViewService.adapter = servicePreviewAdapter
    }

    private fun addServicePreviewDataToList() {
        servicePreviewList.add(ServicePreview(1, "Manicure", R.drawable.manicure))
        servicePreviewList.add(ServicePreview(2, "Haircut", R.drawable.haircut))
        servicePreviewList.add(ServicePreview(3, "Massage", R.drawable.masage))
        servicePreviewList.add(ServicePreview(4, "Makeup", R.drawable.makeup))

    }
}