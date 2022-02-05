package com.example.dictionary.frameworks.web

import com.example.dictionary.entities.Word
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("words/search")
    suspend fun searchAsync(@Query("search") wordToSearch: String): List<Word>
}