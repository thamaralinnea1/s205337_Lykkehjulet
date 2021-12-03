package com.example.s205337lykkehjulet.game

import android.widget.EditText
import androidx.lifecycle.ViewModel
import com.example.s205337lykkehjulet.data.*
import java.lang.StringBuilder

/**
 * Lavet i sparing med camilia Boejden s205360
 * Inspiration fundet fra Android CodeLab unit 3 pathway 3,
 * https://developer.android.com/courses/pathways/android-basics-kotlin-unit-3-pathway-3#codelab-https://developer.android.com/codelabs/basic-android-kotlin-training-livedata
 */

class GameViewHolder : ViewModel() {
    private lateinit var wordList: List<String>
    private val FieldList = fieldList
    lateinit var randomWheelField: String
    lateinit var currentWord: String
    private lateinit var hiddenWord: String
    private lateinit var currentWordList: MutableList<String>
    private val guessedCorrectLetters: MutableList<String> = arrayListOf()

    /**
     * Inspiration til at beyntte live data er fundet fra Andoid CodeLab unit 3 pathway 3
     ** https://developer.android.com/codelabs/basic-android-kotlin-training-livedata?continue=https%3A%2F%2Fdeveloper.android.com%2Fcourses%2Fpathways%2Fandroid-basics-kotlin-unit-3-pathway-3%23codelab-https%3A%2F%2Fdeveloper.android.com%2Fcodelabs%2Fbasic-android-kotlin-training-livedata#3
     */
    private var _life = 5
    val life: Int
        get() = _life

    private var _point = 0
    val point: Int
        get() = _point


    // Generere et tilfældigt felt på hjullet of returne det.
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

    // Funktion der tilføjer point til spillerens beholdning svarende til feltet på hjulet.
    private fun addPoint(randomWheelField: String): Int {
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

    // Returne en liste der svarer til den kategori der er valgt.
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

    /**
     * Hjulpet af hjælpelære
     */
    // Generere at ord ud fra en liste og benytter funktionen hideletters til at returne ordet som * frem for bogstaver.
    private fun newWord(): String {
        val currentWordNumber = (0 until (wordList.size)).random()
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

    /**
     * Hjulpet af hjælpelære
     */
    // Gemmer hvert bogstav fra currentWordList bag et symbol
    private fun hideLetter(): MutableList<String> {

        var hideCurrentWordList = ""
        for (i in 0 until currentWordList.size) {
            if (currentWordList[i] != " ") {
                hideCurrentWordList += "*"
            } else {
                hideCurrentWordList += " "
            }
        }
        return hideCurrentWordList.split("").toMutableList()
    }


    /**
     * Hjulpet af hjælpelære
     */
    // Viser ordet når det gættes rigtigt
    fun showLetter(letter: Char, hiddenWord: String): String {
        currentWord
        var hiddenWordTemp = hiddenWord
        for (i in 0..currentWord.length) {
            if (letter in currentWord) {
                val letterPosition = currentWord.indexOf(letter, i)
                if (letterPosition != -1) {
                    val hiddenWordBuilder = StringBuilder(hiddenWordTemp)
                    hiddenWordBuilder.setCharAt(letterPosition, letter)
                    hiddenWordTemp = hiddenWordBuilder.toString()
                }
            }
        }
        return hiddenWordTemp
    }

    // checke om spilleren har nok point til at købe en vokal
    fun buyVocal(): Boolean {
        return if (_point >= 500) {
            _point -= 500
            true
        } else {
            false
        }
    }


    /*Chekker om det valgte bogstav er inde i  listen over bogstaver for det nuværrende ord.
    Hvis bogstavet er en del af ordet, tilføjes det til listen guessedcorretletters.*/
    private lateinit var showInput: String
    fun checkGuess(input: String, editText: EditText) {
        // Tilføjer altid mellem da det ikke kan gættes
        if ((" ") in currentWordList) guessedCorrectLetters.add(" ")
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
    private fun wrongGuess() {
        _life -= 1
    }

    // Checker om alle i bogstaver i ordet er gættet.
    fun checkForWin(): Boolean {
        return guessedCorrectLetters.containsAll(currentWordList)
    }

    //Checker om spilleren har flere liv.
    fun checkForGameLost(): Boolean {
        return life == 0
    }

}