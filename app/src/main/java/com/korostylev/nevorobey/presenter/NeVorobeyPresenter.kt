package com.korostylev.nevorobey.presenter

import android.content.Context
import androidx.lifecycle.LiveData
import com.korostylev.nevorobey.entity.ActiveGameEntity
import com.korostylev.nevorobey.entity.UsedWordsEntity

interface NeVorobeyPresenter {
    val wordsFromDBLiveData: LiveData<List<UsedWordsEntity>>
    fun checkWord(word: String)
    fun getCurrentRow(): Int
    fun updateCurrentRow()
    fun clearInputFields()
    fun getCurrentGame(): ActiveGameEntity?
    fun getWordsFromDB(): LiveData<List<UsedWordsEntity>>
    fun saveWordToDB(usedWordsEntity: UsedWordsEntity)
    fun deleteWordsFromDB()
}