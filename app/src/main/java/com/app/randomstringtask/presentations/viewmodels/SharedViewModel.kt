package com.app.randomString.viewmodel


import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.randomstringtask.domain.UseCases.DeleteAllRandomStringsUseCase
import com.app.randomstringtask.domain.UseCases.DeleteRandomStringUseCase
import com.app.randomstringtask.domain.UseCases.FetchRandomStringUseCase
import com.app.randomstringtask.domain.UseCases.InsertRandomStringUseCase
import com.app.randomstringtask.domain.UseCases.ObserveRandomStringsUseCase
import com.app.randomstringtask.presentations.modal.RandomStringDisplay
import com.app.randomstringtask.presentations.modal.RandomStringUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject


@HiltViewModel
class SharedViewModel @Inject constructor(
    private val fetchRandomStringUseCase: FetchRandomStringUseCase,
    private val insertUseCase: InsertRandomStringUseCase,
    private val observeUseCase: ObserveRandomStringsUseCase,
    private val deleteUseCase: DeleteRandomStringUseCase,
    private val deleteAllUseCase: DeleteAllRandomStringsUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow<RandomStringUiState>(RandomStringUiState.Idle)
    val uiState: StateFlow<RandomStringUiState> = _uiState

    val allItems: StateFlow<List<RandomStringDisplay>> = observeUseCase()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = emptyList()
        )

    fun fetchRandomString(context: Context, maxLength: Int) {
        viewModelScope.launch {
            _uiState.value = RandomStringUiState.Loading
            try {
                val result = withContext(Dispatchers.IO) {
                    fetchRandomStringUseCase(context, maxLength)
                }

                if (result != null) {
                    insertUseCase(result)
                    _uiState.value = RandomStringUiState.Idle
                } else {
                    _uiState.value = RandomStringUiState.Error("Failed to fetch data")
                }
            } catch (e: Exception) {
                _uiState.value = RandomStringUiState.Error("Unexpected error: ${e.message}")
            }
        }
    }

    fun deleteString(item: RandomStringDisplay) {
        viewModelScope.launch {
            try {
                deleteUseCase(item)
            } catch (e: Exception) {
                _uiState.value = RandomStringUiState.Error("Failed to delete: ${e.message}")
            }
        }
    }

    fun clearAll() {
        viewModelScope.launch {
            try {
                deleteAllUseCase()
            } catch (e: Exception) {
                _uiState.value = RandomStringUiState.Error("Failed to delete all: ${e.message}")
            }
        }
    }
}

