package com.example.collectwordsviewmodelstate

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

// https://medium.com/@husayn.fakher/compose-state-vs-stateflow-state-management-in-jetpack-compose-c99740732023
class WordsViewModelState : ViewModel() {
    var words = mutableStateOf(listOf<String>())
        private set // https://medium.com/@tangkegaga/private-set-set-is-private-f7ad495e201b

    fun add(item: String) {
        words.value = words.value + item
    }

    fun clear() {
        words.value = listOf()
    }

    fun remove(item: String) {
        words.value = words.value - item
    }
}
