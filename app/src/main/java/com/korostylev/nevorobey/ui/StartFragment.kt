package com.korostylev.nevorobey.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.korostylev.nevorobey.R
import com.korostylev.nevorobey.databinding.FragmentStartBinding
import com.korostylev.nevorobey.dto.Level

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [StartFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class StartFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private var selectedLevel: Level? = null
    private lateinit var startButton: ImageView

    fun moveToFragment(selectedLevel: Level) {
        when (selectedLevel) {
            Level.EASY -> {
                val fragment = FourLettersFragment.newInstance()
                requireActivity().supportFragmentManager.beginTransaction()
                    .replace(R.id.fragment_container, fragment, null)
                    .commit()

            }
            Level.MEDIUM -> {
                val fragment = FiveLettersFragment.newInstance()
                requireActivity().supportFragmentManager.beginTransaction()
                    .replace(R.id.fragment_container, fragment, null)
                    .commit()
            }
            Level.HARD -> {
                val fragment = SixLettersFragment.newInstance()
                requireActivity().supportFragmentManager.beginTransaction()
                    .replace(R.id.fragment_container, fragment, null)
                    .commit()

            }
        }
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
        val binding = FragmentStartBinding.inflate(layoutInflater)
        startButton = binding.start
        startButton.isEnabled = false
        binding.easyContainer.setOnClickListener {
            it.setBackgroundResource(R.drawable.easy_stroke)
            binding.mediumContainer.setBackgroundResource(R.drawable.empty_stroke)
            binding.hardContainer.setBackgroundResource(R.drawable.empty_stroke)
            selectedLevel = Level.EASY
            startButton.isEnabled = true
        }
        binding.mediumContainer.setOnClickListener {
            it.setBackgroundResource(R.drawable.medium_stroke)
            binding.easyContainer.setBackgroundResource(R.drawable.empty_stroke)
            binding.hardContainer.setBackgroundResource(R.drawable.empty_stroke)
            selectedLevel = Level.MEDIUM
            startButton.isEnabled = true
        }
        binding.hardContainer.setOnClickListener {
            it.setBackgroundResource(R.drawable.hard_stroke)
            binding.mediumContainer.setBackgroundResource(R.drawable.empty_stroke)
            binding.easyContainer.setBackgroundResource(R.drawable.empty_stroke)
            selectedLevel = Level.HARD
            startButton.isEnabled = true
        }
        binding.start.setOnClickListener {
            moveToFragment(selectedLevel!!)
        }

        // Inflate the layout for this fragment
        return binding.root
    }

    companion object {
        @JvmStatic
        fun newInstance() =
            StartFragment().apply {

            }
    }
}