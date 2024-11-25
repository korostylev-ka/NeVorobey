package ru.korostylev.nevorobey.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import ru.korostylev.nevorobey.entity.UsedWordsEntity

@Dao
interface UsedWordsDao {

    @Query("SELECT * FROM UsedWordsEntity")
    fun getAllWord(): LiveData<List<UsedWordsEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveWordToDB(usedWordsEntity: UsedWordsEntity)

    @Query("DELETE FROM UsedWordsEntity")
    fun deleteWords()
}