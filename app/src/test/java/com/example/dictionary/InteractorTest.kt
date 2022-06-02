package com.example.dictionary

import com.example.dictionary.entities.AppState
import com.example.dictionary.entities.Word
import com.example.dictionary.interactors.Interactor
import com.example.dictionary.interfaceadapters.repositories.IRepository
import com.example.dictionary.interfaceadapters.repositories.IRepositoryLocal
import kotlinx.coroutines.runBlocking

import org.junit.Before
import org.junit.Test
import org.mockito.ArgumentMatchers.anyString
import org.mockito.Mockito
import org.mockito.Mockito.`when`
import org.mockito.Mockito.times
import org.mockito.kotlin.mock

class InteractorTest {
    private lateinit var interactor: Interactor

    private val repositoryLocal = mock<IRepositoryLocal<List<Word>>>()

    private val repositoryRemote = mock<IRepository<List<Word>>>()

    @Before
    fun setUp() {
        interactor = Interactor(repositoryRemote, repositoryLocal)
    }

    @Test
    fun `run getData() like isOnline is false`(): Unit = runBlocking {
        interactor.getData(anyString(), isOnline = false)
        Mockito.verify(repositoryLocal, times(1)).getData(anyString())
    }

    @Test
    fun `run getData() like isOnline is true`(): Unit = runBlocking {
        interactor.getData(anyString(), isOnline = true)
        Mockito.verify(repositoryRemote, times(1)).getData(anyString())
    }

    @Test
    fun `check if local repository saves appState`(): Unit = runBlocking {
        `when`(repositoryRemote.getData(anyString())).thenReturn(listOf())
        interactor.getData(anyString(), isOnline = true)
        Mockito.verify(repositoryLocal, times(1))
            .saveToDatabase(AppState.Success(listOf()))
    }
}