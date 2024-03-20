package com.example.beaverclasshelpme.ui.pages

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.beaverclasshelpme.ClassViewModel
import com.example.beaverclasshelpme.SavedClassViewModel
import com.example.beaverclasshelpme.SharedPreferencesManager
import com.example.beaverclasshelpme.TokenViewModel
import com.example.beaverclasshelpme.data.SaveDataBody
import com.example.beaverclasshelpme.data.SavedClass
import com.google.gson.Gson

@Composable
fun SearchResultPage(
    preferencesManager: SharedPreferencesManager,
    classViewModel: ClassViewModel,
    savedClassViewModel: SavedClassViewModel,
    tokenViewModel: TokenViewModel,
    onAddClick: () -> Unit
) {
    val course by classViewModel.courseData.observeAsState()
    course?.let {
        Column(modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)) {
            Text("Search Results", style = MaterialTheme.typography.headlineSmall)
            Divider(modifier = Modifier.padding(vertical = 4.dp))
            Spacer(modifier = Modifier.height(8.dp))
            Text("Class Code: ${course!!.classCode}", style = MaterialTheme.typography.bodyLarge)
            Spacer(modifier = Modifier.height(8.dp))
            Text("Class Title: ${course!!.classTitle}", style = MaterialTheme.typography.bodyLarge)
            Spacer(modifier = Modifier.height(8.dp))
            Text("CRN: ${course!!.crn}", style = MaterialTheme.typography.bodyLarge)
            Spacer(modifier = Modifier.height(8.dp))
            Text("Meets: ${course!!.meets}", style = MaterialTheme.typography.bodyLarge)
            Spacer(modifier = Modifier.height(8.dp))
            Text("Campus: ${course!!.campus}", style = MaterialTheme.typography.bodyLarge)
            Spacer(modifier = Modifier.height(8.dp))
            Text("Max Enrollment: ${course!!.maxEntrl}", style = MaterialTheme.typography.bodyLarge)
            Spacer(modifier = Modifier.height(8.dp))
            Text("Actual Enrollment: ${course!!.actualEnrl}", style = MaterialTheme.typography.bodyLarge)
            Spacer(modifier = Modifier.height(8.dp))

            Box(
                modifier = Modifier.fillMaxWidth()
            ) {
                Button(
                    onClick = onAddClick,
                    modifier = Modifier.align(Alignment.Center)
                ) {
                    Text("Submit")

                    val crn = course!!.crn
                    val className = course!!.classTitle
                    val classCode = course!!.classCode

                    preferencesManager.saveData("class_name", course!!.classTitle)
                    preferencesManager.saveData("class_code", course!!.classCode)
                    preferencesManager.saveData("max_enrl", course!!.maxEntrl.toString())
                    preferencesManager.saveData("crn", course!!.crn)

                    val currentClass = SavedClass(crn, className, classCode)
                    savedClassViewModel.addNewClass(currentClass)

                    val data = SaveDataBody("htaywink@oregonstate.edu", crn, classCode)
                    val jsonObject = Gson().toJsonTree(data).asJsonObject
                    tokenViewModel.submitClassData(jsonObject)
                }
            }
        }
    }
}