package com.example.todolist.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import com.example.todolist.domain.model.Filter
import com.example.todolist.presentation.screen.todoHomeScreen.TodoHomeScreenEvent
import com.example.todolist.presentation.screen.todoHomeScreen.TodoHomeScreenViewModel

@Composable
fun FilterChipRow(viewModel: TodoHomeScreenViewModel = hiltViewModel()) {

    val selectedFilter = viewModel.state.filter

    Row(modifier = Modifier
        .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        FilterChipItem(
            label = "All",
            selected = selectedFilter == Filter.ALL
        ) {
            viewModel.onEvent(TodoHomeScreenEvent.FilterChanged(filter = Filter.ALL))
        }

        FilterChipItem(
            label = "ACTIVE",
            selected = selectedFilter == Filter.ACTIVE
        ) {
            viewModel.onEvent(TodoHomeScreenEvent.FilterChanged(filter = Filter.ACTIVE))
        }

        FilterChipItem(
            label = "COMPLETED",
            selected = selectedFilter == Filter.COMPLETED
        ) {
            viewModel.onEvent(TodoHomeScreenEvent.FilterChanged(filter = Filter.COMPLETED))
        }
    }

}

@Composable
fun FilterChipItem(
    label: String,
    selected: Boolean,
    onClick: () -> Unit
) {
    FilterChip(
        selected = selected,
        onClick = onClick,
        label = {
            Text(text = label)
        },
        colors = FilterChipDefaults.filterChipColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer,
            selectedLabelColor = MaterialTheme.colorScheme.primary
        ),
        leadingIcon = {
            if (selected) {
                Icon(
                    imageVector = Icons.Default.Check,
                    contentDescription = "Selected"
                )
            } else null
        }
    )
}


























