package com.example.beaverclasshelpme.ui.pages

import android.provider.Settings.Global.getString
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.beaverclasshelpme.ClassViewModel
import com.example.beaverclasshelpme.Course
import com.example.beaverclasshelpme.SharedPreferencesManager

@Composable
fun SearchResultPage(
    preferencesManager: SharedPreferencesManager,
    classViewModel: ClassViewModel,
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

            Button(onClick = onAddClick) {
                Text("Submit")

                preferencesManager.saveData("class_name", course!!.classTitle)
                preferencesManager.saveData("class_code", course!!.classCode)
                preferencesManager.saveData("max_enrl", course!!.maxEntrl.toString())
            }
        }
    }

}
