package com.app.randomstringtask.presentations.screens

import android.widget.Toast
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.app.randomString.viewmodel.RandomStringUiState
import com.app.randomString.viewmodel.RandomStringViewModel
import com.app.randomstringtask.Data.local.AppDatabase
import com.app.randomstringtask.Data.local.RandomStringDao
import com.app.randomstringtask.R
import com.app.randomstringtask.presentations.modal.RandomStringDisplay

@Composable
fun HomePage(viewModel: RandomStringViewModel) {

    val uiState by viewModel.uiState.collectAsState()
    val uiStateGetData by viewModel.uiStateGetData.collectAsState()

    val context = LocalContext.current
    var length by remember { mutableStateOf("10") }


    LaunchedEffect(uiState) {
        if (uiState is RandomStringUiState.Error || uiStateGetData is RandomStringUiState.Error) {
            Toast
                .makeText(context, (uiState as RandomStringUiState.Error).message, Toast.LENGTH_SHORT)
                .show()
        }
    }

    Scaffold { padding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
        ) {
            RandomStringScreen(
                uiState = uiStateGetData,
                maxLength = length,
                onMaxLengthChange = { length = it },
                onGenerateClick = {
                    val len = length.toIntOrNull() ?: 10
                    viewModel.fetchRandomString(context, len)
                },
                onDeleteClick = { viewModel.deleteString(it) },
                onDeleteAllClick = { viewModel.clearAll() }
            )

            if (uiState is RandomStringUiState.Loading) {
                Box(modifier = Modifier.align(Alignment.Center), contentAlignment = Alignment.Center) {
                    CircularProgressIndicator()
                }
            }
        }
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

    ) {
        OutlinedTextField(
            value = maxLength,
            onValueChange = onMaxLengthChange,
            label = { Text(stringResource(R.string.txt_enter_max_length)) },
            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(12.dp))

        Button(
            onClick = onGenerateClick,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(stringResource(R.string.txt_generate))
        }

        Spacer(modifier = Modifier.height(16.dp))

        when (uiState) {
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
                                    Text(stringResource(R.string.txt_value, item.value))
                                    Text(stringResource(R.string.txt_length, item.length))
                                    Text(stringResource(R.string.txt_created, item.created))
                                }
                                IconButton(onClick = { onDeleteClick(item) }) {
                                    Icon(
                                        imageVector = Icons.Default.Delete,
                                        contentDescription = stringResource(R.string.txt_delete)
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
                    Text(stringResource(R.string.txt_delete_all))
                }
            }
            is RandomStringUiState.Empty -> {
                Text(
                    text = stringResource(R.string.txt_no_data_available_yet),
                    modifier = Modifier.align(Alignment.CenterHorizontally),
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
            else -> Unit
        }
    }
}
