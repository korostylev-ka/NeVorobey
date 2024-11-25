package ru.korostylev.nevorobey.entity

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity
class ActiveGameEntity(
    @PrimaryKey(autoGenerate = false)
    val id: Int = 0,
    var isActiveGame: Boolean = false,
    var currentGameLevel: Int = 0,
    var theWord: String = EMPTY_WORD
) {
    companion object {
        const val EASY = 1
        const val MEDIUM = 2
        const val HARD = 3
        private const val EMPTY_WORD = ""
    }
}