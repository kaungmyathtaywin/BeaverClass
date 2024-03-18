package com.example.beaverclasshelpme

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
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
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.beaverclasshelpme.navigation.BottomNavigationBar
import com.example.beaverclasshelpme.ui.pages.CartPage
import com.example.beaverclasshelpme.ui.pages.Screen
import com.example.beaverclasshelpme.ui.pages.SearchPage
import com.example.beaverclasshelpme.ui.pages.SettingsPage
import com.example.beaverclasshelpme.ui.pages.SignInPage
import com.example.beaverclasshelpme.ui.pages.SignUpPage
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
                    BeaverClassApp()
                }
            }
        }
    }
}

// This is the main app
@Composable
fun BeaverClassApp() {
    val isUserLoggedIn = true /* TODO: Hardcoded here */

    // Have to use viewModel Auth
    if (isUserLoggedIn) {
       MainAppFlow()
    } else {
        AuthFlow()
    }
}

@Composable
fun MainAppFlow() {
    val navController = rememberNavController()

    Scaffold(
        bottomBar = { BottomNavigationBar(navController = navController) }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = Screen.Home.route,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(Screen.Home.route) { SearchPage() }
            composable(Screen.Cart.route) { CartPage(cartItems,
                        onDeleteClick = { item ->
                            // deal with the delete item logic here
                        },
                        onSubmitClick = {
                            // deal with the submit logic here
                        },
                        onDraftClick = { item ->
                            // deal with the draft logic here
                        }
                    ) }
            composable(Screen.Settings.route) { SettingsPage() }
        }
    }
}

@Composable
fun AuthFlow() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = Screen.SignIn.route
    ) {
        composable(Screen.SignIn.route) { SignInPage(
            onSignInClick = { /*TODO*/ },
            onSignUpClick = { navController.navigate(Screen.SignUp.route) }
        )}
        composable(Screen.SignUp.route) { SignUpPage(
            onSignUpClick = { /*TODO*/ },
        )}
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