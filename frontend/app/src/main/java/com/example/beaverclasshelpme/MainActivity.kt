package com.example.beaverclasshelpme

import android.Manifest
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.beaverclasshelpme.data.BackendService
import com.example.beaverclasshelpme.data.TokenBody
import com.example.beaverclasshelpme.navigation.BottomNavigationBar
import com.example.beaverclasshelpme.ui.pages.HistoryPage
import com.example.beaverclasshelpme.ui.pages.SelectedPage
import com.example.beaverclasshelpme.ui.pages.Screen
import com.example.beaverclasshelpme.ui.pages.SearchPage
import com.example.beaverclasshelpme.ui.pages.SearchResultPage
import com.example.beaverclasshelpme.ui.pages.SignInPage
import com.example.beaverclasshelpme.ui.pages.SignUpPage
import com.example.beaverclasshelpme.ui.theme.BeaverClassHelpMeTheme
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.messaging.FirebaseMessaging
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers

class MainActivity : ComponentActivity() {
    companion object {
        private const val TAG = "MainActivity"
    }

    private val tokenViewModel: TokenViewModel by viewModels()
    private val saveClassViewModel: SavedClassViewModel by viewModels()

    private lateinit var preferencesManager: SharedPreferencesManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        preferencesManager = SharedPreferencesManager(this)

        // Permission Request for external resources
        val requestPermissionLauncher =
            registerForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted: Boolean ->
                if (isGranted) {
                    // Permission is granted. You can proceed with showing notifications here if necessary.
                    Toast.makeText(this, "Notification permission granted", Toast.LENGTH_SHORT).show()
                } else {
                    // Permission is denied.
                    Toast.makeText(this, "Notification permission denied", Toast.LENGTH_SHORT).show()
                }
            }

        // Notification Permission for higher SDK versions
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.POST_NOTIFICATIONS) != PackageManager.PERMISSION_GRANTED) {
                requestPermissionLauncher.launch(Manifest.permission.POST_NOTIFICATIONS)
            }
        }

        // Firebase Notification
        FirebaseMessaging.getInstance().token.addOnCompleteListener(OnCompleteListener { task ->
            if (!task.isSuccessful) {
                Log.w(TAG, "Fetching FCM registration token failed", task.exception)
                return@OnCompleteListener
            }

            // Get new FCM registration token
            val token = task.result

            /* TODO: Hardcoded email here */
            val data = TokenBody("hk@gmail.com", token)
            val jsonObject = Gson().toJsonTree(data).asJsonObject

            tokenViewModel.submitToken(jsonObject)

            // Log FMC token for notifications
            val msg = "FCM Registration Token: $token"
            Log.d(TAG, msg)
        })

        setContent {
            BeaverClassHelpMeTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    BeaverClassApp(
                        preferencesManager,
                        saveClassViewModel,
                        tokenViewModel
                    )
                }
            }
        }
    }
}

// This is the main app
@Composable
fun BeaverClassApp(
    preferencesManager: SharedPreferencesManager,
    viewModel: SavedClassViewModel,
    tokenViewModel: TokenViewModel
) {
    val isUserLoggedIn = true /* TODO: Hardcoded here */

    // Have to use viewModel Auth
    if (isUserLoggedIn) {
       MainAppFlow(preferencesManager, viewModel, tokenViewModel)
    } else {
        AuthFlow()
    }
}

@Composable
fun MainAppFlow(
    preferencesManager: SharedPreferencesManager,
    viewModel: SavedClassViewModel,
    tokenViewModel: TokenViewModel
) {
    val navController = rememberNavController()
    val backendService = BackendService.create()
    val classRepository = ClassRepository(backendService, Dispatchers.IO)

    Scaffold(
        bottomBar = { BottomNavigationBar(navController = navController) }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = Screen.Home.route,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(Screen.Home.route) { SearchPage(navController, preferencesManager) }
            composable(Screen.Cart.route) { HistoryPage(savedClassViewModel = viewModel)}
            composable(Screen.Settings.route) {
                SelectedPage(
                    navController = navController,
                    preferencesManager,
                    onDeleteClick = {  },
                    onLogoutClick = { viewModel.removeAllClasses() },
                    onDraftClick = { /* ... */ }
                )
            }
            composable(
                route = "search_result/{classCode}/{crn}/{term}",
                arguments = listOf(
                    navArgument("classCode") { type = NavType.StringType },
                    navArgument("crn") { type = NavType.StringType },
                    navArgument("term") { type = NavType.StringType }
                )
            ) { backStackEntry ->
                val classCode = backStackEntry.arguments?.getString("classCode") ?: ""
                val crn = backStackEntry.arguments?.getString("crn") ?: ""
                val term = backStackEntry.arguments?.getString("term") ?: ""

                val classViewModel: ClassViewModel = viewModel(key = "ClassViewModel_$classCode$crn$term") {
                    ClassViewModel(classRepository)
                }

                classViewModel.loadClassData(classCode, crn, term)

                SearchResultPage(
                    preferencesManager,
                    classViewModel = classViewModel,
                    savedClassViewModel = viewModel,
                    tokenViewModel = tokenViewModel,
                    onAddClick = {
                        navController.navigate(Screen.Settings.route)
                    }
                )
            }
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
}