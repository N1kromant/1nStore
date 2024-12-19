package com.n1kromant.instore

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.List
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.n1kromant.instore.ViewModels.MainModelViewIntent
import com.n1kromant.instore.models.ChangePageIntent
import com.n1kromant.instore.ui.theme.InStoreTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val mainViewModel: MainModelViewIntent by viewModels()

        enableEdgeToEdge()
        setContent {
            val navController = rememberNavController()
            val page by mainViewModel.currentPage.collectAsState()

            LaunchedEffect(page) {
                navController.navigate(page.toString())
            }

            InStoreTheme {
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    bottomBar = {
//                        BottomAppBar {  }
                        BottomNavigationBar(mainViewModel, navController)
                    }
                ) { innerPadding ->
                    val modifier = Modifier.padding(innerPadding)

                    NavHost(
                        modifier = modifier,
                        navController = navController,
                        startDestination = STORE_PAGE.toString()
                    ) {
                        composable(STORE_PAGE.toString()) {
                            Greeting("Me1")
                        }
                        composable(CART_PAGE.toString()) {
                            Greeting("Me2")
                        }
                    }

                }
            }
        }
    }
}

@Composable
fun BottomNavigationBar(viewModel: MainModelViewIntent, navController: NavController) {
    val page by viewModel.currentPage.collectAsState()

    NavigationBar {
        NavigationBarItem(
            icon = { Icon(Icons.AutoMirrored.Filled.List, contentDescription = "Store") },
            selected = if (page == 0) true else false,
            onClick = {
                viewModel.newIntent(ChangePageIntent(STORE_PAGE))
            }
        )
        NavigationBarItem(
            icon = { Icon(Icons.Default.ShoppingCart, contentDescription = "Cart") },
            selected = if (page == 1) true else false,
            onClick = {
                viewModel.newIntent(ChangePageIntent(CART_PAGE))
            }
        )
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
    InStoreTheme {
        Greeting("Android")
    }
}

const val STORE_PAGE = 0
const val CART_PAGE = 1