package com.example.s205337lykkehjulet.model

import android.media.Image
import android.os.Bundle
import android.view.View
import android.view.animation.RotateAnimation
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import com.example.s205337lykkehjulet.R


class spinningwheel() {
    private var count = 0
    private var flag = false
    private var spinButton: ImageView? = null

  // de forskellige muligheder spilleren kan lande på
  val options = listOf<String>("ekstraLiv", "800p", "750p, mistetLiv", "250", "1000p", "bankerot", "500p","ekstraLiv", "800p", "750p, mistetLiv", "250", "1000p", "bankerot", "500p" )
  var spinDuration: Long = 0
  var spinRevolution = 0f
  var prizeText = "N/A"
  var wheel: ImageView? = null


    fun spinStart () {
      spinButton = spinButton?.findViewById(R.id.spin)
      spinButton!!.setOnTouchListener(PowerTouchListner())
      intSpinner()

    }


  private fun intSpinner() {
    wheel = wheel?.findViewById(R.id.hjul)

  }
  fun startSpinner () {
    spinDuration = 3600
    spinRevolution = 5000f

    if (count >= 30) {
      spinDuration =1000
      spinRevolution = (3600*2).toFloat()
    }
     if (count>=60) {
       spinDuration =15000
       spinRevolution = (3600*3).toFloat()
     }
// vælger en random ud fra 360
    val end = Math.floor(Math.random()*3600).toInt()
    val sizeOfOptions = options.size
    val degreesPerOption = 360/sizeOfOptions
    val arrow = 0
    val optionIndex = (arrow + end) % sizeOfOptions


  }


  private fun PowerTouchListner(): View.OnTouchListener? {
return null
  }
}

