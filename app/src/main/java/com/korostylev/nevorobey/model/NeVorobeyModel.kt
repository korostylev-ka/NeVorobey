package com.korostylev.nevorobey.model

import com.korostylev.nevorobey.dto.Answer

interface NeVorobeyModel {
    fun getWord(): String
    fun getCurrentRow(): Int
    fun updateCurrentRow()
    fun checkWord(word: String): Answer
}