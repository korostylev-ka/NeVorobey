package ru.korostylev.nevorobey.ui

import ru.korostylev.nevorobey.dto.Answer

interface ViewInterface {
    fun checkWord(answer: Answer)
    fun clearInputFields()

}