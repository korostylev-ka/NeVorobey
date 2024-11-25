package com.korostylev.nevorobey.model

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.korostylev.nevorobey.api.NeVorobeyApi
import com.korostylev.nevorobey.dao.ActiveGameDao
import com.korostylev.nevorobey.dao.UsedWordsDao
import com.korostylev.nevorobey.dto.Answer
import com.korostylev.nevorobey.dto.Answer.Companion.LETTER_IS_EXIST
import com.korostylev.nevorobey.dto.Answer.Companion.LETTER_IS_NOT_EXIST
import com.korostylev.nevorobey.dto.Answer.Companion.LETTER_POSITION_GUESSED
import com.korostylev.nevorobey.dto.Letters
import com.korostylev.nevorobey.dto.Level
import com.korostylev.nevorobey.entity.ActiveGameEntity
import com.korostylev.nevorobey.entity.UsedWordsEntity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.withContext

class NeVorobeyModelImpl(val activeGameDao: ActiveGameDao, val usedWordsDao: UsedWordsDao, level: Level): NeVorobeyModel {


    private var wordSize = when (level) {
        Level.EASY -> EASY_WORD_LENGHT
        Level.MEDIUM -> MEDIUM_WORD_LENGHT
        Level.HARD -> HARD_WORD_LENGHT
    }
    private var answer = Answer(wordSize)
    private var currentRow: Int = 1
//    private var theWord = when (wordSize) {
//        4 -> "РУКА"
//        5 -> "СКАЛА"
//        6 -> "БОЛОТО"
//        else -> ""
//    }
//    private var theWord = activeGameDao.getCurrentGame()?.theWord ?: throw RuntimeException("There is no word in DB")
    private var theWord = activeGameDao.getCurrentGame()?.theWord ?: EMPTY_WORD



    override fun getWord() = theWord

    override fun getCurrentRow()  = currentRow

    override fun updateCurrentRow() {
        currentRow++
    }

    override fun checkWord(word: String): Answer {
        if (word.lowercase() == theWord.lowercase()) {
            answer.win()
        }
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
            val currentBackground = lettersBackground[index]?.second
            Log.d("vorobey", "color background is $currentBackground")

            if (letter == theWordLowCase[index]) {
                answer.updateLetters(index, Pair(theLetter, Answer.LETTER_POSITION_GUESSED))
                answer.addBackground(theLetter, LETTER_POSITION_GUESSED)
            }
            if (theWordLowCase.contains(letter) && letter != theWordLowCase[index]) {
                val count = theWordLowCase.count {
                    it == letter
                }
                val guessed = guessedLetters[letter]
                if (guessed != null) {
                    if (guessed < count) {
                        answer.updateLetters(index, Pair(theLetter, LETTER_IS_EXIST))
                        if (currentBackground == LETTER_IS_NOT_EXIST) {
                            Log.d("vorobey", "letter back $letter to $currentBackground")
                            answer.addBackground(theLetter, LETTER_IS_NOT_EXIST)
                            Log.d("vorobey", "letter back $letter to ${answer.getKeysBackground()}")
                        } else {
                            Log.d("vorobey", "letter back $letter to $currentBackground")
                            answer.addBackground(theLetter, LETTER_IS_EXIST)
                            Log.d("vorobey", "letter back $letter to ${answer.getKeysBackground()}")
                        }
//                        when (currentBackground) {
//                            null -> answer.addBackground(theLetter, LETTER_IS_EXIST)
//
//                        }
                    } else {
                        answer.updateLetters(index, Pair(theLetter, LETTER_IS_NOT_EXIST))
                        answer.addBackground(theLetter, LETTER_IS_NOT_EXIST)
                    }
                } else {
                    answer.updateLetters(index, Pair(theLetter, LETTER_IS_EXIST))
                    answer.addBackground(theLetter, LETTER_IS_EXIST)
                }
            }
            if (!theWordLowCase.contains(letter)) {
                answer.updateLetters(index, Pair(theLetter, LETTER_IS_NOT_EXIST))
                answer.addBackground(theLetter, LETTER_IS_NOT_EXIST)
            }

        }
        answer.increaseCurrentRow()
        return answer
    }

    override fun saveCurrentGame(activeGameEntity: ActiveGameEntity) {
        activeGameDao.insert(activeGameEntity)
    }

    override fun getCurrentGame(): ActiveGameEntity? {
        return activeGameDao.getCurrentGame()
    }

    override fun deleteCurrentGame() {
        TODO("Not yet implemented")
    }

    override fun getWordsFromDB(): LiveData<List<UsedWordsEntity>> {
        return usedWordsDao.getAllWord()
//        _wordsFromDB.value = usedWordsDao.getAllWord().value
    }

    override fun saveWordToDB(usedWordsEntity: UsedWordsEntity) {
        usedWordsDao.saveWordToDB(usedWordsEntity)
    }

    override fun deleteWordsFromDB() {
        usedWordsDao.deleteWords()
    }

    override fun finishGame() {
        usedWordsDao.deleteWords()
        activeGameDao.finishGame()
    }

    override suspend fun getRandomWord(wordSize: Int): String {
        val response = NeVorobeyApi.service.getRandomWord(wordSize)
        theWord = response.body().toString()
        Log.d("vorobey", "response is ${response.body()}")
        return theWord
    }

    companion object {
        const val EASY_WORD_LENGHT = 4
        const val MEDIUM_WORD_LENGHT = 5
        const val HARD_WORD_LENGHT = 6
        const val EMPTY_WORD = ""
    }



}