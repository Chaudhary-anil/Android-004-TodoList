package com.example.todolist.presentation.screen.todoHomeScreen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.todolist.presentation.navigation.TodoAddEditScreenRoute

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TodoHomeScreenUi(navController: NavHostController, viewModel: TodoHomeScreenViewModel = hiltViewModel()) {

    val state = viewModel.state
    val todos = state.todos

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                onClick = { navController.navigate(TodoAddEditScreenRoute)}
            ) {
                Text("Add Todo")
            }
        },
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text("My Todos") },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = MaterialTheme.colorScheme.primaryContainer)
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            LazyColumn {
                items(todos, key = {it.id}) { todos ->
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                    ) {
                        Column {
                            Text(
                                text = todos.title
                            )
                            Text(
                                text = todos.description
                            )
                        }
                    }
                }
            }
        }
    }
}







































