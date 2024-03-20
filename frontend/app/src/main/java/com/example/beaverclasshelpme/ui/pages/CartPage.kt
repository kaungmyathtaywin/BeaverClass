package com.example.beaverclasshelpme.ui.pages

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.beaverclasshelpme.CartItem
import com.example.beaverclasshelpme.CartItemRow
import com.example.beaverclasshelpme.Course

@Composable
fun CartPage(selectedClass: Course, onDeleteClick: () -> Unit, onLogoutClick: () -> Unit, onDraftClick: () -> Unit) {
    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        Text("Selected Class:", style = MaterialTheme.typography.displaySmall)
        Spacer(modifier = Modifier.height(16.dp))

        CourseItemRow(course = selectedClass)

        Spacer(modifier = Modifier.height(16.dp))
        Row {
            Button(onClick = onDraftClick, modifier = Modifier.weight(1f)) {
                Text("Draft")
            }
            Spacer(modifier = Modifier.width(8.dp))
            Button(onClick = onDeleteClick, modifier = Modifier.weight(1f)) {
                Text("Remove")
            }
        }
        Spacer(modifier = Modifier.height(32.dp))
        Button(onClick = onLogoutClick, modifier = Modifier.align(Alignment.CenterHorizontally)) {
            Text("Logout")
        }
    }
}

@Composable
fun CourseItemRow(course: Course) {
    Column {
        Text("Class Code: ${course.classCode}", style = MaterialTheme.typography.bodyLarge)
        Text("Class Title: ${course.classTitle}", style = MaterialTheme.typography.bodyLarge)
        Text("Class CRN: ${course.crn}", style = MaterialTheme.typography.bodyLarge)
        Text("Class maxEntrl: ${course.maxEntrl}", style = MaterialTheme.typography.bodyLarge)
        Text("Class actualEnrl: ${course.actualEnrl}", style = MaterialTheme.typography.bodyLarge)
        Text("Class Meets: ${course.meets}", style = MaterialTheme.typography.bodyLarge)
        Text("Class Campus: ${course.campus}", style = MaterialTheme.typography.bodyLarge)
    }
}