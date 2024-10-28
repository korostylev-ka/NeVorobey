package com.korostylev.nevorobey.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.korostylev.nevorobey.R
import com.korostylev.nevorobey.databinding.FragmentSixLettersBinding

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [SixLettersFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class SixLettersFragment : Fragment() {
    private var _binding: FragmentSixLettersBinding? = null
    private val binding: FragmentSixLettersBinding
        get() = _binding ?: throw RuntimeException("FragmentSixLettersBinding is null")

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentSixLettersBinding.inflate(inflater, container, false)
        return binding.root
    }

    companion object {
        @JvmStatic
        fun newInstance(): SixLettersFragment {
            return SixLettersFragment()
        }
    }
}