package com.korostylev.nevorobey.dto

import android.util.Log

data class Answer(
    var lettersAmount: Int
) {
    private val letters: MutableList<Int?> = MutableList(lettersAmount) {null}
    private var currentRow = 0
    fun clearLetters() {
        letters.clear()
    }
    fun increaseCurrentRow() = currentRow++
    fun getCurrentRow() = currentRow
    fun updateLetters(index: Int, value: Int?) {
        letters[index] = value
    }
    fun getLetters() = letters
    init {
        Log.d("vorobey", letters.toString())
    }
}