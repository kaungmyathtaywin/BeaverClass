package com.example.beaverclasshelpme.ui.pages

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.example.beaverclasshelpme.CartItem
import com.example.beaverclasshelpme.DraftEmailViewModel
import com.example.beaverclasshelpme.EmailDataRequest
@Composable
fun DraftEmailPage(emailViewModel: DraftEmailViewModel) {
    val context = LocalContext.current
    var emailContent by remember { mutableStateOf("") }
    val selectedItem = EmailDataRequest(
        name = "IronMan",
        email = "ironman@oregonstate.edu",
        professorName = "Rob Hess",
        className = "CS599",
        classId = "59371"
    )
    val emailText by emailViewModel.emailText.observeAsState("")
    emailContent = emailText


    Column(modifier = Modifier
        .fillMaxSize()
        .padding(16.dp)) {
        Text("Email Draft", style = MaterialTheme.typography.headlineSmall)
        Spacer(modifier = Modifier.height(8.dp))
        Button(
            onClick = {
                selectedItem?.let { item ->
                    val emailDataRequest = EmailDataRequest(
                        name = item.name,
                        email = item.email,
                        professorName = item.professorName,
                        className = item.className,
                        classId = item.classId
                    )
                    emailViewModel.fetchDraftEmail(emailDataRequest)
                }/* Fetch the email content from the API */ },
            modifier = Modifier.padding(8.dp)
        ) {
            Text("Generate Email")
        }
        OutlinedTextField(
            value = emailContent,
            onValueChange = { emailContent = it },
            modifier = Modifier
                .fillMaxHeight(0.8f)
                .padding(vertical = 8.dp),
            label = { Text("Email Content") }
        )
        Spacer(modifier = Modifier.height(8.dp))
        Button(
            onClick = {
                copyTextToClipboard(context, emailContent)
                Toast.makeText(context, "Email content copied to clipboard", Toast.LENGTH_SHORT).show()
            },
            modifier = Modifier.padding(8.dp)
        ) {
            Text("Copy to Clipboard")
        }

    }
}

fun copyTextToClipboard(context: Context, text: String) {
    val clipboard = context.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
    val clip = ClipData.newPlainText("email content", text)
    clipboard.setPrimaryClip(clip)
}
