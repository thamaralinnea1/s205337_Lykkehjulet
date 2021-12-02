package com.example.s205337lykkehjulet

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.s205337lykkehjulet.adapter.ItemAdapter
import com.example.s205337lykkehjulet.data.DataCategories
import com.example.s205337lykkehjulet.databinding.FragmentRulesBinding
import com.example.s205337lykkehjulet.databinding.FragmentSecondFragmentBinding

class RulesFragment : Fragment() {

    private lateinit var binding: FragmentRulesBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment trough binding
        binding = FragmentRulesBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.startGame.setOnClickListener { goToNewFragment() }
    }

    fun goToNewFragment() {
        findNavController().navigate(R.id.action_rulesFragment_to_startFragment)
    }

}