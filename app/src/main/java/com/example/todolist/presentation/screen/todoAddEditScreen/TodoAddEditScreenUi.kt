package com.example.todolist.presentation.screen.todoAddEditScreen

import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.todolist.presentation.components.PriorityChipRow
import com.example.todolist.presentation.navigation.TodoHomeScreenRoute

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TodoAddEditScreenUi(
    viewModel: TodoAddEditScreenViewModel = hiltViewModel(),
    navController: NavHostController
) {
    val state = viewModel.state
    val context = LocalContext.current

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text("Add Task") },
                navigationIcon = {
                    Icon(
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = "Back Navigation",
                        modifier = Modifier
                            .clickable(
                                indication = null,
                                interactionSource = remember { MutableInteractionSource() }
                            ) {
                                navController.navigate(TodoHomeScreenRoute)
                            }
                    )
                },
                actions = {
                    TextButton(
                        onClick = {
                            if (state.title.isNotEmpty()) {
                                viewModel.onEvent(TodoAddEditScreenEvent.OnSaveClicked)}
                            else {
                                Toast.makeText(context, "Enter Title", Toast.LENGTH_SHORT).show()
                            }
                        }
                    ) {
                        Text(text = "Save")
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = MaterialTheme.colorScheme.primaryContainer)
            )
        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
                .padding(16.dp)
                .verticalScroll(rememberScrollState())
        ) {
            Text(
                text = "Title *"
            )
            OutlinedTextField(
                value = state.title,
                onValueChange = {it ->  viewModel.onEvent(TodoAddEditScreenEvent.OnTitleChange(title = it))},
                singleLine = true,
                modifier = Modifier
                    .fillMaxWidth()
            )
            Text("Description")
            OutlinedTextField(
                value = state.description,
                onValueChange = {description ->  viewModel.onEvent(TodoAddEditScreenEvent.OnDescriptionChange(description = description) )},
                minLines = 3,
                modifier = Modifier
                    .fillMaxWidth()
            )
            //priority chips
            Text("Priority")
            PriorityChipRow(selectedPriority = state.priority) { viewModel.onEvent(
                TodoAddEditScreenEvent.OnPriorityChange(it)) }
        }
    }
}




























































