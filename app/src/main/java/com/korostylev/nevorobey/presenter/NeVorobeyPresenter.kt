package com.korostylev.nevorobey.presenter

interface NeVorobeyPresenter {
    fun checkWord(word: String)
    fun getCurrentRow(): Int
    fun updateCurrentRow()
    fun clearInputFields()
    fun getTheLetter(text: String)
}