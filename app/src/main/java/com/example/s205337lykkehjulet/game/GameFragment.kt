package com.example.s205337lykkehjulet.game

import android.content.Context
import android.os.Bundle
import android.text.method.DigitsKeyListener
import android.view.*
import android.view.inputmethod.InputMethodManager
import androidx.fragment.app.Fragment
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
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
        binding.pointText.text = "Point: ${viewModel.point}"

        // brugte vi til at komme ind i de forskellige kategoricard viewms.
        // viewModel.generateWord(category!!)
        // root.findViewById<TextView>(R.id.testTxt).text = viewModel.word

        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // fjerner keyboard fra skræm efter at der er indtastet et bogstav
        val hideKeyboardVocal =
            context?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        binding.guessVocal.addTextChangedListener {
            hideKeyboardVocal.hideSoftInputFromWindow(
                view.windowToken, 0
            )
        }

        val hideKeyboardConsonant =
            context?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        binding.guessConsonant.addTextChangedListener {
            hideKeyboardConsonant.hideSoftInputFromWindow(
                view.windowToken, 0
            )
        }

        guessConsonant.isEnabled = false
        binding.spin.setOnClickListener {
            activateWheel()
            binding.guessConsonant.isEnabled = true
            binding.guessConsonant.showkeyboard()
        }

        binding.kobVokal.setOnClickListener { buyVocalButton() }
        binding.getOrd.setOnClickListener { letterSubmitted() }


        // https://stackoverflow.com/questions/47298935/handling-enter-key-on-edittext-kotlin-android
        /* guess_consonant.setOnKeyListener(View.OnKeyListener { v, keyCode, event ->
            if (keyCode == KeyEvent.KEYCODE_ENTER && event.action == KeyEvent.ACTION_DOWN) {
                //Perform Code
                letterSubmitted()
                guess.setText("")
                return@OnKeyListener true
            }
            false
        })*/
    }

    private fun activateWheel() {
        binding.landedOnField.text = viewModel.spinWheel()
        binding.lifeCount.text = "${viewModel.life}"
        if (viewModel.randomWheelField == "Bankerot") {
            loseDialog()
        }
    }

    private fun buyVocalButton() {
        if (viewModel.buyVocal() === true) {
            binding.pointsCount.text = "Point: ${viewModel.point}"
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

    // Tager input fra brugeren og tildeler det til en variabel.
    // Variablen bruges til at kalde på metoden checkguess der checker om input er en del af ordet der skal gættes.
    // Der checkes altid ved letter submitted om hele ordet er gættet i gennem checkForWin.


    private fun letterSubmitted() {
        val input = binding.guess.text.toString()
        viewModel.checkGuess(input)
        binding.pointsCount.text = "Point: ${viewModel.point}"
        binding.lifeCount.text = "${viewModel.life}"
        viewModel.checkForWin()
        if (viewModel.checkForWin() === true) {
            return winDialog()
        }
        // Skal laves om så feletet også fjernes fra currenField i View Model
        landed_on_field.setText("")
    }


    // Hvis spilleren vinder kaldes denne Dialog som giver spilleren mulighed for at gå ud af eller spille igen.
    private fun winDialog() {
        MaterialAlertDialogBuilder(requireContext())
            .setTitle(getString(R.string.game_won))
            .setMessage(getString(R.string.dialogMessage))
            .setNegativeButton(getString(R.string.exit)) { _, _ -> exitGame() }
            .setPositiveButton(getString(R.string.play_again)) { _, _ -> restartGame() }
            .show()
    }

    private fun loseDialog() {
        MaterialAlertDialogBuilder(requireContext())
            .setTitle(getString(R.string.game_lost))
            .setNegativeButton(getString(R.string.exit)) { _, _ -> exitGame() }
            .setPositiveButton(getString(R.string.play_again)) { _, _ -> restartGame() }
            .show()
    }

    private fun exitGame() {
        activity?.finish()
    }

    private fun restartGame() {
        viewModel.reinitializeNewGame()
    }


    private fun View.showkeyboard() {
        this.requestFocus()
        val inputMethodManager =
            context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.showSoftInput(this, InputMethodManager.SHOW_IMPLICIT)
    }

}


