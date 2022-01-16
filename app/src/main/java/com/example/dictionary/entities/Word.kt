package com.example.dictionary.entities

import com.google.gson.annotations.SerializedName

data class Word(
    @field:SerializedName("text") val text: String?,
    @field:SerializedName("meanings") val meanings: List<Meaning>?
)