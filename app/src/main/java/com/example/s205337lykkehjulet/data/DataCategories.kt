package com.example.s205337lykkehjulet.data

import com.example.s205337lykkehjulet.R
import com.example.s205337lykkehjulet.model.ImageButtonCategories

//klassen bruges til at generere et cardview som består af en String og et billede
class DataCategories {

    fun loadButtonContext(): List<ImageButtonCategories> {
        return listOf(
            ImageButtonCategories(R.string.button1_text, R.drawable.spinhjul1),
            ImageButtonCategories(R.string.button2_text, R.drawable.spinhjul2),
            ImageButtonCategories(R.string.button3_text, R.drawable.spinhjul3),
            ImageButtonCategories(R.string.button4_text, R.drawable.spinhjul4)
        )
    }
}