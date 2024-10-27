package com.korostylev.nevorobey.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.korostylev.nevorobey.R
import com.korostylev.nevorobey.databinding.FragmentFourLettersBinding


class FourLettersFragment : Fragment() {
    private var _binding: FragmentFourLettersBinding? = null
    private val binding: FragmentFourLettersBinding
        get() = _binding ?: throw RuntimeException("FragmentFourLettersBinding is null")

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentFourLettersBinding.inflate(inflater, container, false)
        return binding.root
    }

    companion object {
        @JvmStatic
        fun newInstance(): FourLettersFragment {
            return FourLettersFragment()
        }

    }
}