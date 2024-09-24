package com.example.collectwordsviewmodelstate

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

// https://medium.com/@husayn.fakher/compose-state-vs-stateflow-state-management-in-jetpack-compose-c99740732023
class WordsViewModelState : ViewModel() {
    var words by mutableStateOf(listOf<String>())
        private set // https://medium.com/@tangkegaga/private-set-set-is-private-f7ad495e201b

    fun add(word: String) {
        words= words + word
    }

    fun clear() {
        words = listOf()
    }

    fun remove(item: String) {
        words = words - item
    }
}
