package com.korostylev.nevorobey.ui

import com.korostylev.nevorobey.dto.Answer

interface ViewInterface {
    fun checkWord(answer: Answer)
    fun clearInputFields()

}