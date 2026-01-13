package com.example.todolist.presentation.screen.todoHomeScreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.todolist.presentation.components.FilterChipRow
import com.example.todolist.presentation.components.TodoItemCard
import com.example.todolist.presentation.navigation.TodoAddEditScreenRoute

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TodoHomeScreenUi(
    navController: NavHostController,
    viewModel: TodoHomeScreenViewModel = hiltViewModel(),
) {

    val state = viewModel.state
    val todos = state.todos

    Scaffold(
        floatingActionButton = {
            ExtendedFloatingActionButton(
                onClick = { navController.navigate(TodoAddEditScreenRoute) },
                icon = { Image(imageVector = Icons.Default.Add, contentDescription = "Add Btn") },
                text = { Text("Add Todo") },
                modifier = Modifier
                    .navigationBarsPadding()
            )
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
                .background(color = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.35f))
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {

            //search
            OutlinedTextField(
                value = state.searchQuery,
                onValueChange = { viewModel.onEvent(TodoHomeScreenEvent.SearchChanged(query = it)) },
                leadingIcon = {
                    Image(
                        imageVector = Icons.Default.Search,
                        contentDescription = "Search Todo"
                    )
                },
                label = { Text(text = "Search Tasks...") },
                modifier = Modifier
                    .fillMaxWidth(),
                shape = RoundedCornerShape(8.dp),
                maxLines = 1,
                singleLine = true
            )

            //filter row
            FilterChipRow()

            //empty or list

            if (todos.isEmpty()) {
                Text(
                    text = "Add some Todos"
                )
            } else {
                LazyColumn(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    items(todos, key = { it.id }) { todos ->
                        TodoItemCard(
                            todo = todos,
                            onToggle = { viewModel.onEvent(TodoHomeScreenEvent.ToggleComplete(todo = it))},
                            onDelete = { viewModel.onEvent(TodoHomeScreenEvent.DeleteTodo(todo = it))}
                        )
                    }
                }
            }
        }
    }
}







































