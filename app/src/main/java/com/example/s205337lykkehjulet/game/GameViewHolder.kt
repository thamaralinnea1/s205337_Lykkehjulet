package com.example.s205337lykkehjulet.game

import androidx.lifecycle.ViewModel
import com.example.s205337lykkehjulet.data.dyr


class GameViewHolder: ViewModel() {
    private val wordList: MutableList<String> = mutableListOf()
    // lateint betyder at variablen først definneres senere
    private lateinit var currentWord: String

    private fun NewWord() {
        currentWord = dyr.random()
    }



    var word: String = "null"


    fun generateWord(category: String): String {
        when (category) {
            "Dyr" -> {
                word = "Kategori Dyr"
                return word
            }
            "Handlinger" -> {
                word = " Kategori Handlinger"
                return word
            }
            "Ordsprog" -> {
                word = " Kategori Ordsprog"
                return word
            }
            "Udtryk" -> {
                word = "Kategori Udtryk"
                return word
            }
            else -> {
                word = "Intet"
                return word
            }
        }
    }

}