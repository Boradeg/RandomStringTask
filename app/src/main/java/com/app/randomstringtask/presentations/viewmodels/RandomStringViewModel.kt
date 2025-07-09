package com.app.randomString.viewmodel


import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.randomstringtask.presentations.modal.RandomStringDisplay
import com.app.randomstringtask.domain.UseCases.DeleteAllRandomStringsUseCase
import com.app.randomstringtask.domain.UseCases.DeleteRandomStringUseCase
import com.app.randomstringtask.domain.UseCases.FetchRandomStringUseCase
import com.app.randomstringtask.domain.UseCases.InsertRandomStringUseCase
import com.app.randomstringtask.domain.UseCases.ObserveRandomStringsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

sealed class RandomStringUiState {
    data object Loading : RandomStringUiState()
    data object IDLE : RandomStringUiState()
    data class Success(val data: List<RandomStringDisplay>) : RandomStringUiState()
    data class Error(val message: String) : RandomStringUiState()
    data object Empty : RandomStringUiState()
}

@HiltViewModel
class RandomStringViewModel @Inject constructor(
    private val fetchRandomStringUseCase: FetchRandomStringUseCase,
    private val insertUseCase: InsertRandomStringUseCase,
    private val observeUseCase: ObserveRandomStringsUseCase,
    private val deleteUseCase: DeleteRandomStringUseCase,
    private val deleteAllUseCase: DeleteAllRandomStringsUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow<RandomStringUiState>(RandomStringUiState.Empty)
    val uiState: StateFlow<RandomStringUiState> = _uiState.asStateFlow()

    private val _uiStateGetData = MutableStateFlow<RandomStringUiState>(RandomStringUiState.Empty)
    val uiStateGetData : StateFlow<RandomStringUiState> = _uiStateGetData.asStateFlow()

    init {
        observeAllStringsFromRoom()
    }

    private fun observeAllStringsFromRoom() {
        observeUseCase().onEach { list ->
            _uiStateGetData.value = if (list.isEmpty()) {
                    RandomStringUiState.Empty
                } else {
                    RandomStringUiState.Success(list)
                }
            }
            .catch { e -> _uiStateGetData.value = RandomStringUiState.Error(e.message ?: "Unknown Error") }
            .launchIn(viewModelScope)
    }


    fun fetchRandomString(context: Context, maxLength: Int) {
        viewModelScope.launch {
            _uiState.value = RandomStringUiState.Loading
            val result = withContext(Dispatchers.IO) {
                fetchRandomStringUseCase(context, maxLength)
            }
            if (result != null) {
                insertUseCase(result) // ✅ This will automatically update Room Flow
                _uiState.value= RandomStringUiState.IDLE
                // No need to update _uiState manually — Room observer will trigger
            } else {
               // observeAllStringsFromRoom()
                _uiState.value = RandomStringUiState.Error("Failed to fetch random string")
            }
        }
    }

    fun deleteString(item: RandomStringDisplay) {
        viewModelScope.launch {
            deleteUseCase(item)
        }
    }

    fun clearAll() {
        viewModelScope.launch {
            deleteAllUseCase()
        }
    }
}
/*
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
}*/
