package com.korostylev.nevorobey.model

import com.korostylev.nevorobey.dto.Answer
import com.korostylev.nevorobey.entity.ActiveGameEntity

interface NeVorobeyModel {
    fun getWord(): String
    fun getCurrentRow(): Int
    fun updateCurrentRow()
    fun checkWord(word: String): Answer
    fun saveCurrentGame(activeGameEntity: ActiveGameEntity)
    fun deleteCurrentGame()
}