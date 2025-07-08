package com.app.randomstringtask

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.ViewModel
import com.app.randomstringtask.ui.theme.RandomStringTaskTheme
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject
@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val viewModel: RandomStringViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {

            RandomStringTaskTheme {
                val message by viewModel.message.collectAsState()

                Scaffold( modifier = Modifier.fillMaxSize() ) { innerPadding ->
                    Greeting(
                        name = message,
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}
@HiltViewModel
class RandomStringViewModel @Inject constructor(
    private val repository: RandomStringRepository
) : ViewModel() {

    private val _message = MutableStateFlow("")
    val message: StateFlow<String> = _message

    init {
        _message.value = repository.getData()
    }
}
class RandomStringRepository @Inject constructor() {
    fun getData(): String {
        return "Hello from Repository"
    }
}
@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    RandomStringTaskTheme {
        Greeting("Android")
    }
}