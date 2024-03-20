package com.example.beaverclasshelpme.ui.pages

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun SettingsPage() {
    Column(modifier = Modifier
        .fillMaxSize()
        .padding(16.dp)) {
        Text("Account & Preference Settings", style = MaterialTheme.typography.displaySmall, modifier = Modifier.padding(vertical = 8.dp))

        SettingSection(title = "Before Registration")
        SettingOption("Set alarm before the registration", listOf("5min", "10min", "30min", "1hr"))

        SettingSection(title = "After Registration")
        SettingToggleOption("Set alarm if there are space for class", listOf("Yes", "No"))
        SettingOption("The frequency for alarm", listOf("10min", "30min", "1hr", "1Day", ))

        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = { /* TODO: Save action */ }, modifier = Modifier.fillMaxWidth()) {
            Text("Save")
        }
        Spacer(modifier = Modifier.height(8.dp))
        Button(onClick = { /* TODO: Logout action */ }, modifier = Modifier.fillMaxWidth()) {
            Text("Logout")
        }
    }
}

@Composable
fun SettingSection(title: String) {
    Text(title, style = MaterialTheme.typography.labelLarge, modifier = Modifier.padding(vertical = 16.dp))
}

@Composable
fun SettingOption(title: String, options: List<String>) {
    Text(title, style = MaterialTheme.typography.labelLarge)
    Row(modifier = Modifier.padding(vertical = 8.dp)) {
        options.forEach { option ->
            Button(onClick = { /* TODO: Select option action */ }, modifier = Modifier.padding(end = 8.dp)) {
                Text(option)
            }
        }
    }
}

@Composable
fun SettingToggleOption(title: String, options: List<String>) {
    Text(title, style = MaterialTheme.typography.bodyLarge)
    Row(modifier = Modifier.padding(vertical = 8.dp)) {
        options.forEach { option ->
            Button(onClick = { /* TODO: Toggle option action */ }, modifier = Modifier.padding(end = 8.dp)) {
                Text(option)
            }
        }
    }
}