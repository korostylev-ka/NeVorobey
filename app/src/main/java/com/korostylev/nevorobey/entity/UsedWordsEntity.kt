package com.korostylev.nevorobey.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class UsedWordsEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val word: String = ""
) {
    companion object {
        const val ID = 0
    }
}