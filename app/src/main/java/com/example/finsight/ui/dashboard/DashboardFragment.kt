package com.example.finsight.ui.dashboard

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.finsight.R
import com.example.finsight.adapter.AssetAdapter
import com.example.finsight.data.DummyData

class DashboardFragment : Fragment() {
    @SuppressLint("MissingInflatedId")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val v = inflater.inflate(R.layout.fragment_dashboard, container, false)

        val rv = v.findViewById<RecyclerView>(R.id.assetRecycler)
        rv.layoutManager = LinearLayoutManager(requireContext())
        rv.adapter = AssetAdapter(DummyData.assets) { asset ->
            val fragment = AssetDetailFragment()
            val bundle = Bundle()
            bundle.putString("symbol", asset.symbol)
            bundle.putString("name", asset.name)
            fragment.arguments = bundle

            parentFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, fragment)
                .addToBackStack(null)
                .commit()
        }

        return v
    }
}
