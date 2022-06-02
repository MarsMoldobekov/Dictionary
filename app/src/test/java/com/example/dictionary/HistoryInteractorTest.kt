package com.example.dictionary

import com.example.dictionary.entities.Word
import com.example.dictionary.interactors.HistoryInteractor
import com.example.dictionary.interfaceadapters.repositories.IRepository
import com.example.dictionary.interfaceadapters.repositories.IRepositoryLocal
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.mockito.ArgumentMatchers
import org.mockito.Mockito
import org.mockito.kotlin.mock

class HistoryInteractorTest {
    private lateinit var historyInteractor: HistoryInteractor

    private val repositoryLocal = mock<IRepositoryLocal<List<Word>>>()

    private val repositoryRemote = mock<IRepository<List<Word>>>()

    @Before
    fun setUp() {
        historyInteractor = HistoryInteractor(repositoryRemote, repositoryLocal)
    }

    @Test
    fun `run getData() like isOnline is false`(): Unit = runBlocking {
        historyInteractor.getData(ArgumentMatchers.anyString(), isOnline = false)
        Mockito.verify(repositoryLocal, Mockito.times(1)).getData(ArgumentMatchers.anyString())
    }

    @Test
    fun `run getData() like isOnline is true`(): Unit = runBlocking {
        historyInteractor.getData(ArgumentMatchers.anyString(), isOnline = true)
        Mockito.verify(repositoryRemote, Mockito.times(1)).getData(ArgumentMatchers.anyString())
    }
}