package ru.korostylev.nevorobey.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import ru.korostylev.nevorobey.databinding.FragmentFinishBinding


class FinishFragment : Fragment() {

    private var hiddenWord: String = ""

    private var _binding: FragmentFinishBinding? = null
    private val binding: FragmentFinishBinding
        get() = _binding ?: throw RuntimeException("FragmentFinishBinding is null")


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
        _binding = FragmentFinishBinding.inflate(layoutInflater, container, false)
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

        @JvmStatic
        fun newInstance(word: String) =
            FinishFragment().apply {
                arguments = Bundle().apply {
                    putString(HIDDEN_WORD, word)
                }
            }
    }
}