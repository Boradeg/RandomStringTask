package com.app.randomstringtask.presentations.modal

sealed class RandomStringUiState {
    data object Idle : RandomStringUiState()
    data object Loading : RandomStringUiState()
    data class Error(val message: String) : RandomStringUiState()
}