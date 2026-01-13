package com.example.todolist.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun PriorityChipRow(
    selectedPriority: Int,
    onPriorityChange: (Int) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        PriorityChip(
            selected = selectedPriority == 1,
            label = "LOW",
            onClick = { onPriorityChange(1) }
        )

        PriorityChip(
            selected = selectedPriority == 2,
            label = "MEDIUM",
            onClick = { onPriorityChange(2) }
        )

        PriorityChip(
            selected = selectedPriority == 3,
            label = "HIGH",
            onClick = { onPriorityChange(3) }
        )
    }
}

@Composable
fun PriorityChip(
    selected: Boolean,
    label: String,
    onClick: () -> Unit
) {
    FilterChip(
        selected = selected,
        label = { Text(text = label) },
        onClick = { onClick() },
        colors = FilterChipDefaults.filterChipColors(
            containerColor = if (selected) MaterialTheme.colorScheme.primary
            else MaterialTheme.colorScheme.surfaceVariant,
            selectedLabelColor = if (selected) MaterialTheme.colorScheme.onPrimary
            else MaterialTheme.colorScheme.onSurface
        )
    )
}


































