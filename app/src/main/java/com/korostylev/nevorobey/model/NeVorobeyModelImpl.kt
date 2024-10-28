package com.korostylev.nevorobey.model

import android.util.Log
import com.korostylev.nevorobey.dto.Answer
import com.korostylev.nevorobey.dto.Answer.Companion.LETTER_IS_EXIST
import com.korostylev.nevorobey.dto.Answer.Companion.LETTER_IS_NOT_EXIST
import com.korostylev.nevorobey.dto.Letters

class NeVorobeyModelImpl: NeVorobeyModel {
    private var wordSize = 5
    private var answer = Answer(wordSize)
    private var currentRow: Int = 1
    private var theWord = "ШПАЛА"

    override fun getWord() = theWord

    override fun getCurrentRow()  = currentRow

    override fun updateCurrentRow() {
        currentRow++
    }

    override fun checkWord(word: String): Answer {
        var guessedLetters = mutableMapOf<Char, Int>()
        val lettersBackground = answer.getKeysBackground()
        val wordLowCase = word.lowercase().toList()
        val theWordLowCase = theWord.lowercase().toList()
        if (wordLowCase.size != theWordLowCase.size) throw Exception("Wrong size of the word")
        for ((index,letter) in wordLowCase.withIndex()) {
            if (letter == theWordLowCase[index]) {
                val value = guessedLetters[letter]
                if (value == null) {
                    guessedLetters[letter] = 1
                } else {
                    guessedLetters[letter] = guessedLetters[letter]!! + 1
                }
            }
        }
        for ((index,letter) in wordLowCase.withIndex()) {
            val theLetter = Letters.entries.filter {
                it.letter == letter
            }[0]
//            val currentBackground = lettersBackground[index]

            if (letter == theWordLowCase[index]) {
                answer.updateLetters(index, Pair(theLetter, Answer.LETTER_POSITION_GUESSED))
            }
            if (theWordLowCase.contains(letter) && letter != theWordLowCase[index]) {
                val count = theWordLowCase.count {
                    it == letter
                }
                val guessed = guessedLetters[letter]
                if (guessed != null) {
                    if (guessed < count) {
                        answer.updateLetters(index, Pair(theLetter, LETTER_IS_EXIST))
//                        when (currentBackground) {
//                            null -> answer.addBackground(theLetter, LETTER_IS_EXIST)
//
//                        }
                    } else {
                        answer.updateLetters(index, Pair(theLetter, LETTER_IS_NOT_EXIST))
//                        answer.addBackground(theLetter, LETTER_IS_NOT_EXIST)
                    }
                } else {
                    answer.updateLetters(index, Pair(theLetter, LETTER_IS_EXIST))
                }
            }
            if (!theWordLowCase.contains(letter)) {
                answer.updateLetters(index, Pair(theLetter, LETTER_IS_NOT_EXIST))
                answer.addBackground(theLetter, Answer.LETTER_IS_NOT_EXIST)
            }

        }
        answer.increaseCurrentRow()
        return answer
    }

}