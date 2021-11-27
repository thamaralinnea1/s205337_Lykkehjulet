package com.example.s205337lykkehjulet.game

import androidx.lifecycle.ViewModel
import com.example.s205337lykkehjulet.data.dyr


class GameViewHolder: ViewModel() {
     val wordList = dyr
    // lateint betyder at variablen først definneres senere
    lateinit var currentWordList: MutableList<String>
    lateinit var guessedCorrectLetters: MutableList<String>
    var life = 5
    var point = 0


    fun newWord(): MutableList<String> {
        var currentWordNumber = (0 until (wordList.size)).random()
        var currentWord = wordList[currentWordNumber]
        currentWordList = currentWord.split("").toMutableList()
        currentWordList.removeFirst()
        currentWordList.removeLast()
        hideLetters()
        return currentWordList
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