package com.example.s205337lykkehjulet.game

import android.widget.EditText
import androidx.compose.ui.text.toLowerCase
import androidx.lifecycle.ViewModel
import com.example.s205337lykkehjulet.data.*
import java.lang.StringBuilder


class GameViewHolder : ViewModel() {
    lateinit var wordList: List<String>
    val FieldList = fieldList
    lateinit var randomWheelField: String
    lateinit var currentWord: String
    lateinit var hiddenWord: String

    // lateint betyder at variablen først definneres senere
    lateinit var currentWordList: MutableList<String>
    val guessedCorrectLetters: MutableList<String> = arrayListOf()

    private var _life = 5
    val life: Int
        get() = _life

    //var lifeStatus by Delegates.notNull<Int>()
    private var _point = 1000
    val point: Int
        get() = _point


    fun spinWheel(): String {
        randomWheelField = FieldList.random()
        when (randomWheelField) {
            "Mistet Liv" -> {
                _life -= 1
            }
            "Ekstra Liv" -> {
                _life += 1
            }
            "Bankerot" -> {
                _life = 0
            }

        }
        checkForGameLost()
        return randomWheelField
    }

    // Funktion der tilføjer point til spillerens beholdning svarende til det feltet på hjulet.
    fun addPoint(randomWheelField: String): Int {
        when (randomWheelField) {
            "1000 point" -> {
                _point += 1000

            }
            "500 point" -> {
                _point += 500
            }
            "800 point" -> {
                _point += 800
            }
            "750 point" -> {
                _point += 750
            }
            "250 point" -> {
                _point += 250
            }
            "Gæt volal" -> {
            }
        }
        return _point
    }

    // Returne en liste der svare til den kategori der er valgt.
    fun setCategory(category: String): String {
        when (category) {
            "Dyr" -> {
                wordList = dyr
            }
            "Handlinger" -> {
                wordList = handlinger
            }
            "Udtryk" -> {
                wordList = udtryk
            }
            "Ordsprog" -> {
                wordList = ordsprog
            }
        }
        return newWord()

    }


    fun newWord(): String {
        var currentWordNumber = (0 until (wordList.size)).random()
        currentWord = wordList[currentWordNumber]
        currentWordList = currentWord.split("").toMutableList()
        currentWordList.removeFirst()
        currentWordList.removeLast()

        val letters = hideLetter()

        hiddenWord = ""
        for (word in letters) {
            hiddenWord += word
        }
        showInput = hiddenWord
        return hiddenWord
    }


    // gemmer hvert bogstav fra currentWordList bag et symbol
    fun hideLetter(): MutableList<String> {

        var hideCurrentWordList = ""
        for (i in 1..currentWordList.size) {
            hideCurrentWordList += "*"
        }
        return hideCurrentWordList.split("").toMutableList()
    }


    fun showLetter (letter: Char, hiddenWord: String): String {
        currentWord
        var hiddenWordTemp = hiddenWord
        for (i in 0..currentWord.length) {
            if (letter in currentWord ) {
                val letter_position =  currentWord.indexOf(letter,i)
                if (letter_position != -1) {
                    val hiddenWordBuilder = StringBuilder(hiddenWordTemp)
                    hiddenWordBuilder.setCharAt(letter_position, letter)
                    hiddenWordTemp = hiddenWordBuilder.toString()
                }
            }
        }
        return hiddenWordTemp
    }

    fun buyVocal(): Boolean {
        if (_point >= 500) {
            _point -= 500
            return true
        } else {
            return false
        }
    }


    fun guessWord() {
    }

    // Chekker om det valgte bogstav er inde i  listen over bogstaver for det nuværrende ord.
    // hvis bogstavet er en del af ordet, da tilføjes det til guessedcorret letters.

    lateinit var showInput: String
    fun checkGuess(input: String, editText: EditText) {
        // Tilføjer altid mellem da det ikke kan gættes
        if ((" ") in currentWordList) guessedCorrectLetters.add(" ")

        // Er det et rigtigt bogstav?
        // lav til for loop ( for (index, value)
        if (input in currentWordList) {
            addPoint(randomWheelField)
            guessedCorrectLetters.add(input)
            println(guessedCorrectLetters)
        } else {
            wrongGuess()
        }
        editText.setText("")
    }

    // hvis gættet er forkert mister brugeren et liv.
    fun wrongGuess() {
        _life -= 1
    }


    fun checkForWin(): Boolean {
        return guessedCorrectLetters.containsAll(currentWordList)
    }


    fun checkForGameLost(): Boolean {
        return life == 0
    }


    // contains ()
    // wordListCount  -> make a variabel for number of list
    // wordList -> Make a list from the split word from the number

    var word: String = "null"

    fun reinitializeNewGame() {
        _life = 5
        _point = 0

    }
}