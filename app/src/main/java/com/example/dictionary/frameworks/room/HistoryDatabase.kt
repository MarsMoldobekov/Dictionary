package com.example.dictionary.frameworks.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.dictionary.entities.HistoryEntity

@Database(entities = [HistoryEntity::class], version = 1, exportSchema = false)
abstract class HistoryDatabase : RoomDatabase() {
    abstract fun historyDao(): HistoryDao
}