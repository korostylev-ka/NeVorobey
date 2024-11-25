package com.korostylev.nevorobey.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.korostylev.nevorobey.R
import com.korostylev.nevorobey.databinding.FragmentFinishBinding
import com.korostylev.nevorobey.databinding.FragmentWinBinding

class WinFragment : Fragment() {
    private var hiddenWord: String = ""

    private var _binding: FragmentWinBinding? = null
    private val binding: FragmentWinBinding
        get() = _binding ?: throw RuntimeException("FragmentWinBinding is null")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            hiddenWord = it.getString(HIDDEN_WORD).toString()
        }
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentWinBinding.inflate(layoutInflater, container, false)
        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.tryImg.setOnClickListener {
            requireActivity().supportFragmentManager
                .popBackStack()
        }
        binding.tryText.setOnClickListener {
            requireActivity().supportFragmentManager
                .popBackStack()
        }
        binding.exitImg.setOnClickListener {
            requireActivity().finish()
        }
        binding.exitText.setOnClickListener {
            requireActivity().finish()
        }
        binding.wordHiddenText.text = hiddenWord.uppercase()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {

        private const val HIDDEN_WORD = "hidden_word"

        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(word: String) =
            WinFragment().apply {
                arguments = Bundle().apply {
                    putString(HIDDEN_WORD, word)
                }
            }
    }
}