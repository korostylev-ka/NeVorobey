package com.korostylev.nevorobey.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.korostylev.nevorobey.R
import com.korostylev.nevorobey.dao.ActiveGameDao
import com.korostylev.nevorobey.databinding.FragmentStartBinding
import com.korostylev.nevorobey.db.NeVorobeyDB
import com.korostylev.nevorobey.dto.Level
import com.korostylev.nevorobey.entity.ActiveGameEntity

// TODO: Rename parameter arguments, choose names that match

class StartFragment : Fragment() {
    private var _binding: FragmentStartBinding? = null
    private val binding: FragmentStartBinding
        get() = _binding ?: throw RuntimeException("FragmentStartBinding is null")
    private lateinit var activeGameDao: ActiveGameDao
    private var selectedLevel: Level? = null
    private lateinit var startButton: ImageView

    private fun moveToFragment(selectedLevel: Level) {
        val currentGame = activeGameDao.getCurrentGame()
        val selectedGameLevel = when (selectedLevel) {
            Level.EASY -> ActiveGameEntity.EASY
            Level.MEDIUM -> ActiveGameEntity.MEDIUM
            Level.HARD -> ActiveGameEntity.HARD
        }
        if (currentGame == null) {
            when (selectedLevel) {
                Level.EASY -> {
                    val fragment = FourLettersFragment.newInstance(NEW_GAME)
                    requireActivity().supportFragmentManager.beginTransaction()
                        .replace(R.id.fragment_container, fragment, null)
                        .addToBackStack(NEW_GAME_TAG)
                        .commit()

                }
                Level.MEDIUM -> {
                    val fragment = FiveLettersFragment.newInstance(NEW_GAME)
                    requireActivity().supportFragmentManager.beginTransaction()
                        .replace(R.id.fragment_container, fragment, null)
                        .addToBackStack(NEW_GAME_TAG)
                        .commit()
                }
                Level.HARD -> {
                val fragment = SixLettersFragment.newInstance(NEW_GAME)
                requireActivity().supportFragmentManager.beginTransaction()
                    .replace(R.id.fragment_container, fragment, null)
                    .addToBackStack(NEW_GAME_TAG)
                    .commit()

                }
            }
        } else {
            showContinueDialog(currentGame.currentGameLevel, selectedGameLevel)
        }
    }

    private fun bindViews() {
        startButton = binding.start
        startButton.isEnabled = false
    }

    private fun setClickListeners() {
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
    }

    private fun showContinueDialog(currentGameLevel: Int, selectedGameLevel: Int) {
        val fragment = ContinueDialogFragment.newInstance(currentGameLevel, selectedGameLevel)
        fragment.show(requireActivity().supportFragmentManager, null)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activeGameDao = NeVorobeyDB.getInstance(requireActivity()).activeGameDao
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentStartBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bindViews()
        setClickListeners()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        @JvmStatic
        fun newInstance() =
            StartFragment().apply {

            }

        private const val NEW_GAME = false
        private const val CONTINUE_GAME = true
        const val NEW_GAME_TAG = "NEW_GAME"

    }
}