package com.korostylev.nevorobey.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.korostylev.nevorobey.dao.ActiveGameDao
import com.korostylev.nevorobey.entity.ActiveGameEntity

@Database(entities = [ActiveGameEntity::class], version = 1)
abstract class NeVorobeyDB: RoomDatabase() {
    abstract val activeGameDao: ActiveGameDao

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