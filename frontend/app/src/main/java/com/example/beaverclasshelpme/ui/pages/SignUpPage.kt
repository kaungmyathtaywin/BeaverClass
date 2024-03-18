package com.example.beaverclasshelpme.ui.pages

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp

@Composable
fun SignUpPage(onSignUpClick: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        Spacer(modifier = Modifier.height(50.dp))
        LogoWithAppName()
        Spacer(modifier = Modifier.height(50.dp))
        Text("Create Your Account", style = MaterialTheme.typography.labelLarge)
        Spacer(modifier = Modifier.height(16.dp))
        Text("Please login with Google", style = MaterialTheme.typography.labelLarge)
        Spacer(modifier = Modifier.height(50.dp))
        Button(onClick = onSignUpClick) {
            Text("Sign Up")
        }
    }
}

@Composable
fun Logo() {
    Text(
        "B",
        style = TextStyle(
            fontSize = MaterialTheme.typography.displayLarge.fontSize,
            color = Color(0xFFFF9800)
        )
    )
}

@Composable
fun LogoWithAppName() {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Logo()
        Text("BeaverClassHelpMe", style = MaterialTheme.typography.displaySmall)
    }
}