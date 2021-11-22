package com.example.s205337lykkehjulet.game

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.s205337lykkehjulet.R

class second_fragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        //Tilføldigt valg ord:
        val gameVM = GameViewHolder()
        val word = gameVM.word


        return inflater.inflate(R.layout.fragment_second_fragment, container, false)
    }

}