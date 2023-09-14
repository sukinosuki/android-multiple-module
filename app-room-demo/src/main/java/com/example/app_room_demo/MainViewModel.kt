package com.example.app_room_demo

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class MainViewModel(private val wordRepository: WordRepository) : ViewModel() {

    val allWords = wordRepository.getAllWords()

    fun insert(word: Word) = viewModelScope.launch {
        wordRepository.insert(word)
    }
}