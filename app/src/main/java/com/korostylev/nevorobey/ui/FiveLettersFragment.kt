package com.korostylev.nevorobey.ui

import android.annotation.SuppressLint
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import com.korostylev.nevorobey.R
import com.korostylev.nevorobey.application.NeVorobeyApplication
import com.korostylev.nevorobey.databinding.FragmentFiveLettersBinding
import com.korostylev.nevorobey.databinding.FragmentFourLettersBinding
import com.korostylev.nevorobey.dto.Answer
import com.korostylev.nevorobey.entity.ActiveGameEntity
import com.korostylev.nevorobey.presenter.NeVorobeyPresenter
import com.korostylev.nevorobey.presenter.NeVorobeyPresenterImpl
import com.korostylev.nevorobey.viewmodel.KeyBoardViewModel
import kotlin.math.E

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"
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

/**
 * A simple [Fragment] subclass.
 * Use the [FiveLettersFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class FiveLettersFragment : Fragment(), ViewInterface, KeyboardAction {
    private var _binding: FragmentFiveLettersBinding? = null
    private val binding: FragmentFiveLettersBinding
        get() = _binding ?: throw RuntimeException("FragmentFiveLettersBinding is null")
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private var currentLetterPosition = 0
        set(value)  {
            if (value < 2) {
                field = 1
            } else {
                if (value > 6) {
                    field = 6
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
    private lateinit var cell11: TextView
    private lateinit var cell12: TextView
    private lateinit var cell13: TextView
    private lateinit var cell14: TextView
    private lateinit var cell15: TextView
    private lateinit var cell21: TextView
    private lateinit var cell22: TextView
    private lateinit var cell23: TextView
    private lateinit var cell24: TextView
    private lateinit var cell25: TextView
    private lateinit var cell31: TextView
    private lateinit var cell32: TextView
    private lateinit var cell33: TextView
    private lateinit var cell34: TextView
    private lateinit var cell35: TextView
    private lateinit var cell41: TextView
    private lateinit var cell42: TextView
    private lateinit var cell43: TextView
    private lateinit var cell44: TextView
    private lateinit var cell45: TextView
    private lateinit var cell51: TextView
    private lateinit var cell52: TextView
    private lateinit var cell53: TextView
    private lateinit var cell54: TextView
    private lateinit var cell55: TextView
    private lateinit var cell61: TextView
    private lateinit var cell62: TextView
    private lateinit var cell63: TextView
    private lateinit var cell64: TextView
    private lateinit var cell65: TextView
    private lateinit var letter1: TextView
    private lateinit var letter2: TextView
    private lateinit var letter3: TextView
    private lateinit var letter4: TextView
    private lateinit var letter5: TextView
    private lateinit var presenter: NeVorobeyPresenter
    private val keyBoardViewModel: KeyBoardViewModel by activityViewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
        presenter = NeVorobeyPresenterImpl(this, requireContext(), ActiveGameEntity.MEDIUM)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFiveLettersBinding.inflate(inflater, container, false)
        addKeyboardFragment()


        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bindViews()
        keyboardTextObserve()
        addTextWatchers()
        setClickListeners()
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

    private fun bindViews() {
        cell11 = binding.cell11
        cell12 = binding.cell12
        cell13 = binding.cell13
        cell14 = binding.cell14
        cell15 = binding.cell15
        cell21 = binding.cell21
        cell22 = binding.cell22
        cell23 = binding.cell23
        cell24 = binding.cell24
        cell25 = binding.cell25
        cell31 = binding.cell31
        cell32 = binding.cell32
        cell33 = binding.cell33
        cell34 = binding.cell34
        cell35 = binding.cell35
        cell41 = binding.cell41
        cell42 = binding.cell42
        cell43 = binding.cell43
        cell44 = binding.cell44
        cell45 = binding.cell45
        cell51 = binding.cell51
        cell52 = binding.cell52
        cell53 = binding.cell53
        cell54 = binding.cell54
        cell55 = binding.cell55
        cell61 = binding.cell61
        cell62 = binding.cell62
        cell63 = binding.cell63
        cell64 = binding.cell64
        cell65 = binding.cell65
        letter1 = binding.input1
        letter2 = binding.input2
        letter3 = binding.input3
        letter4 = binding.input4
        letter5 = binding.input5
        binding.buttonOk.isEnabled = false
        binding.buttonCancel.isEnabled = false
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
            letter4.text.isNotEmpty() && letter5.text.isNotEmpty()) {
            binding.buttonOk.isEnabled = true
        } else {
            binding.buttonOk.isEnabled = false
        }
        if (letter1.text.isNotEmpty() || letter2.text.isNotEmpty() || letter3.text.isNotEmpty() ||
            letter4.text.isNotEmpty() || letter5.text.isNotEmpty()) {
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
                }
                currentLetterPosition--
            } else {
                when (currentLetterPosition) {
                    FIRST_LETTER_POSITION -> letter1.text = it
                    SECOND_LETTER_POSITION -> letter2.text = it
                    THIRD_LETTER_POSITION -> letter3.text = it
                    FOURTH_LETTER_POSITION -> letter4.text = it
                    FIFTH_LETTER_POSITION -> letter5.text = it
                }
                currentLetterPosition++
            }
        }

    }


    companion object {

        @JvmStatic
        fun newInstance() =
            FiveLettersFragment()

        private const val EMPTY_TEXT = ""

    }

    private fun getTheWordFromLetters(): String {
        val word = letter1.text.toString() + letter2.text.toString() + letter3.text.toString() +
                letter4.text.toString() + letter5.text.toString()
        return word
    }

    private fun clearInput() {
        letter1.text = EMPTY_TEXT
        letter2.text = EMPTY_TEXT
        letter3.text = EMPTY_TEXT
        letter4.text = EMPTY_TEXT
        letter5.text = EMPTY_TEXT
        currentLetterPosition = 1
    }

    @SuppressLint("ResourceAsColor")
    fun changeViewBackground(textView: TextView, value: Int?) {
        when (value) {
            Answer.LETTER_POSITION_GUESSED -> {
                textView.setBackgroundResource(R.drawable.cell_text_view_a)
                textView.setTextColor(R.color.color_grey)
            }
            Answer.LETTER_IS_EXIST -> {
                textView.setBackgroundResource(R.drawable.cell_text_view_b)
                textView.setTextColor(R.color.color_grey)
            }
            else -> textView.setBackgroundResource(R.drawable.cell_text_view)
        }
    }

    fun changeCurrentCells(currentRow: Int) {
        when (currentRow) {
            ROW_ONE -> {
                currentCell1 = cell11
                currentCell2 = cell12
                currentCell3 = cell13
                currentCell4 = cell14
                currentCell5 = cell15

            }
            ROW_TWO -> {
                currentCell1 = cell21
                currentCell2 = cell22
                currentCell3 = cell23
                currentCell4 = cell24
                currentCell5 = cell25

            }
            ROW_THREE -> {
                currentCell1 = cell31
                currentCell2 = cell32
                currentCell3 = cell33
                currentCell4 = cell34
                currentCell5 = cell35

            }
            ROW_FOUR -> {
                currentCell1 = cell41
                currentCell2 = cell42
                currentCell3 = cell43
                currentCell4 = cell44
                currentCell5 = cell45

            }
            ROW_FIVE -> {
                currentCell1 = cell51
                currentCell2 = cell52
                currentCell3 = cell53
                currentCell4 = cell54
                currentCell5 = cell55

            }
            ROW_SIX -> {
                currentCell1 = cell61
                currentCell2 = cell62
                currentCell3 = cell63
                currentCell4 = cell64
                currentCell5 = cell65

            }

            else -> {
                currentCell1 = null
                currentCell2 = null
                currentCell3 = null
                currentCell4 = null
                currentCell5 = null
            }
        }
    }

    override fun checkWord(answer: Answer) {
        val letters = answer.getLetters()
        for (item in letters) {
            if (item != null) {
                keyBoardViewModel.changeLetterBackground(item.first, item.second)
            }
        }
        val letterOne = letter1.text.toString()
        val letterTwo = letter2.text.toString()
        val letterThree = letter3.text.toString()
        val letterFour = letter4.text.toString()
        val letterFive = letter5.text.toString()
        if (letterOne.isEmpty() || letterTwo.isEmpty() || letterThree.isEmpty() || letterFour.isEmpty()
            || letterFive.isEmpty()) {
            Toast.makeText(context, R.string.enter_all_letters, Toast.LENGTH_LONG)
                .show()
            return
        }
        changeCurrentCells(answer.getCurrentRow())
        currentCell1?.let {
            changeViewBackground(it, answer.getLetters()[0]?.second)
            it.text = letterOne
        }
        currentCell2?.let {
            changeViewBackground(it, answer.getLetters()[1]?.second)
            it.text = letterTwo
        }
        currentCell3?.let {
            changeViewBackground(it, answer.getLetters()[2]?.second)
            it.text = letterThree
        }
        currentCell4?.let {
            changeViewBackground(it, answer.getLetters()[3]?.second)
            it.text = letterFour
        }
        currentCell5?.let {
            changeViewBackground(it, answer.getLetters()[4]?.second)
            it.text = letterFive
        }
        clearInput()
    }

    override fun clearInputFields() {
        clearInput()
    }

    override fun pressed(text: String) {
        Log.d("vorobey", "text5")
    }

}