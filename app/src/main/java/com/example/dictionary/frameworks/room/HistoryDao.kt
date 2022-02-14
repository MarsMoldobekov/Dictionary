package com.example.dictionary.frameworks.room

import androidx.room.*
import com.example.dictionary.entities.HistoryEntity

@Dao
interface HistoryDao {
    @Query(value = "SELECT * FROM HistoryEntity")
    suspend fun all(): List<HistoryEntity>

    @Query(value = "SELECT * FROM HistoryEntity WHERE word LIKE :word")
    suspend fun getDataByWord(word: String): HistoryEntity

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(vararg entity: HistoryEntity)

    @Update
    suspend fun update(entity: HistoryEntity)

    @Delete
    suspend fun delete(entity: HistoryEntity)
}