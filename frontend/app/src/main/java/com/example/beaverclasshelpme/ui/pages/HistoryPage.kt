package com.example.beaverclasshelpme.ui.pages

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material3.Card
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.beaverclasshelpme.SavedClassViewModel
import com.example.beaverclasshelpme.data.SavedClass

@Composable
fun HistoryPage(
    savedClassViewModel: SavedClassViewModel
) {
    val savedClasses by savedClassViewModel.savedClasses.observeAsState(initial = emptyList())

    Column(modifier = Modifier
        .fillMaxSize()
        .padding(16.dp)) {
        Text("History", style = MaterialTheme.typography.headlineMedium)
        Divider(modifier = Modifier.padding(vertical = 4.dp))

        LazyColumn {
            items(savedClasses) { myClass ->
                SavedClassItem(myClass = myClass)
            }
        }
    }
}

@Composable
fun SavedClassItem(myClass: SavedClass) {
    Card (modifier = Modifier.padding(8.dp).fillMaxWidth()) {
        Text(text = myClass.className, modifier = Modifier.padding(16.dp), style = TextStyle(fontWeight = FontWeight.Bold))
        Text(text = "Code: ${myClass.classCode}", modifier = Modifier.padding(16.dp))
        Text(text = "CRN: ${myClass.crn}", modifier = Modifier.padding(16.dp))
    }
}