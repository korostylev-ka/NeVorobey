package ru.korostylev.nevorobey.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import ru.korostylev.nevorobey.entity.ActiveGameEntity

@Dao
interface ActiveGameDao {

    @Query("SELECT * FROM ActiveGameEntity WHERE id = 0")
    fun getCurrentGame(): ActiveGameEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(activeGameEntity: ActiveGameEntity)

    @Query("DELETE FROM ActiveGameEntity")
    fun finishGame()
}