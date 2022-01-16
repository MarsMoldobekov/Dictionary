package com.example.dictionary.entities

import com.google.gson.annotations.SerializedName

data class Meaning(
    @field:SerializedName("translation") val translation: Translation?,
    @field:SerializedName("imageUrl") val imageUrl: String?
)