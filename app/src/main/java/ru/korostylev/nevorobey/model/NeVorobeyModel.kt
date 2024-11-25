package ru.korostylev.nevorobey.model

import androidx.lifecycle.LiveData
import ru.korostylev.nevorobey.dto.Answer
import ru.korostylev.nevorobey.entity.ActiveGameEntity
import ru.korostylev.nevorobey.entity.UsedWordsEntity

interface NeVorobeyModel {

    fun getWord(): String
    fun getCurrentRow(): Int
    fun updateCurrentRow()
    fun checkWord(word: String): Answer
    fun saveCurrentGame(activeGameEntity: ActiveGameEntity)
    fun getCurrentGame(): ActiveGameEntity?
    fun deleteCurrentGame()
    fun getWordsFromDB(): LiveData<List<UsedWordsEntity>>
    fun saveWordToDB(usedWordsEntity: UsedWordsEntity)
    fun deleteWordsFromDB()
    fun finishGame()
    suspend fun getRandomWord(wordSize: Int): String
    suspend fun isWordExist(word: String): Boolean
}