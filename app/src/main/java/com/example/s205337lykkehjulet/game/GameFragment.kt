package com.example.s205337lykkehjulet.game

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.viewModels
import com.example.s205337lykkehjulet.R
import com.example.s205337lykkehjulet.databinding.FragmentSecondFragmentBinding
import com.example.s205337lykkehjulet.databinding.FragmentStartBinding
import kotlinx.android.synthetic.main.fragment_second_fragment.view.*

class GameFragment : Fragment() {
    private val viewModel: GameViewHolder by viewModels()
    private lateinit var binding: FragmentSecondFragmentBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment trough binding
        binding = FragmentSecondFragmentBinding.inflate(inflater,container,false)

        //Tilfældigt valg ord:
        val category = context?.resources?.getString(requireArguments().getInt("Title"))

        binding.guessingWord.text = viewModel.newWord().toString()

        // brugte vi til at komme ind i de forskellige kategoricard viewms.
        // viewModel.generateWord(category!!)
        // root.findViewById<TextView>(R.id.testTxt).text = viewModel.word

        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding?.apply {
            // Setter en click listner på knapperne i secondfragment
            root.kob_vokal.setOnClickListener { activateWheel() }
        }
    }

    fun activateWheel () {
        Toast.makeText(activity,"Hjul drejer", Toast.LENGTH_SHORT).show()
    }

}

