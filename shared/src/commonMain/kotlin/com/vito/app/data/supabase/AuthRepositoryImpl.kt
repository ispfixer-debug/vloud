package com.vito.app.data.supabase

import com.vito.app.data.model.SessionData
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow

/**
 * Auth Repository Implementation - stub
 */
class AuthRepositoryImpl : AuthRepository {
    
    private val _authState = MutableStateFlow(false)
    private var currentSession: SessionData? = null
    
    override suspend fun signUp(username: String, pinHash: String, displayName: String, referralDriverId: String?, language: String): Result<SessionData> {
        // TODO: Implement with Supabase
        return Result.failure(NotImplementedError("Auth requires backend"))
    }
    
    override suspend fun signIn(username: String, pinHash: String): Result<SessionData> {
        // TODO: Implement with Supabase
        return Result.failure(NotImplementedError("Auth requires backend"))
    }
    
    override suspend fun signOut() {
        currentSession = null
        _authState.value = false
    }
    
    override suspend fun getCurrentSession(): SessionData? = currentSession
    
    override fun authStateFlow(): Flow<Boolean> = _authState
    
    override fun isAuthenticated(): Boolean = _authState.value
}