package com.vito.app.data.supabase

import com.vito.app.data.model.VitoUser
import com.vito.app.data.model.VitoUserInput
import com.vito.app.data.model.VitoUserUpdate
import com.vito.app.data.model.SessionData
import kotlinx.coroutines.flow.Flow

/**
 * Auth Repository - handles authentication flows
 */
interface AuthRepository {
    /**
     * Sign up with username and PIN
     * @param username The desired username
     * @param pinHash PIN hashed with SHA-256
     * @param displayName User's display name
     * @param referralDriverId Optional driver referral token
     * @param language Preferred language code
     * @return SessionData on success, null on failure
     */
    suspend fun signUp(
        username: String,
        pinHash: String,
        displayName: String,
        referralDriverId: String? = null,
        language: String = "en"
    ): Result<SessionData>
    
    /**
     * Sign in with username and PIN
     * @param username The username
     * @param pinHash PIN hashed with SHA-256
     * @return SessionData on success, null on failure
     */
    suspend fun signIn(username: String, pinHash: String): Result<SessionData>
    
    /**
     * Sign out and clear session
     */
    suspend fun signOut()
    
    /**
     * Get current session
     * @return SessionData if signed in, null otherwise
     */
    suspend fun getCurrentSession(): SessionData?
    
    /**
     * Stream of auth state changes
     */
    fun authStateFlow(): Flow<Boolean>
    
    /**
     * Verify if user is authenticated
     */
    fun isAuthenticated(): Boolean
}