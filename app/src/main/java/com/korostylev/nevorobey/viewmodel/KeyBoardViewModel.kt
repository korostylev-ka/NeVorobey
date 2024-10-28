package com.korostylev.nevorobey.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.korostylev.nevorobey.dto.Letters

class KeyBoardViewModel: ViewModel() {

    private var emptyText = ""

    private val _keyboardText: MutableLiveData<String> = MutableLiveData(emptyText)
    private val _keyBackground: MutableLiveData<Pair<Letters, Int>> = MutableLiveData()
    val keyboardText: LiveData<String>
        get() = _keyboardText
    val keyBackground: LiveData<Pair<Letters, Int>>
        get() = _keyBackground

    fun getTheLetterFromKeyboard(letter: String): String {
        Log.d("vorobey", letter)
        _keyboardText.value = letter
        return letter
    }

    fun changeLetterBackground(letter: Letters, value: Int) {
        _keyBackground.value = Pair(letter, value)
    }

}