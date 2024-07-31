package com.korostylev.nevorobey.model

import android.util.Log
import com.korostylev.nevorobey.dto.Answer

class NeVorobeyModelImpl: NeVorobeyModel {
    private var wordSize = 5
    private var answer = Answer(wordSize)
    private var currentRow: Int = 1
    private val theWord = "ШЛАНГ"



    override fun getWord() = theWord

    override fun getCurrentRow()  = currentRow

    override fun updateCurrentRow() {
        currentRow++
    }

    override fun checkWord(word: String): Answer {
        var letterMap = mutableMapOf<Char, Int>()
        val wordLowCase = word.lowercase().toList()
        val theWordLowCase = theWord.lowercase().toList()
        if (wordLowCase.size != theWordLowCase.size) throw Exception("Wrong size of the word")
        for ((index,letter) in wordLowCase.withIndex()) {
            if (letter == theWordLowCase[index]) {
                answer.updateLetters(index, 1)
                //check that there is more such letters in the word
                val count = theWordLowCase.count {
                    it == letter
                }
                letterMap[letter] = count
            }
            if (theWordLowCase.contains(letter) && letter != theWordLowCase[index]) {
                val count = letterMap[letter]
                if (count == 1) {
                    answer.updateLetters(index, null)
                } else {
                    answer.updateLetters(index, 0)
                }


            }
            if (!theWordLowCase.contains(letter)) {
                answer.updateLetters(index, null)
            }
        }
        answer.increaseCurrentRow()
        Log.d("vorobey", letterMap.toString())
        return answer
    }

}