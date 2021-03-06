package com.example.dictionary.entities

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Translation(
    @field:SerializedName("text") val translation: String?
) : Parcelable