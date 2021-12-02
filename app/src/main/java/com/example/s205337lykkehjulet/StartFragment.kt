package com.example.s205337lykkehjulet

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.s205337lykkehjulet.adapter.ItemAdapter
import com.example.s205337lykkehjulet.data.DataCategories
import kotlinx.android.synthetic.main.list_item.view.*

class StartFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?

    ): View? {
        // Inflate the layout for this fragment
       val root = inflater.inflate(R.layout.fragment_start, container, false)

        val myDataset = DataCategories().loadButtonContext()
        val recyclerView = root?.findViewById<RecyclerView>(R.id.recycler_view)
        recyclerView!!.adapter = ItemAdapter(requireContext(), myDataset)
        recyclerView!!.setHasFixedSize(true)

      return root
    }
}