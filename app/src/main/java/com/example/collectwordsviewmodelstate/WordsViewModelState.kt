package com.example.collectwordsviewmodelstate

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class WordsViewModelState : ViewModel() {
    private val _words = MutableStateFlow(listOf<String>())
    val words: StateFlow<List<String>> = _words

    fun add(item: String) {
        _words.value = _words.value + item
    }

    fun clear() {
        _words.value = listOf()
    }

    fun remove(item: String) {
        _words.value = _words.value - item
    }
}
