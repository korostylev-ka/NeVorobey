package com.korostylev.nevorobey.ui

import android.content.Context
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
import com.korostylev.nevorobey.presenter.NeVorobeyPresenter
import com.korostylev.nevorobey.viewmodel.KeyBoardViewModel

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

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
    private var keyboardAction: KeyboardAction? = null
    private var neVorobeyPresenter: NeVorobeyPresenter? = null
    private val keyBoardViewModel: KeyBoardViewModel by activityViewModels()


    override fun onAttach(context: Context) {
        super.onAttach(context)
        try {
            keyboardAction = context as KeyboardAction?
            neVorobeyPresenter = context as NeVorobeyPresenter?
        } catch (e: Exception) {
            Log.d("vorobey", e.message.toString())
        }

    }

    override fun onDetach() {
        super.onDetach()
        keyboardAction = null
        neVorobeyPresenter = null
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
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
        letter20 = binding.letter21
        letter22 = binding.letter22
        letter23 = binding.letter23
        letter24 = binding.letter24
        letter25 = binding.letter26
        letter27 = binding.letter27
        letter28 = binding.letter28
        letter29 = binding.letter29
        letter30 = binding.letter30
        letter31 = binding.letter31
        letter32 = binding.letter32
        letter33 = binding.letter33
        letter1.setOnClickListener {
//            keyboardAction?.pressed(letter1.text.toString())
            keyBoardViewModel.getTheLetterFromKeyboard(letter1.text.toString())
        }
        letter4.setOnClickListener {

            keyboardAction?.pressed(letter4.text.toString())
        }
        // Inflate the layout for this fragment
        return binding.root
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment KeyboardFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance() =
            KeyboardFragment().apply {
                arguments = Bundle().apply {

                }
            }
    }


}