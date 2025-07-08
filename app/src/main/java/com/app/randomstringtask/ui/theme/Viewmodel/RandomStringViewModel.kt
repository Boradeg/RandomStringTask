package com.app.randomString.viewmodel


import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.randomstringtask.ui.theme.Screen.RandomStringDisplay
import com.app.randomstringtask.ui.theme.UseCases.FetchRandomStringUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RandomStringViewModel @Inject constructor(
    private val fetchRandomStringUseCase: FetchRandomStringUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow<RandomStringUiState>(RandomStringUiState.Empty)
    val uiState: StateFlow<RandomStringUiState> = _uiState.asStateFlow()

    private val stringList = mutableListOf<RandomStringDisplay>()

    fun fetchRandomString(context: Context, maxLength: Int) {
        viewModelScope.launch {
            _uiState.value = RandomStringUiState.Loading
            delay(1000)
            val result = fetchRandomStringUseCase(context, maxLength)
            if (result != null) {
                stringList.add(result)
                _uiState.value = RandomStringUiState.Success(stringList.toList())
            } else {
                _uiState.value = RandomStringUiState.Error("Failed to fetch random string")
            }
        }
    }

    fun deleteString(item: RandomStringDisplay) {
        stringList.remove(item)
        _uiState.value = if (stringList.isEmpty()) {
            RandomStringUiState.Empty
        } else {
            RandomStringUiState.Success(stringList.toList())
        }
    }

    fun clearAll() {
        stringList.clear()
        _uiState.value = RandomStringUiState.Empty
    }
}



sealed class RandomStringUiState {
    object Loading : RandomStringUiState()
    data class Success(val data: List<RandomStringDisplay>) : RandomStringUiState()
    data class Error(val message: String) : RandomStringUiState()
    object Empty : RandomStringUiState()
}