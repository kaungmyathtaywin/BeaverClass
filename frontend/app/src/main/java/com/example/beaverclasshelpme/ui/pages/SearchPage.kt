package com.example.beaverclasshelpme.ui.pages

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.beaverclasshelpme.data.BackendService
import com.example.beaverclasshelpme.ClassRepository
import com.example.beaverclasshelpme.ClassViewModel
import kotlinx.coroutines.Dispatchers

@Composable
fun SearchPage(navController: NavController) {
    var term by remember { mutableStateOf("") }
    var classTitle by remember { mutableStateOf("") }
    var classCode by remember { mutableStateOf("") }
    var crn by remember { mutableStateOf("") }
    val backendService = BackendService.create()

    val ioDispatcher = Dispatchers.IO
    val classRepository = ClassRepository(backendService, ioDispatcher)

    val classViewModel: ClassViewModel = viewModel(factory = object : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            @Suppress("UNCHECKED_CAST")
            return ClassViewModel(classRepository) as T
        }
    })
    Column(modifier = Modifier
        .fillMaxSize()
        .padding(16.dp)) {
        Text(text = "BeaverClassHelpMe", style = MaterialTheme.typography.displaySmall)
        Spacer(modifier = Modifier.height(16.dp))
        TextField(
            value = term,
            onValueChange = { term = it },
            label = { Text("Term") }
        )
//        Spacer(modifier = Modifier.height(8.dp))
//        TextField(
//            value = classTitle,
//            onValueChange = { classTitle = it },
//            label = { Text("Class Title") }
//        )
        Spacer(modifier = Modifier.height(8.dp))
        TextField(
            value = classCode,
            onValueChange = { classCode = it },
            label = { Text("Class Code") }
        )
        Spacer(modifier = Modifier.height(8.dp))
        TextField(
            value = crn,
            onValueChange = { crn = it },
            label = { Text("CRN") }
        )
        Spacer(modifier = Modifier.height(16.dp))
        Button(
            onClick = {
                // operate the search logic here
                // use the value that user pass in the input field to search the class
                classViewModel.loadClassData(classCode, crn, term)

                navController.navigate("search_result/$classCode/$crn/$term")
            },
            modifier = Modifier.align(Alignment.CenterHorizontally),
            enabled = term.isNotEmpty() && classCode.isNotEmpty() && crn.isNotEmpty()
        ) {
            Text("Search")
        }

    }


}