package com.example.finsight.adapter

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.finsight.R
import com.example.finsight.model.Asset

class AssetAdapter(
    private val data: List<Asset>,
    private val onClick: (Asset) -> Unit
) : RecyclerView.Adapter<AssetAdapter.VH>() {

    inner class VH(view: View) : RecyclerView.ViewHolder(view) {
        val name: TextView = view.findViewById(R.id.assetName)
        val price: TextView = view.findViewById(R.id.assetPrice)
        val change: TextView = view.findViewById(R.id.assetChange)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        val v = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_asset, parent, false)
        return VH(v)
    }

    override fun getItemCount(): Int = data.size

    override fun onBindViewHolder(holder: VH, position: Int) {
        val a = data[position]
        holder.name.text = a.name
        holder.price.text = String.format("%.2f", a.price)
        holder.change.text = a.changePercent
        holder.change.setTextColor(if (a.trendUp) Color.parseColor("#2EB82E") else Color.parseColor("#E53935"))
        holder.itemView.setOnClickListener { onClick(a) }
    }
}
