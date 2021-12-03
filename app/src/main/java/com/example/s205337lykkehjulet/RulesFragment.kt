package com.example.s205337lykkehjulet

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.s205337lykkehjulet.adapter.RulesAdapter
import com.example.s205337lykkehjulet.databinding.FragmentRulesBinding

class RulesFragment : Fragment() {

    private lateinit var binding: FragmentRulesBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment trough binding
        binding = FragmentRulesBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val list = listOf<String>(
            getString(R.string.rule1),
            getString(R.string.rule2),
            getString(R.string.rule3),
            getString(R.string.rule4),
            getString(R.string.rule5),
            getString(R.string.rule6),
            getString(R.string.rule7),
            getString(R.string.rule8),
            getString(R.string.rule9),
            getString(R.string.rule10),
            getString(R.string.rule11),
            getString(R.string.rule12),
        )
        val recyclerView = binding.rulesText
        recyclerView.apply {
            recyclerView.layoutManager = LinearLayoutManager(context)
            recyclerView.adapter = RulesAdapter(list)
        }
    }
}