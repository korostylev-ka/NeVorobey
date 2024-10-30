package com.korostylev.nevorobey.ui

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.activityViewModels
import com.korostylev.nevorobey.R
import com.korostylev.nevorobey.application.NeVorobeyApplication
import com.korostylev.nevorobey.databinding.FragmentSixLettersBinding
import com.korostylev.nevorobey.dto.Answer
import com.korostylev.nevorobey.entity.ActiveGameEntity
import com.korostylev.nevorobey.presenter.NeVorobeyPresenter
import com.korostylev.nevorobey.presenter.NeVorobeyPresenterImpl
import com.korostylev.nevorobey.viewmodel.KeyBoardViewModel

private const val BACKSPACE = "backspace"
private const val ROW_ONE = 1
private const val ROW_TWO = 2
private const val ROW_THREE = 3
private const val ROW_FOUR = 4
private const val ROW_FIVE = 5
private const val ROW_SIX = 6
private const val FIRST_LETTER_POSITION = 1
private const val SECOND_LETTER_POSITION = 2
private const val THIRD_LETTER_POSITION = 3
private const val FOURTH_LETTER_POSITION = 4
private const val FIFTH_LETTER_POSITION = 5
private const val SIXTH_LETTER_POSITION = 6
private const val SEVENTH_LETTER_POSITION = 7

class SixLettersFragment : Fragment(), ViewInterface, KeyboardAction {
    private var _binding: FragmentSixLettersBinding? = null
    private val binding: FragmentSixLettersBinding
        get() = _binding ?: throw RuntimeException("FragmentSixLettersBinding is null")
    private var currentLetterPosition = 0
        set(value)  {
            if (value < 2) {
                field = 1
            } else {
                if (value > 7) {
                    field = 7
                } else {
                    field = value
                }
            }
        }

    private var currentCell1: TextView? =null
    private var currentCell2: TextView? = null
    private var currentCell3: TextView? = null
    private var currentCell4: TextView? = null
    private var currentCell5: TextView? = null
    private var currentCell6: TextView? = null
    private lateinit var cell11: TextView
    private lateinit var cell12: TextView
    private lateinit var cell13: TextView
    private lateinit var cell14: TextView
    private lateinit var cell15: TextView
    private lateinit var cell16: TextView
    private lateinit var cell21: TextView
    private lateinit var cell22: TextView
    private lateinit var cell23: TextView
    private lateinit var cell24: TextView
    private lateinit var cell25: TextView
    private lateinit var cell26: TextView
    private lateinit var cell31: TextView
    private lateinit var cell32: TextView
    private lateinit var cell33: TextView
    private lateinit var cell34: TextView
    private lateinit var cell35: TextView
    private lateinit var cell36: TextView
    private lateinit var cell41: TextView
    private lateinit var cell42: TextView
    private lateinit var cell43: TextView
    private lateinit var cell44: TextView
    private lateinit var cell45: TextView
    private lateinit var cell46: TextView
    private lateinit var cell51: TextView
    private lateinit var cell52: TextView
    private lateinit var cell53: TextView
    private lateinit var cell54: TextView
    private lateinit var cell55: TextView
    private lateinit var cell56: TextView
    private lateinit var cell61: TextView
    private lateinit var cell62: TextView
    private lateinit var cell63: TextView
    private lateinit var cell64: TextView
    private lateinit var cell65: TextView
    private lateinit var cell66: TextView
    private lateinit var letter1: TextView
    private lateinit var letter2: TextView
    private lateinit var letter3: TextView
    private lateinit var letter4: TextView
    private lateinit var letter5: TextView
    private lateinit var letter6: TextView
    private lateinit var presenter: NeVorobeyPresenter
    private val keyBoardViewModel: KeyBoardViewModel by activityViewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        presenter = NeVorobeyPresenterImpl(this, requireContext(), ActiveGameEntity.HARD)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentSixLettersBinding.inflate(inflater, container, false)
        addKeyboardFragment()


        addTextWatchers()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        keyboardTextObserve()
        bindViews()
        addTextWatchers()
        setClickListeners()
    }

    private fun bindViews() {
        cell11 = binding.cell11
        cell12 = binding.cell12
        cell13 = binding.cell13
        cell14 = binding.cell14
        cell15 = binding.cell15
        cell16 = binding.cell16
        cell21 = binding.cell21
        cell22 = binding.cell22
        cell23 = binding.cell23
        cell24 = binding.cell24
        cell25 = binding.cell25
        cell26 = binding.cell26
        cell31 = binding.cell31
        cell32 = binding.cell32
        cell33 = binding.cell33
        cell34 = binding.cell34
        cell35 = binding.cell35
        cell36 = binding.cell36
        cell41 = binding.cell41
        cell42 = binding.cell42
        cell43 = binding.cell43
        cell44 = binding.cell44
        cell45 = binding.cell45
        cell46 = binding.cell46
        cell51 = binding.cell51
        cell52 = binding.cell52
        cell53 = binding.cell53
        cell54 = binding.cell54
        cell55 = binding.cell55
        cell56 = binding.cell56
        cell61 = binding.cell61
        cell62 = binding.cell62
        cell63 = binding.cell63
        cell64 = binding.cell64
        cell65 = binding.cell65
        cell66 = binding.cell66
        letter1 = binding.input1
        letter2 = binding.input2
        letter3 = binding.input3
        letter4 = binding.input4
        letter5 = binding.input5
        letter6 = binding.input6
        binding.buttonOk.isEnabled = false
        binding.buttonCancel.isEnabled = false
    }

    private fun setClickListeners() {
        binding.buttonOk.setOnClickListener {
            val word = getTheWordFromLetters()
            presenter.checkWord(word)
        }
        binding.buttonCancel.setOnClickListener {
            presenter.clearInputFields()
        }
    }

    private fun addKeyboardFragment() {
        val currentFragment = requireActivity().supportFragmentManager.findFragmentById(R.id.keyboard)
        if (currentFragment == null) {
            val fragment = KeyboardFragment.newInstance()
            requireActivity().supportFragmentManager.beginTransaction()
                .add(R.id.keyboard, fragment, null)
                .commit()
        }
    }

    fun switchButtons() {
        if (letter1.text.isNotEmpty() && letter2.text.isNotEmpty() && letter3.text.isNotEmpty() &&
            letter4.text.isNotEmpty() && letter5.text.isNotEmpty() && letter6.text.isNotEmpty()) {
            binding.buttonOk.isEnabled = true
        } else {
            binding.buttonOk.isEnabled = false
        }
        if (letter1.text.isNotEmpty() || letter2.text.isNotEmpty() || letter3.text.isNotEmpty() ||
            letter4.text.isNotEmpty() || letter5.text.isNotEmpty() || letter6.text.isNotEmpty()) {
            binding.buttonCancel.isEnabled = true
        } else {
            binding.buttonCancel.isEnabled = false
        }
    }

    private fun addTextWatchers() {
        val inputTextWatcherLetterOne = object : TextWatcher {

            var position = 0

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                position = p1
            }

            override fun afterTextChanged(p0: Editable?) {
                p0?.let {//
                    if (it.length == 1 && it.toString().all { !it.isLetter() }) {
                        p0.delete(position, position + 1)
                    }
                    if (it.length == 1 && it.toString().all { it.isLetter() }) {
                        letter2.requestFocus()
                    }
                    if (it.length > 1) {

                        p0.delete(position, position + 1)
                    }
                    switchButtons()
                }
            }
        }

        val inputTextWatcherLetterTwo = object : TextWatcher {
            var position = 0

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                position = p1


            }

            override fun afterTextChanged(p0: Editable?) {
                p0?.let {
                    if (it.length == 1 && it.toString().all { !it.isLetter() }) {
                        p0.delete(position, position + 1)
                    }
                    if (it.length == 1 && it.toString().all { it.isLetter() }) {
                        letter3.requestFocus()
                    }
                    if (it.length > 1) {

                        p0.delete(position, position + 1)
                    }
                    switchButtons()
                }
            }
        }

        val inputTextWatcherLetterThree = object : TextWatcher {
            var position = 0

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                position = p1


            }

            override fun afterTextChanged(p0: Editable?) {
                p0?.let {
                    if (it.length == 1 && it.toString().all { !it.isLetter() }) {
                        p0.delete(position, position + 1)
                    }
                    if (it.length == 1 && it.toString().all { it.isLetter() }) {
                        letter4.requestFocus()
                    }
                    if (it.length > 1) {

                        p0.delete(position, position + 1)
                    }
                    switchButtons()
                }
            }
        }

        val inputTextWatcherLetterFour = object : TextWatcher {
            var position = 0

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                position = p1


            }

            override fun afterTextChanged(p0: Editable?) {
                p0?.let {
                    if (it.length == 1 && it.toString().all { !it.isLetter() }) {
                        p0.delete(position, position + 1)
                    }
                    if (it.length == 1 && it.toString().all { it.isLetter() }) {
                        letter5.requestFocus()
                    }
                    if (it.length > 1) {

                        p0.delete(position, position + 1)
                    }
                    switchButtons()
                }
            }
        }

        val inputTextWatcherLetterFive = object : TextWatcher {
            var position = 0

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                position = p1


            }

            override fun afterTextChanged(p0: Editable?) {
                p0?.let {
                    if (it.length == 1 && it.toString().all { !it.isLetter() }) {
                        p0.delete(position, position + 1)
                    }
                    if (it.length == 1 && it.toString().all { it.isLetter() }) {
                        letter6.requestFocus()
                    }
                    if (it.length > 1) {

                        p0.delete(position, position + 1)
                    }
                    switchButtons()
                }
            }
        }
        val inputTextWatcherLetterSix = object : TextWatcher {
            var position = 0

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                position = p1


            }

            override fun afterTextChanged(p0: Editable?) {
                p0?.let {
                    if (it.length == 1 && it.toString().all { !it.isLetter() }) {
                        p0.delete(position, position + 1)
                    }

                    if (it.length > 1) {

                        p0.delete(position, position + 1)
                    }
                    switchButtons()
                }
            }
        }
        binding.input1.addTextChangedListener(inputTextWatcherLetterOne)
        binding.input2.addTextChangedListener(inputTextWatcherLetterTwo)
        binding.input3.addTextChangedListener(inputTextWatcherLetterThree)
        binding.input4.addTextChangedListener(inputTextWatcherLetterFour)
        binding.input5.addTextChangedListener(inputTextWatcherLetterFive)
        binding.input6.addTextChangedListener(inputTextWatcherLetterSix)
    }

    private fun keyboardTextObserve() {
        keyBoardViewModel.keyboardText.observe(viewLifecycleOwner) {
            if (it == BACKSPACE) {
                when (currentLetterPosition) {
                    FIRST_LETTER_POSITION -> letter1.text = EMPTY_TEXT
                    SECOND_LETTER_POSITION -> letter1.text = EMPTY_TEXT
                    THIRD_LETTER_POSITION -> letter2.text = EMPTY_TEXT
                    FOURTH_LETTER_POSITION -> letter3.text = EMPTY_TEXT
                    FIFTH_LETTER_POSITION -> letter4.text = EMPTY_TEXT
                    SIXTH_LETTER_POSITION -> letter5.text = EMPTY_TEXT
                    SEVENTH_LETTER_POSITION -> letter6.text = EMPTY_TEXT
                }
                currentLetterPosition--
            } else {
                when (currentLetterPosition) {
                    FIRST_LETTER_POSITION -> letter1.text = it
                    SECOND_LETTER_POSITION -> letter2.text = it
                    THIRD_LETTER_POSITION -> letter3.text = it
                    FOURTH_LETTER_POSITION -> letter4.text = it
                    FIFTH_LETTER_POSITION -> letter5.text = it
                    SIXTH_LETTER_POSITION -> letter6.text = it
                }
                currentLetterPosition++
            }
        }

    }

    private fun getTheWordFromLetters(): String {
        val word = letter1.text.toString() + letter2.text.toString() + letter3.text.toString() +
                letter4.text.toString() + letter5.text.toString() + letter6.text.toString()
        return word
    }

    private fun clearInput() {
        letter1.text = EMPTY_TEXT
        letter2.text = EMPTY_TEXT
        letter3.text = EMPTY_TEXT
        letter4.text = EMPTY_TEXT
        letter5.text = EMPTY_TEXT
        letter6.text = EMPTY_TEXT
        currentLetterPosition = 1
    }

    companion object {
        @JvmStatic
        fun newInstance(): SixLettersFragment {
            return SixLettersFragment()
        }

        private const val EMPTY_TEXT = ""
    }

    override fun pressed(text: String) {
        TODO("Not yet implemented")
    }

    override fun checkWord(answer: Answer) {
        TODO("Not yet implemented")
    }

    override fun clearInputFields() {
        TODO("Not yet implemented")
    }
}