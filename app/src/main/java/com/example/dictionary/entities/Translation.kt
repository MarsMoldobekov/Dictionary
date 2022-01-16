package com.example.dictionary.entities

import com.google.gson.annotations.SerializedName

data class Translation(
    @field:SerializedName("translation") val translation: String?
)