package com.example.s205337lykkehjulet.game

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.view.*
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import androidx.fragment.app.Fragment
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import com.example.s205337lykkehjulet.R
import com.example.s205337lykkehjulet.databinding.FragmentSecondFragmentBinding
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import kotlinx.android.synthetic.main.fragment_second_fragment.*

class GameFragment : Fragment() {
    private val viewModel: GameViewHolder by viewModels()
    private lateinit var binding: FragmentSecondFragmentBinding


    @SuppressLint("SetTextI18n")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflater layoutet fra dette fragment igennem binding
        binding = FragmentSecondFragmentBinding.inflate(inflater, container, false)
        val category = context?.resources?.getString(requireArguments().getInt("Title"))

        // Tildeler variablen en kategori ud fra hvilket cardView der af trykket på.
        binding.guessingWord.text = viewModel.setCategory(category!!)

        //sætter liv, point og kategori på fragmentet
        binding.pointsCount.text = "Point: ${viewModel.point}"
        binding.lifeCount.text = "${viewModel.life}"
        binding.categoryTitle.text = category

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.guessWord.isEnabled = false
        binding.guessButton.isEnabled = false
        binding.guessConsonant.isEnabled = false
        binding.guessVocal.isEnabled = false


        // knytter funktioner til knapperne i fragmentet
        binding.spin.setOnClickListener { activateWheel(view)
                        createKeyboard(guessConsonant)
                        binding.guessConsonant.isEnabled = true
                        binding.guessButton.isEnabled = true
                         guessConsonant.showkeyboard()
        }
        binding.guessWordButton.setOnClickListener{
            binding.guessWord.isEnabled = true
            binding.guessButton.isEnabled = true
            binding.guessWord.showkeyboard()
        }
        binding.guessVocalButton.setOnClickListener { buyVocalButton() }
        binding.guessButton.setOnClickListener { letterSubmitted(view) }
        binding.help.setOnClickListener { rules() }
    }

    // funktion der inflater fragmentet fragment_rules ud navigationsniagrammet
    private fun rules() {
        findNavController().navigate(R.id.action_second_fragment_to_rulesFragment)
    }

    //aktivere hjulet og generee et felt ud fra spinWheel ()
    private fun activateWheel(view: View) {
        binding.landedOnField.text = viewModel.spinWheel()
        binding.lifeCount.text = "${viewModel.life}"
        if (viewModel.randomWheelField == "Bankerot") {
            loseDialog(view)
        }
        if (viewModel.life == 0) {
            loseDialog(view)
        }
    }

    // lader brugeren købe en vokal og åbner keyboard
    @SuppressLint("SetTextI18n")
    private fun buyVocalButton() {
        if (viewModel.buyVocal() === true) {
            binding.pointsCount.text = "Point: ${viewModel.point}"
            binding.landedOnField.text = "Gæt på en vokal"
            createKeyboard(guessVocal)
            guessVocal.isEnabled = true
            binding.guessVocal.showkeyboard()
        }

        // https://stackoverflow.com/questions/2506876/how-to-change-position-of-toast-in-android/2507069
        else {
            Toast.makeText(
                activity, "Du har ikke nok point til at købe en vokal!" +
                        "Spin hjulet og gæt på en konsonant ", Toast.LENGTH_LONG
            ).show()
        }
    }


    /* Tager input fra brugeren og tildeler det til en variabel.
       Variablen bruges til at kalde på metoden checkguess der checker om input er en del af ordet der skal gættes.
       Der checkes altid ved letter submitted om hele ordet er gættet i gennem checkForWin.*/

    @SuppressLint("SetTextI18n")
    private fun letterSubmitted(view: View) {
        lateinit var input: String
        if (binding.guessConsonant.text.toString() != "") {
            input = binding.guessConsonant.text.toString()
            viewModel.checkGuess(input, guessConsonant)
        }
        else if (binding.guessWord.text.toString() != "") {
            if(binding.guessWord.text.toString().equals(viewModel.currentWord)) {
                binding.guessingWord.text = viewModel.currentWord
                return winDialog(view)
            }
        }
        else  {
            input = binding.guessVocal.text.toString()
            viewModel.randomWheelField = "Gæt Vokal"
            viewModel.checkGuess(input, guessVocal)
        }
        binding.guessingWord.text =
            viewModel.showLetter(input.first(), binding.guessingWord.text.toString())
        binding.pointsCount.text = "Point: ${viewModel.point}"
        binding.lifeCount.text = "${viewModel.life}"
        viewModel.checkForWin()
        if (viewModel.checkForWin() === true) {
            return winDialog(view)
        }
        if (viewModel.checkForGameLost() === true){
            return loseDialog(view)
        }
        // Skal laves om så feletet også fjernes fra currenField i View Model
        landed_on_field.text = ""
        binding.guessButton.isEnabled = false
    }

    // Hvis spilleren vinder kaldes denne Dialog som giver spilleren mulighed for at gå ud af eller spille igen.
    private fun winDialog(view: View) {
        MaterialAlertDialogBuilder(requireContext())
            .setTitle(getString(R.string.game_won))
            .setMessage(getString(R.string.dialogMessage))
            .setCancelable(false)
            .setNegativeButton(getString(R.string.exit)) { _, _ -> exitGame() }
            .setPositiveButton(getString(R.string.play_again)) { _, _ -> restartGame(view) }
            .show()
    }
    // Hvis spilleren taber kaldes denne Dialog.
    private fun loseDialog(view: View) {
        MaterialAlertDialogBuilder(requireContext())
            .setTitle(getString(R.string.game_lost))
            .setCancelable(false)
            .setNegativeButton(getString(R.string.exit)) { _, _ -> exitGame() }
            .setPositiveButton(getString(R.string.play_again)) { _, _ -> restartGame(view) }
            .show()
    }

    private fun exitGame() {
        activity?.finish()
    }

    /* Hvis spilleren trykker på dialog knappen "Spil igen tages spilleren tilbage
       til FragmentStart hvor de kna vælge en ny kategori*/
    private fun restartGame(view: View) {
     Navigation.findNavController(view).navigate(GameFragmentDirections.actionSecondFragmentToStart())
    }

    // Viser keyboarded på skærmen.
    private fun View.showkeyboard() {
        this.requestFocus()
        val inputMethodManager =
            context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.showSoftInput(this, InputMethodManager.SHOW_IMPLICIT)
    }

    private fun createKeyboard (editText: EditText) {
        val hidekeyboard = context?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        editText.addTextChangedListener { hidekeyboard.hideSoftInputFromWindow(view?.windowToken,0) }
    }
}


