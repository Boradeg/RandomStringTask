package com.app.randomstringtask.ui.theme.Screen

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.app.randomString.viewmodel.RandomStringUiState
import com.app.randomString.viewmodel.RandomStringViewModel

data class RandomStringDisplay(
    val value: String,
    val length: Int,
    val created: String
)

@Composable
fun HomePage(viewModel: RandomStringViewModel) {
    val uiState by viewModel.uiState.collectAsState()
    val context = LocalContext.current
    var length by remember { mutableStateOf("10") }

    Box(modifier = Modifier.fillMaxSize()) {
        RandomStringScreen(
            uiState = uiState,
            maxLength = length,
            onMaxLengthChange = { length = it },
            onGenerateClick = {
                val len = length.toIntOrNull() ?: 10
                viewModel.fetchRandomString(context, len)
            },
            onDeleteClick = { viewModel.deleteString(it) },
            onDeleteAllClick = { viewModel.clearAll() }
        )




    }
}


@Composable
fun RandomStringScreen(
    uiState: RandomStringUiState,
    maxLength: String,
    onMaxLengthChange: (String) -> Unit,
    onGenerateClick: () -> Unit,
    onDeleteClick: (RandomStringDisplay) -> Unit,
    onDeleteAllClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .statusBarsPadding()
    ) {
        OutlinedTextField(
            value = maxLength,
            onValueChange = onMaxLengthChange,
            label = { Text("Enter max length") },
            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(12.dp))

        Button(
            onClick = onGenerateClick,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Generate")
        }

        Spacer(modifier = Modifier.height(16.dp))

        when (uiState) {
            is RandomStringUiState.Loading -> {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    CircularProgressIndicator()
                }
            }

            is RandomStringUiState.Success -> {
                LazyColumn(modifier = Modifier.weight(1f)) {
                    items(uiState.data) { item ->
                        Card(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 4.dp),
                            elevation = CardDefaults.cardElevation(4.dp)
                        ) {
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(12.dp),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Column(modifier = Modifier.weight(1f)) {
                                    Text("Value: ${item.value}")
                                    Text("Length: ${item.length}")
                                    Text("Created: ${item.created}")
                                }
                                IconButton(onClick = { onDeleteClick(item) }) {
                                    Icon(
                                        imageVector = Icons.Default.Delete,
                                        contentDescription = "Delete"
                                    )
                                }
                            }
                        }
                    }
                }

                Button(
                    onClick = onDeleteAllClick,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 12.dp)
                ) {
                    Text("Delete All")
                }
            }

            is RandomStringUiState.Empty -> {
                Text(
                    text = "No data available yet.",
                    modifier = Modifier.align(Alignment.CenterHorizontally),
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }

            else -> {} // Already handled error as toast above
        }
    }
}
