package com.korostylev.nevorobey.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.korostylev.nevorobey.dao.ActiveGameDao
import com.korostylev.nevorobey.dao.UsedWordsDao
import com.korostylev.nevorobey.entity.ActiveGameEntity
import com.korostylev.nevorobey.entity.UsedWordsEntity

@Database(entities = [ActiveGameEntity::class, UsedWordsEntity::class], version = 1)
abstract class NeVorobeyDB: RoomDatabase() {
    abstract val activeGameDao: ActiveGameDao
    abstract val usedWordsDao: UsedWordsDao

    companion object {
        private var instance: NeVorobeyDB? = null

        fun getInstance(context: Context): NeVorobeyDB {
            return  instance ?: buildDatabase(context)
        }

        private fun buildDatabase(context: Context) = Room.databaseBuilder(
            context,
            NeVorobeyDB::class.java,
            "nevorobey.db"
        )
            .allowMainThreadQueries()
            .build()
    }
}