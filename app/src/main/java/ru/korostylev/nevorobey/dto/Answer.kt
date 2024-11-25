package ru.korostylev.nevorobey.dto

import android.util.Log

data class Answer(
    var lettersAmount: Int
) {
    private val letters: MutableList<Pair<Letters, Int>?> = MutableList(lettersAmount) {null}
    private val keysBackgroundValues: MutableList<Pair<Letters, Int>?> = MutableList(33) {null}
    private var currentRow = 0
    private var max_rows = 6
    private var isWinner = false

    fun increaseCurrentRow() = currentRow++
    fun getCurrentRow() = currentRow
    fun updateLetters(index: Int, value: Pair<Letters, Int>) {
        letters[index] = value
    }
    fun getLetters() = letters

    fun getKeysBackground() = keysBackgroundValues

    fun addBackground(letters: Letters, backgroundValue: Int) {
        keysBackgroundValues.add(Pair(letters, backgroundValue))
    }

    fun isGameFinished(): Boolean {
        return currentRow == max_rows
    }

    fun isWinner() = isWinner
    fun win() {
        isWinner = true
    }

    companion object {

        const val LETTER_POSITION_GUESSED = 1
        const val LETTER_IS_EXIST = 0
        const val LETTER_IS_NOT_EXIST = -1
    }

}