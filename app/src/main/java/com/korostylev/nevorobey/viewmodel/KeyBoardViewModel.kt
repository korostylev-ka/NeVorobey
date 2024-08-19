package com.korostylev.nevorobey.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class KeyBoardViewModel: ViewModel() {

    fun getTheLetterFromKeyboard(letter: String): String {
        Log.d("vorobey", letter)
        _keyboardText.value = letter
        return letter
    }
    private var emptyText = ""
    private val _keyboardText: MutableLiveData<String> = MutableLiveData(emptyText)
    val keyboardText: LiveData<String>
            get() = _keyboardText
}