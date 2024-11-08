package com.korostylev.nevorobey.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.activityViewModels
import com.korostylev.nevorobey.R
import com.korostylev.nevorobey.databinding.FragmentKeyboardBinding
import com.korostylev.nevorobey.dto.Answer
import com.korostylev.nevorobey.viewmodel.KeyBoardViewModel

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"
private const val BACKSPACE = "backspace"

/**
 * A simple [Fragment] subclass.
 * Use the [KeyboardFragment.newInstance] factory method to
 * create an instance of this fragment.
 */

class KeyboardFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private lateinit var letter1: TextView
    private lateinit var letter2: TextView
    private lateinit var letter3: TextView
    private lateinit var letter4: TextView
    private lateinit var letter5: TextView
    private lateinit var letter6: TextView
    private lateinit var letter7: TextView
    private lateinit var letter8: TextView
    private lateinit var letter9: TextView
    private lateinit var letter10: TextView
    private lateinit var letter11: TextView
    private lateinit var letter12: TextView
    private lateinit var letter13: TextView
    private lateinit var letter14: TextView
    private lateinit var letter15: TextView
    private lateinit var letter16: TextView
    private lateinit var letter17: TextView
    private lateinit var letter18: TextView
    private lateinit var letter19: TextView
    private lateinit var letter20: TextView
    private lateinit var letter21: TextView
    private lateinit var letter22: TextView
    private lateinit var letter23: TextView
    private lateinit var letter24: TextView
    private lateinit var letter25: TextView
    private lateinit var letter26: TextView
    private lateinit var letter27: TextView
    private lateinit var letter28: TextView
    private lateinit var letter29: TextView
    private lateinit var letter30: TextView
    private lateinit var letter31: TextView
    private lateinit var letter32: TextView
    private lateinit var letter33: TextView
    private lateinit var backspaceButton: TextView
    private val keyBoardViewModel: KeyBoardViewModel by activityViewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("vorobey", "keyboard oncreate")
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentKeyboardBinding.inflate(layoutInflater)
        letter1 = binding.letter1
        letter2 = binding.letter2
        letter3 = binding.letter3
        letter4 = binding.letter4
        letter5 = binding.letter5
        letter6 = binding.letter6
        letter7 = binding.letter7
        letter8 = binding.letter8
        letter9 = binding.letter9
        letter10 = binding.letter10
        letter11 = binding.letter11
        letter12 = binding.letter12
        letter13 = binding.letter13
        letter14 = binding.letter14
        letter15 = binding.letter15
        letter16 = binding.letter16
        letter17 = binding.letter17
        letter18 = binding.letter18
        letter19 = binding.letter19
        letter20 = binding.letter20
        letter21 = binding.letter21
        letter22 = binding.letter22
        letter23 = binding.letter23
        letter24 = binding.letter24
        letter25 = binding.letter25
        letter26 = binding.letter26
        letter27 = binding.letter27
        letter28 = binding.letter28
        letter29 = binding.letter29
        letter30 = binding.letter30
        letter31 = binding.letter31
        letter32 = binding.letter32
        letter33 = binding.letter33
        backspaceButton = binding.backspace
        keyBoardViewModel.keyBackground.observe(viewLifecycleOwner) {
            val letterIndex = it.first.index
            val background = when (it.second) {
                Answer.LETTER_IS_EXIST -> R.drawable.keyboard_cell_exist
                Answer.LETTER_IS_NOT_EXIST -> R.drawable.keyboard_cell_not_exist
                Answer.LETTER_POSITION_GUESSED -> R.drawable.keyboard_cell_guessed
                else -> R.drawable.keyboard_cell_default
            }
            when (letterIndex) {
                LETTER1 -> letter1.setBackgroundResource(background)
                LETTER2 -> letter2.setBackgroundResource(background)
                LETTER3 -> letter3.setBackgroundResource(background)
                LETTER4 -> letter4.setBackgroundResource(background)
                LETTER5 -> letter5.setBackgroundResource(background)
                LETTER6 -> letter6.setBackgroundResource(background)
                LETTER7 -> letter7.setBackgroundResource(background)
                LETTER8 -> letter8.setBackgroundResource(background)
                LETTER9 -> letter9.setBackgroundResource(background)
                LETTER10 -> letter10.setBackgroundResource(background)
                LETTER11 -> letter11.setBackgroundResource(background)
                LETTER12 -> letter12.setBackgroundResource(background)
                LETTER13 -> letter13.setBackgroundResource(background)
                LETTER14 -> letter14.setBackgroundResource(background)
                LETTER15 -> letter15.setBackgroundResource(background)
                LETTER16 -> letter16.setBackgroundResource(background)
                LETTER17 -> letter17.setBackgroundResource(background)
                LETTER18 -> letter18.setBackgroundResource(background)
                LETTER19 -> letter19.setBackgroundResource(background)
                LETTER20 -> letter20.setBackgroundResource(background)
                LETTER21 -> letter21.setBackgroundResource(background)
                LETTER22 -> letter22.setBackgroundResource(background)
                LETTER23 -> letter23.setBackgroundResource(background)
                LETTER24 -> letter24.setBackgroundResource(background)
                LETTER25 -> letter25.setBackgroundResource(background)
                LETTER26 -> letter26.setBackgroundResource(background)
                LETTER27 -> letter27.setBackgroundResource(background)
                LETTER28 -> letter28.setBackgroundResource(background)
                LETTER29 -> letter29.setBackgroundResource(background)
                LETTER30 -> letter30.setBackgroundResource(background)
                LETTER31 -> letter31.setBackgroundResource(background)
                LETTER32 -> letter32.setBackgroundResource(background)
                LETTER33 -> letter33.setBackgroundResource(background)


            }
        }
        letter1.setOnClickListener {
            keyBoardViewModel.getTheLetterFromKeyboard(letter1.text.toString())

        }
        letter2.setOnClickListener {
            keyBoardViewModel.getTheLetterFromKeyboard(letter2.text.toString())
        }
        letter3.setOnClickListener {
            keyBoardViewModel.getTheLetterFromKeyboard(letter3.text.toString())
        }
        letter4.setOnClickListener {
            keyBoardViewModel.getTheLetterFromKeyboard(letter4.text.toString())
        }
        letter5.setOnClickListener {
            keyBoardViewModel.getTheLetterFromKeyboard(letter5.text.toString())
        }
        letter6.setOnClickListener {
            keyBoardViewModel.getTheLetterFromKeyboard(letter6.text.toString())
        }
        letter7.setOnClickListener {
            keyBoardViewModel.getTheLetterFromKeyboard(letter7.text.toString())
        }
        letter8.setOnClickListener {
            keyBoardViewModel.getTheLetterFromKeyboard(letter8.text.toString())
        }
        letter9.setOnClickListener {
            keyBoardViewModel.getTheLetterFromKeyboard(letter9.text.toString())
        }
        letter10.setOnClickListener {
            keyBoardViewModel.getTheLetterFromKeyboard(letter10.text.toString())
        }
        letter11.setOnClickListener {
            keyBoardViewModel.getTheLetterFromKeyboard(letter11.text.toString())
        }
        letter12.setOnClickListener {
            keyBoardViewModel.getTheLetterFromKeyboard(letter12.text.toString())
        }
        letter13.setOnClickListener {
            keyBoardViewModel.getTheLetterFromKeyboard(letter13.text.toString())
        }
        letter14.setOnClickListener {
            keyBoardViewModel.getTheLetterFromKeyboard(letter14.text.toString())
        }
        letter15.setOnClickListener {
            keyBoardViewModel.getTheLetterFromKeyboard(letter15.text.toString())
        }
        letter16.setOnClickListener {
            keyBoardViewModel.getTheLetterFromKeyboard(letter16.text.toString())
        }
        letter17.setOnClickListener {
            keyBoardViewModel.getTheLetterFromKeyboard(letter17.text.toString())
        }
        letter18.setOnClickListener {
            keyBoardViewModel.getTheLetterFromKeyboard(letter18.text.toString())
        }
        letter19.setOnClickListener {
            keyBoardViewModel.getTheLetterFromKeyboard(letter19.text.toString())
        }
        letter20.setOnClickListener {
            keyBoardViewModel.getTheLetterFromKeyboard(letter20.text.toString())
        }
        letter21.setOnClickListener {
            keyBoardViewModel.getTheLetterFromKeyboard(letter21.text.toString())
        }
        letter22.setOnClickListener {
            keyBoardViewModel.getTheLetterFromKeyboard(letter22.text.toString())
        }
        letter23.setOnClickListener {
            keyBoardViewModel.getTheLetterFromKeyboard(letter23.text.toString())
        }
        letter24.setOnClickListener {
            keyBoardViewModel.getTheLetterFromKeyboard(letter24.text.toString())
        }
        letter25.setOnClickListener {
            keyBoardViewModel.getTheLetterFromKeyboard(letter25.text.toString())
        }
        letter26.setOnClickListener {
            keyBoardViewModel.getTheLetterFromKeyboard(letter26.text.toString())
        }
        letter27.setOnClickListener {
            keyBoardViewModel.getTheLetterFromKeyboard(letter27.text.toString())
        }
        letter28.setOnClickListener {
            keyBoardViewModel.getTheLetterFromKeyboard(letter28.text.toString())
        }
        letter29.setOnClickListener {
            keyBoardViewModel.getTheLetterFromKeyboard(letter29.text.toString())
        }
        letter30.setOnClickListener {
            keyBoardViewModel.getTheLetterFromKeyboard(letter30.text.toString())
        }
        letter31.setOnClickListener {
            keyBoardViewModel.getTheLetterFromKeyboard(letter31.text.toString())
        }
        letter32.setOnClickListener {
            keyBoardViewModel.getTheLetterFromKeyboard(letter32.text.toString())
        }
        letter33.setOnClickListener {
            keyBoardViewModel.getTheLetterFromKeyboard(letter33.text.toString())
        }
        backspaceButton.setOnClickListener {
            keyBoardViewModel.getTheLetterFromKeyboard(BACKSPACE)
        }

        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("vorobey", "keyboard ondestroy")
    }

    companion object {

        @JvmStatic
        fun newInstance() =
            KeyboardFragment().apply {
                arguments = Bundle().apply {

                }
            }

        const val LETTER1 = 1
        const val LETTER2 = 2
        const val LETTER3 = 3
        const val LETTER4 = 4
        const val LETTER5 = 5
        const val LETTER6 = 6
        const val LETTER7 = 7
        const val LETTER8 = 8
        const val LETTER9 = 9
        const val LETTER10 = 10
        const val LETTER11 = 11
        const val LETTER12 = 12
        const val LETTER13 = 13
        const val LETTER14 = 14
        const val LETTER15 = 15
        const val LETTER16 = 16
        const val LETTER17 = 17
        const val LETTER18 = 18
        const val LETTER19 = 19
        const val LETTER20 = 20
        const val LETTER21 = 21
        const val LETTER22 = 22
        const val LETTER23 = 23
        const val LETTER24 = 24
        const val LETTER25 = 25
        const val LETTER26 = 26
        const val LETTER27 = 27
        const val LETTER28 = 28
        const val LETTER29 = 29
        const val LETTER30 = 30
        const val LETTER31 = 31
        const val LETTER32 = 32
        const val LETTER33 = 33

    }


}