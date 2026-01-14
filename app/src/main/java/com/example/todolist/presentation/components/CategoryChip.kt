package com.example.todolist.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

@Composable
fun CategoryChipRow(
    selectedCategory: String,
    onChangeCategory: (String) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically
    ) {
        CategoryItem(
            selected = "Personal" == selectedCategory,
            label = "Personal",
            onClick = { onChangeCategory("Personal") }
        )

        CategoryItem(
            selected = "Shopping" == selectedCategory,
            label = "Shopping",
            onClick = { onChangeCategory("Shopping") }
        )

        CategoryItem(
            selected = "Work" == selectedCategory,
            label = "Work",
            onClick = { onChangeCategory("Work") }
        )
    }
}

@Composable
fun CategoryItem(
    selected: Boolean,
    label: String,
    onClick: () -> Unit
) {
    FilterChip(
        selected = selected,
        label = { Text(text = label) },
        onClick = { onClick() },
        colors = FilterChipDefaults.filterChipColors(
            selectedContainerColor = if (selected) MaterialTheme.colorScheme.secondaryContainer else MaterialTheme.colorScheme.secondary
        )
    )
}