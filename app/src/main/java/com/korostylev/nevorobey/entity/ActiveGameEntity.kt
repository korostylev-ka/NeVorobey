package com.korostylev.nevorobey.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class ActiveGameEntity(
    @PrimaryKey(autoGenerate = false)
    val id: Int = 0,
    var isActiveGame: Boolean = false,
    var currentGameLevel: Int = 0
) {
    companion object {
        const val EASY = 1
        const val MEDIUM = 2
        const val HARD = 3
    }
}