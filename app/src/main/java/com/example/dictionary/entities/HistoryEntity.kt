package com.example.dictionary.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(indices = [Index(value = ["id"], unique = true)])
data class HistoryEntity(
    @field:PrimaryKey
    @field:ColumnInfo(name = "id")
    val id: String?,

    @field:ColumnInfo(name = "word")
    val word: String?,

    @field:ColumnInfo(name = "description")
    val description: String?
)