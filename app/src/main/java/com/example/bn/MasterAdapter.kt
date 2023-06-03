package com.example.bn

import android.graphics.BitmapFactory
import android.util.Base64
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.bn.dto.Master
import com.example.bn.dto.MasterArrayDto
import com.example.bn.dto.PublicMasterProfileDto

class MasterAdapter(private val masterList: MasterArrayDto) :
    RecyclerView.Adapter<MasterAdapter.MasterViewHolder>() {

    var onItemClick: ((Long) -> Unit)? = null

    class MasterViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val masterImageView: ImageView = itemView.findViewById(R.id.avatar)
        val masterName: TextView = itemView.findViewById(R.id.name)
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MasterViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.avatar_card, parent, false)
        return MasterViewHolder(view)
    }

    override fun getItemCount(): Int {
        return masterList.masters.size
    }

    override fun onBindViewHolder(holder: MasterViewHolder, position: Int) {
        val master = masterList.masters[position]
        val imageBytes = Base64.decode(master.image, Base64.DEFAULT)

        val bitmap = BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.size)
        holder.masterImageView.setImageBitmap(bitmap)
        holder.masterName.text = master.name

        holder.itemView.setOnClickListener {
            onItemClick?.invoke(master.id)
        }
    }

}