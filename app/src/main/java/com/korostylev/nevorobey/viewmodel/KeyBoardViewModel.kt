package com.korostylev.nevorobey.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class KeyBoardViewModel: ViewModel() {

    fun getTheLetterFromKeyboard(letter: String): String {
        keyboardText_.value = letter
        return letter
    }
    private var emptyText = ""
    private val keyboardText_: MutableLiveData<String> = MutableLiveData(emptyText)
    val keyboardText: LiveData<String>
            get() = keyboardText_
}