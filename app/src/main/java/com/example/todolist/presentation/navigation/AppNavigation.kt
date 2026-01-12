package com.example.todolist.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.todolist.presentation.screen.todoAddEditScreen.TodoAddEditScreenUi
import com.example.todolist.presentation.screen.todoHomeScreen.TodoHomeScreenUi

@Composable
fun AppNavigation(modifier: Modifier = Modifier) {

    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = TodoHomeScreenRoute
    ) {
        composable<TodoHomeScreenRoute> {
            TodoHomeScreenUi(navController)
        }
        composable<TodoAddEditScreenRoute> {
            TodoAddEditScreenUi( navController = navController)
        }
    }
}