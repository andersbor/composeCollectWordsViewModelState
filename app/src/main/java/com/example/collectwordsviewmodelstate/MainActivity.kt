package com.example.collectwordsviewmodelstate

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.collectwordsviewmodelstate.ui.theme.CollectWordsViewModelStateTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            // add to gradle file
            // https://developer.android.com/develop/ui/compose/libraries#viewmodel
            CollectWordsViewModelStateTheme {
                MainScreen()
            }
        }
    }
}

@Composable
fun MainScreen(viewModel: WordsViewModelState = viewModel()) {
    Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
        CollectWords(
            modifier = Modifier.padding(innerPadding),
            words = viewModel.words,
            add = viewModel::add,
            remove = { viewModel.remove(it) },
            clear = viewModel::clear
        )
    }
}

// never pass down ViewModel instances to other composables
// https://developer.android.com/develop/ui/compose/migrate/other-considerations#viewmodel
@Composable
fun CollectWords(
    modifier: Modifier = Modifier,
    words: List<String>,
    add: (String) -> Unit,
    remove: (String) -> Unit,
    clear: () -> Unit
) {
    // Add to gradle file  implementation("androidx.lifecycle:lifecycle-viewmodel-compose:2.8.2")
    // https://tigeroakes.com/posts/mutablestateof-list-vs-mutablestatelistof/
    var newWord by remember { mutableStateOf("") }
    var result by remember { mutableStateOf("") }
    var showList by remember { mutableStateOf(true) }

    Column(modifier = modifier.padding(16.dp)) {
        Text(
            text = "Collect words", style = MaterialTheme.typography.headlineLarge
        )
        OutlinedTextField(value = newWord,
            onValueChange = { newWord = it },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
            modifier = Modifier.fillMaxWidth(),
            label = { Text("Enter a word") })
        Row(
            modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Button(onClick = { add(newWord); newWord = "" }) {
                Text("Add")
            }
            Button(onClick = {
                clear()
                newWord = ""
                result = ""
            }) {
                Text("Clear")
            }
            Button(onClick = { result = words.toString() }) {
                Text("Show")
            }
        }
        if (result.isNotEmpty()) {
            Text(result)
        } else {
            Text("Empty", fontStyle = FontStyle.Italic)
        }
        Row(
            verticalAlignment = Alignment.CenterVertically, modifier = Modifier.fillMaxWidth()
        ) {
            Text("Show list")
            Spacer(modifier = Modifier.padding(5.dp))
            Switch(checked = showList, onCheckedChange = { showList = it })
        }
        if (showList) {
            if (words.isEmpty()) {
                Text("No words")
            } else {
                LazyColumn(modifier = Modifier.fillMaxWidth()) {
                    items(words) { word: String ->
                        Text(word, modifier = Modifier
                            .clickable { remove(word) }
                            .padding(8.dp))
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CollectWordsPreview() {
    CollectWords(words = listOf("Hello", "World"), add = {}, remove = {}, clear = {})
}