package com.example.beaverclasshelpme.ui.pages

import android.graphics.drawable.Icon
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.beaverclasshelpme.ClassViewModel
import com.example.beaverclasshelpme.SharedPreferencesManager
import com.example.beaverclasshelpme.TokenViewModel
import com.example.beaverclasshelpme.data.ClassBody
import com.example.beaverclasshelpme.data.SaveDataBody
import com.google.gson.Gson

@Composable
fun SelectedPage(
    tokenViewModel: TokenViewModel,
    navController: NavController,
    preferencesManager: SharedPreferencesManager,
    onDeleteClick: () -> Unit,
    onLogoutClick: () -> Unit,
    onDraftClick: () -> Unit
) {
    val className = preferencesManager.getData("class_name")
    val classCode = preferencesManager.getData("class_code")
    val maxEnrollment = preferencesManager.getData("max_enrl")
    
    Column(modifier = Modifier
        .fillMaxSize()
        .padding(16.dp)) {
        Text("Selected Class", style = MaterialTheme.typography.headlineMedium)
        Divider(modifier = Modifier.padding(vertical = 4.dp))
        
        if (className != null && classCode != null && maxEnrollment != null) {
            Spacer(modifier = Modifier.height(16.dp))

            Column {
                Text("Class Title: ${className}", style = MaterialTheme.typography.bodyLarge)
                Text("Class Code: ${classCode}", style = MaterialTheme.typography.bodyLarge)
                Text("Class Maximum Enrollment: ${maxEnrollment}", style = MaterialTheme.typography.bodyLarge)
            }

            Spacer(modifier = Modifier.height(16.dp))
            Row {
                Button(onClick = {
                    navController.navigate(Screen.DraftEmail.route)
                                 }, modifier = Modifier.weight(1f)) {
                    Text("Draft")
                }
                Spacer(modifier = Modifier.width(8.dp))
                Button(
                    onClick = {
                        val data = ClassBody("htaywink@oregonstate.edu")
                        val jsonObject = Gson().toJsonTree(data).asJsonObject

                        tokenViewModel.deleteClassData(jsonObject)

                        preferencesManager.clearData()

                        navController.navigate(Screen.Home.route)
                    },
                    modifier = Modifier.weight(1f)
                ) {
                    Text("Remove")
                }
            }
        } else {
            Column {
                Text("Please select a class first!", style = MaterialTheme.typography.bodyLarge)
            }
        }

        Box(modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)) {
            Button(
                onClick = onLogoutClick, // Set the background color
                shape = CircleShape,
                modifier = Modifier
                    .align(Alignment.BottomEnd)
            ) {
                Icon(
                    imageVector = Icons.Default.Clear,
                    contentDescription = "Logout",
                    modifier = Modifier.size(ButtonDefaults.IconSize)
                )
                Spacer(Modifier.size(ButtonDefaults.IconSpacing))
                Text("Clear History")
            }
        }
    }
}