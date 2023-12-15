package com.example.nativelearningandroidcompose.ui.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navArgument
import androidx.navigation.compose.rememberNavController
import com.example.nativelearningandroidcompose.data.CoworkingSpace
import com.example.nativelearningandroidcompose.data.DataRepository
import com.example.nativelearningandroidcompose.ui.theme.NativelearningandroidcomposeTheme

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            var viewModel = MainViewModel(DataRepository(), LocalContext.current)
            val navController = rememberNavController()

            Column {
                TopAppBar(
                    title = { Text(text = "CoworkSpaces") },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp)
                )

                NavHost(
                    navController = navController,
                    startDestination = "main_screen"
                ) {
                    composable("main_screen") {
                        Surface(
                            modifier = Modifier.fillMaxSize(),
                            color = MaterialTheme.colorScheme.background
                        ) {
                            CoworkingList(
                                viewModel,
                                onItemClicked = { selectedItem ->
                                    navController.navigate("detail_screen/${selectedItem.organisation}")
                                }
                            )
                        }
                    }

                    composable(
                        "detail_screen/{organisation}",
                        arguments = listOf(navArgument("organisation") { type = NavType.StringType })
                    ) { backStackEntry ->
                        val organisation = requireNotNull(backStackEntry.arguments?.getString("organisation"))
                        val selectedItem = viewModel.getCoworkingSpaceByOrganisation(organisation)
                        if (selectedItem != null) {
                            DetailScreen(coworkingSpace = selectedItem)
                        }
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    NativelearningandroidcomposeTheme {
        CoworkingList(MainViewModel(DataRepository(), LocalContext.current), { })
    }
}