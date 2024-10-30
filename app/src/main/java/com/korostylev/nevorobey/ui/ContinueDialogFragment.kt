package com.korostylev.nevorobey.ui

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.DialogFragment
import com.korostylev.nevorobey.R
import com.korostylev.nevorobey.entity.ActiveGameEntity

private const val CURRENT_GAME_LEVEL = "level"
private const val SELECTED_GAME_LEVEL = "selectedLevel"

class ContinueDialogFragment: DialogFragment() {

    private var currentGameLevel = 0
    private var selectedGameLevel = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            currentGameLevel = it.getInt(CURRENT_GAME_LEVEL)
            selectedGameLevel = it.getInt(SELECTED_GAME_LEVEL)
        }
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return AlertDialog.Builder(context)
            .setMessage(R.string.have_current_game)
            .setPositiveButton(R.string.start_new_dialog) {_,_->
                moveToGameFragment(selectedGameLevel)
            }
            .setNegativeButton(R.string.continue_dialog) {_,_->
                requireActivity().supportFragmentManager.popBackStack()
            }
            .setNeutralButton(R.string.back_dialog) {_,_->
                requireActivity().supportFragmentManager.popBackStack()
            }
            .create()
    }

    fun moveToGameFragment(selectedLevel: Int) {
        val fragment = when (selectedLevel) {
            ActiveGameEntity.EASY -> FourLettersFragment.newInstance()
            ActiveGameEntity.MEDIUM -> FiveLettersFragment.newInstance()
            ActiveGameEntity.HARD -> SixLettersFragment.newInstance()
            else -> throw RuntimeException("No level!")
        }
        requireActivity().supportFragmentManager
            .beginTransaction()
            .replace(R.id.fragment_container, fragment)
            .commit()
    }

    companion object {
        fun newInstance(currentGameLevel: Int, selectedGameLevel: Int) = ContinueDialogFragment().apply {
            arguments = Bundle().apply {
                putInt(CURRENT_GAME_LEVEL, currentGameLevel)
                putInt(SELECTED_GAME_LEVEL, selectedGameLevel)
            }
        }
    }
}