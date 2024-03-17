package com.example.beaverclasshelpme

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.BlendMode.Companion.Color
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.beaverclasshelpme.ui.theme.BeaverClassHelpMeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BeaverClassHelpMeTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    //Greeting("Android")
                    SearchPage(navController = null)
                    //SearchResultPage(courseList)
//                    AddToCartPage(courseDetails) { detail ->
//                        // TODO: Handle the add to cart action
//                    }
//                    SignInPage(
//                        onSignInClick = {
//                            // deal with the sign in logic here
//                        },
//                        onSignUpClick = {
//                            // direct to the signup page
//                        }
//                    )
//                    SignUpPage (
//                        onSignUpClick =
//                        {
//                        // deal with the sign up logic here
//                        }
//                    )
//                    CartPage(cartItems,
//                        onDeleteClick = { item ->
//                            // deal with the delete item logic here
//                        },
//
//                        onSubmitClick = {
//                            // deal with the submit logic here
//                        },
//                        onDraftClick = { item ->
//                            // deal with the draft logic here
//                        }
//                    )
                    //DraftEmailPage(cartItems[0])
                    //SettingsPage()
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    BeaverClassHelpMeTheme {
        Greeting("Android")
    }
}

@Composable
fun SignInPage(onSignInClick: () -> Unit, onSignUpClick: () -> Unit) {
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
        Text("Sign in", style = MaterialTheme.typography.labelLarge)
        Spacer(modifier = Modifier.height(16.dp))
        Text("Please login with Google", style = MaterialTheme.typography.labelLarge)
        Spacer(modifier = Modifier.height(50.dp))
        Button(onClick = onSignInClick) {
            Text("Sign In")
        }
        Spacer(modifier = Modifier.height(8.dp))
        Button(onClick = onSignUpClick) {
            Text("Sign Up")
        }
    }
}

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

@Composable
fun SearchPage(navController: NavController?) {
    var term by remember { mutableStateOf("") }
    var classTitle by remember { mutableStateOf("") }
    var classCode by remember { mutableStateOf("") }
    var crn by remember { mutableStateOf("") }

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
        Spacer(modifier = Modifier.height(8.dp))
        TextField(
            value = classTitle,
            onValueChange = { classTitle = it },
            label = { Text("Class Title") }
        )
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
            },
            modifier = Modifier.align(Alignment.CenterHorizontally),
            enabled = classTitle.isNotEmpty()
        ) {
            Text("Search")
        }
    }

    Row(
        modifier = Modifier.padding(16.dp),
        verticalAlignment = Alignment.Bottom
    ) {
        IconButton(onClick = { /* navigate to home page */ }) {
            Icon(Icons.Default.Home, contentDescription = "Home")
        }
        Spacer(modifier = Modifier.weight(1f))
        IconButton(onClick = { /* navigate to cart */ }) {
            Icon(Icons.Default.ShoppingCart, contentDescription = "Cart")
        }
        Spacer(modifier = Modifier.weight(1f))
        IconButton(onClick = { /* navigate to settings */ }) {
            Icon(Icons.Default.Settings, contentDescription = "Settings")
        }
    }
}


val courseList = listOf(
    Course("CS 561", "Software Engineering I"),
    Course("CS 562", "Software Engineering II"),
    Course("CS 563", "Software Engineering III"),
)

@Composable
fun SearchResultPage(courses: List<Course>) {
    Column(modifier = Modifier.fillMaxSize()) {
        Text("Search Result", style = MaterialTheme.typography.displaySmall, modifier = Modifier.padding(16.dp))
        LazyColumn {
            items(courses) { course ->
                CourseItem(course, onClick = {
                    // the operations when you click the item in the list
                })
            }
        }
    }
    Row(
        modifier = Modifier.padding(16.dp),
        verticalAlignment = Alignment.Bottom
    ) {
        IconButton(onClick = { /* navigate to home page */ }) {
            Icon(Icons.Default.Home, contentDescription = "Home")
        }
        Spacer(modifier = Modifier.weight(1f))
        IconButton(onClick = { /* navigate to cart */ }) {
            Icon(Icons.Default.ShoppingCart, contentDescription = "Cart")
        }
        Spacer(modifier = Modifier.weight(1f))
        IconButton(onClick = { /* navigate to settings */ }) {
            Icon(Icons.Default.Settings, contentDescription = "Settings")
        }
    }
}

@Composable
fun CourseItem(course: Course, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .padding(horizontal = 8.dp, vertical = 4.dp)
            .fillMaxWidth()
            .clickable(onClick = onClick),

    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(course.id, style = MaterialTheme.typography.bodyMedium)
            Text(course.title, style = MaterialTheme.typography.bodyMedium)
            Divider()
        }
    }
}

val courseDetails = listOf(
    CourseDetail("10293", "Corvallis", "TR 4 - 5:50pm", 135, 88),
    CourseDetail("10294", "Cascade", "MW 10 - 11:50pm", 30, 17),
    CourseDetail("10295", "Ecampus", "", 49, 49),
)

@Composable
fun AddToCartPage(courseDetails: List<CourseDetail>, onAddClick: (CourseDetail) -> Unit) {
    Column(modifier = Modifier.fillMaxSize()) {
        Text("Add class to cart", style = MaterialTheme.typography.displaySmall, modifier = Modifier.padding(16.dp))

        Row(modifier = Modifier.padding(8.dp)) {
            Text("CRN", modifier = Modifier.weight(1f))
            Text("Campus", modifier = Modifier.weight(1f))
            Text("Meets", modifier = Modifier.weight(1f))
            Text("Max Enrl", modifier = Modifier.weight(1f))
            Text("Actual Enrl", modifier = Modifier.weight(1f))
            Spacer(modifier = Modifier.width(68.dp))
        }

        LazyColumn {
            items(courseDetails) { detail ->
                CourseDetailItem(detail, onAddClick)
            }
        }
    }

    Row(
        modifier = Modifier.padding(16.dp),
        verticalAlignment = Alignment.Bottom
    ) {
        IconButton(onClick = { /* navigate to home page */ }) {
            Icon(Icons.Default.Home, contentDescription = "Home")
        }
        Spacer(modifier = Modifier.weight(1f))
        IconButton(onClick = { /* navigate to cart */ }) {
            Icon(Icons.Default.ShoppingCart, contentDescription = "Cart")
        }
        Spacer(modifier = Modifier.weight(1f))
        IconButton(onClick = { /* navigate to settings */ }) {
            Icon(Icons.Default.Settings, contentDescription = "Settings")
        }
    }
}

@Composable
fun CourseDetailItem(courseDetail: CourseDetail, onAddClick: (CourseDetail) -> Unit) {
    Row(modifier = Modifier
        .fillMaxWidth()
        .padding(8.dp)) {
        Text(courseDetail.crn, modifier = Modifier.weight(1f))
        Text(courseDetail.campus, modifier = Modifier.weight(1f))
        Text(courseDetail.meets, modifier = Modifier.weight(1f))
        Text("${courseDetail.maxEnrl}", modifier = Modifier.weight(1f))
        Text("${courseDetail.actualEnrl}", modifier = Modifier.weight(1f))
        Button(onClick = { onAddClick(courseDetail) }) {
            Text("Add")
        }
    }
}

val cartItems = listOf(
    CartItem("10976", "CS 561", "Software Engineering I", "TR 4 - 5:50pm"),
    CartItem("10977", "CS 562", "Software Engineering II", "MW 10 - 11:50am"),
    CartItem("10978", "CS 563", "Software Engineering III", "WF 3 - 4:50pm"),
)

@Composable
fun CartPage(cartItems: List<CartItem>, onDeleteClick: (CartItem) -> Unit, onSubmitClick: () -> Unit, onDraftClick: (CartItem) -> Unit) {
    Column(modifier = Modifier.fillMaxSize()) {
        Text("Cart", style = MaterialTheme.typography.displaySmall, modifier = Modifier.padding(16.dp))
        LazyColumn {
            items(cartItems) { item ->
                CartItemRow(item, onDeleteClick, onDraftClick)
            }
        }
        Button(
            onClick = onSubmitClick,
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(16.dp)
        ) {
            Text("Submit")
        }
    }
    Row(
        modifier = Modifier.padding(16.dp),
        verticalAlignment = Alignment.Bottom
    ) {
        IconButton(onClick = { /* navigate to home page */ }) {
            Icon(Icons.Default.Home, contentDescription = "Home")
        }
        Spacer(modifier = Modifier.weight(1f))
        IconButton(onClick = { /* navigate to cart */ }) {
            Icon(Icons.Default.ShoppingCart, contentDescription = "Cart")
        }
        Spacer(modifier = Modifier.weight(1f))
        IconButton(onClick = { /* navigate to settings */ }) {
            Icon(Icons.Default.Settings, contentDescription = "Settings")
        }
    }
}

@Composable
fun CartItemRow(item: CartItem, onDraftClick: (CartItem) -> Unit, onDeleteClick: (CartItem) -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(text = "CRN ${item.crn}", modifier = Modifier.weight(1f))
            Text(text = item.title, modifier = Modifier.weight(1f))
            Spacer(modifier = Modifier.width(8.dp))
            Button(
                onClick = { onDraftClick(item) },
                modifier = Modifier.weight(1f)
            ) {
                Text("Draft")
            }
            Spacer(modifier = Modifier.width(8.dp))
            Button(
                onClick = { onDeleteClick(item) },
                modifier = Modifier.weight(1f)
            ) {
                Text("Delete")
            }
        }
        Text(text = "Meets: ${item.meets}", modifier = Modifier.padding(vertical = 4.dp))
        Divider()
    }
}


@Composable
fun DraftEmailPage(cartItem: CartItem) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Divider(modifier = Modifier.padding(vertical = 8.dp))
        Text("Draft the email template and email to professor",
            style = MaterialTheme.typography.displaySmall)
        Spacer(modifier = Modifier.height(16.dp))
        Text(cartItem.id, style = MaterialTheme.typography.bodyLarge)
        Text(cartItem.title, style = MaterialTheme.typography.bodyLarge)
        Text("CRN ${cartItem.crn} Meets: ${cartItem.meets}", style = MaterialTheme.typography.bodyMedium)
        Spacer(modifier = Modifier.height(16.dp))
        val emailText = remember { mutableStateOf("Hello Professor Ma,\n\nI am Josh, is there a chance that I can still enroll for this class?\n\nBest Regards,\nJosh") }
        TextField(
            value = emailText.value,
            onValueChange = { emailText.value = it },
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
        )
        Spacer(modifier = Modifier.height(16.dp))
        Button(
            onClick = {
                // TODO: implement the function to copy to clipboard
            },
            modifier = Modifier.align(Alignment.CenterHorizontally)
        ) {
            Text("Copy to clipboard")
        }

    }
    Row(
        modifier = Modifier.padding(16.dp),
        verticalAlignment = Alignment.Bottom
    ) {
        IconButton(onClick = { /* navigate to home page */ }) {
            Icon(Icons.Default.Home, contentDescription = "Home")
        }
        Spacer(modifier = Modifier.weight(1f))
        IconButton(onClick = { /* navigate to cart */ }) {
            Icon(Icons.Default.ShoppingCart, contentDescription = "Cart")
        }
        Spacer(modifier = Modifier.weight(1f))
        IconButton(onClick = { /* navigate to settings */ }) {
            Icon(Icons.Default.Settings, contentDescription = "Settings")
        }
    }
}

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
// Todo: for LogoutDialog we can decide if we want this function or not
@Composable
fun LogoutDialog(isOpen: MutableState<Boolean>, onConfirm: () -> Unit, onDismiss: () -> Unit) {
    if (isOpen.value) {
        AlertDialog(
            onDismissRequest = onDismiss,
            title = { Text("Are you sure you want to logout?") },
            confirmButton = {
                Button(onClick = {
                    onConfirm()
                    isOpen.value = false
                }) { Text("Yes") }
            },
            dismissButton = {
                Button(onClick = {
                    onDismiss()
                    isOpen.value = false
                }) { Text("No") }
            }
        )
    }
}
