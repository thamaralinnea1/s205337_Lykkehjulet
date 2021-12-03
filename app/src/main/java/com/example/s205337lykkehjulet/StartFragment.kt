package com.example.s205337lykkehjulet

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.s205337lykkehjulet.adapter.ItemAdapter
import com.example.s205337lykkehjulet.data.DataCategories

/**
 *
 * Inspiration implementering af recycler view er fundet fra Android CodeLab unit 2 pathway 3
 * https://developer.android.com/courses/pathways/android-basics-kotlin-unit-2-pathway-3
 *
 *  * Inspiration til at benytte NavigationGraph er fundet fra Android CodeLab unit 3 pathway 4
 * https://developer.android.com/courses/pathways/android-basics-kotlin-unit-3-pathway-4
 */
class StartFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?

    ): View {
        // Inflate the layout for this fragment
        val root = inflater.inflate(R.layout.fragment_start, container, false)

        val myDataset = DataCategories().loadButtonContext()
        val recyclerView = root?.findViewById<RecyclerView>(R.id.recycler_view)
        recyclerView!!.adapter = ItemAdapter(requireContext(), myDataset)
        recyclerView.setHasFixedSize(true)

        return root
    }
}