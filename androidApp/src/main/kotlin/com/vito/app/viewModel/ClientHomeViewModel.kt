package com.vito.app.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vito.app.data.supabase.AuthRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

/**
 * Client Home ViewModel - Main client dashboard
 */
class ClientHomeViewModel(
    private val authRepository: AuthRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(ClientHomeUiState())
    val uiState: StateFlow<ClientHomeUiState> = _uiState

    fun loadUser() {
        viewModelScope.launch {
            val session = authRepository.getCurrentSession()
            _uiState.value = _uiState.value.copy(
                userId = session?.userId ?: "",
                username = session?.username ?: "",
                displayName = session?.displayName ?: "",
                userType = session?.userType ?: ""
            )
        }
    }
}

data class ClientHomeUiState(
    val userId: String = "",
    val username: String = "",
    val displayName: String = "",
    val userType: String = "",
    val isLoading: Boolean = false,
    val walletBalance: Double = 0.0
)