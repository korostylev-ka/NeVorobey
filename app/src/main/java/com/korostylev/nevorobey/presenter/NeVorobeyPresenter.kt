package com.korostylev.nevorobey.presenter

import android.content.Context

interface NeVorobeyPresenter {
    fun checkWord(word: String)
    fun getCurrentRow(): Int
    fun updateCurrentRow()
    fun clearInputFields()
    fun getTheLetter(text: String)
}