package com.example.s205337lykkehjulet.game

import androidx.lifecycle.ViewModel
import com.example.s205337lykkehjulet.data.dyr


class GameViewHolder: ViewModel() {
    val wordList = dyr
    val FieldList = fieldList

    // lateint betyder at variablen først definneres senere
    lateinit var currentWordList: MutableList<String>
    lateinit var guessedCorrectLetters: MutableList<String>
    var life = 5
    var point = 0

    fun spinWheel (): String {
        var randomWheelField = FieldList.random()
        when (randomWheelField) {
            "Mistet Liv" -> {
                life -= 1
                println(life)
            }
            "Ekstra Liv" -> {
                life += 1
                println(life)
            }
            "Bankerot" -> {
                life == 0
                loseGame()
            }
        }
        return randomWheelField
    }




    fun newWord(): MutableList<String> {
        var currentWordNumber = (0 until (wordList.size)).random()
        var currentWord = wordList[currentWordNumber]
        currentWordList = currentWord.split("").toMutableList()
        currentWordList.removeFirst()
        currentWordList.removeLast()
        hideLetters()
        return currentWordList
    }

    fun hideLetters() {
        currentWordList.forEach {
            print("*")
        }

    }
    // Chekker om det valgte bogstav er inde i det listen over bogstaver for det nuværrende ord.
    // hvis bogstavet er en del af ordet, da tilføjes det til guessedcorret letters.
    fun checkGuess(input: String): Boolean {

        // Tilføjer altid mellem da det ikke kan gættes
        if ((" ") in currentWordList) guessedCorrectLetters.add(" ")

        // Er det et rigtigt bogstav?
        if (input in currentWordList) {
            guessedCorrectLetters.add(input)
            increaseScore()
            return true
        }
        else {
            wrongGuess()
            return false
        }
    }

    // hvis gættet er forkert mister brugeren et liv.
    fun wrongGuess () {
        var newLifeStatus = 1 - life
    }

    fun checkForWin() {
        if (guessedCorrectLetters.containsAll(currentWordList))
        return println("Du har vundet")
    }

    fun increaseScore() {
    }

    fun loseGame () {
        if (life == 0 ) {
            println("Spil er tabt")
        }
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