package com.example.bn

import android.content.Intent
import android.graphics.BitmapFactory
import android.os.Bundle
import android.util.Base64
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearSnapHelper
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.SnapHelper
import com.bumptech.glide.Glide
import com.example.bn.api.SessionManager
import com.example.bn.dto.Master
import com.example.bn.dto.MasterArrayDto
import com.example.bn.dto.ServiceListDto
import com.example.bn.dto.ServicePreview
import io.jsonwebtoken.Claims
import io.jsonwebtoken.Jwts
import kotlinx.coroutines.launch

class HomeFragment : Fragment() {
    private lateinit var userName : TextView
    private lateinit var profilePhoto: ImageView

    private lateinit var recyclerViewMaster: RecyclerView
    private var masterList:MasterArrayDto = MasterArrayDto()
    private lateinit var masterAdapter: MasterAdapter

    private lateinit var recyclerViewService: RecyclerView
    private var servicePreviewList: ServiceListDto = ServiceListDto()
    private lateinit var servicePreviewAdapter: ServicePreviewAdapter
    private val sessionManager = SessionManager.getInstance()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_home, container, false)

        userName = root.findViewById(R.id.textView2)
        profilePhoto = root.findViewById(R.id.userAvatar)

        userName.text = sessionManager.getUser().name
        val imageBytes = Base64.decode(sessionManager.getUser().image, Base64.DEFAULT)
        val bitmap = BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.size)
        profilePhoto.setImageBitmap(bitmap)


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

        sessionManager.getCoroutineScope().launch {
            performGetAllMasters()
            masterAdapter = MasterAdapter(masterList)
            recyclerViewMaster.adapter = masterAdapter

            masterAdapter.onItemClick={profileId ->
                val intent = Intent(requireContext(),ProfileActivity::class.java)
                intent.putExtra("profile", 0)
                intent.putExtra("cardId",profileId)
                startActivity(intent)
            }
        }

    }

    private fun initServicePreview(root: View) {
        recyclerViewService = root.findViewById(R.id.service_list)
        recyclerViewService.setHasFixedSize(true)
        recyclerViewService.layoutManager =
            LinearLayoutManager(requireActivity(), RecyclerView.VERTICAL, false)

        val snapHelper: SnapHelper = LinearSnapHelper()
        snapHelper.attachToRecyclerView(recyclerViewService)

        sessionManager.getCoroutineScope().launch {
            performGetAllServices()
            servicePreviewAdapter = ServicePreviewAdapter(servicePreviewList)
            recyclerViewService.adapter = servicePreviewAdapter

            servicePreviewAdapter.onItemClick={
                val intent = Intent(requireContext(),ProfileActivity::class.java)
                intent.putExtra("profile", 1)
                startActivity(intent)
            }

        }

    }
    private suspend fun performGetAllServices() {
        try {
            val response = sessionManager.getUserApi().getAllServices(sessionManager.getToken())
            if (response.isSuccessful) {
                servicePreviewList = response.body()!!
                Toast.makeText(requireContext(), "GotServices", Toast.LENGTH_SHORT).show()
            } else {
                val errorBody = response.errorBody()?.string()
                Toast.makeText(requireContext(), "Error: $errorBody", Toast.LENGTH_SHORT).show()
            }
        } catch (e: Exception) {
            Toast.makeText(requireContext(), "Error: ${e.message}", Toast.LENGTH_SHORT).show()
        }}
    private suspend fun performGetAllMasters() {
        try {
            val response = sessionManager.getUserApi().getAllMasters(sessionManager.getToken())
            if (response.isSuccessful) {
                masterList = response.body()!!
                Toast.makeText(requireContext(), "GotMasters", Toast.LENGTH_SHORT).show()
            } else {
                val errorBody = response.errorBody()?.string()
                Toast.makeText(requireContext(), "Error: $errorBody", Toast.LENGTH_SHORT).show()
            }
        } catch (e: Exception) {
            Toast.makeText(requireContext(), "Error: ${e.message}", Toast.LENGTH_SHORT).show()
        }}
}