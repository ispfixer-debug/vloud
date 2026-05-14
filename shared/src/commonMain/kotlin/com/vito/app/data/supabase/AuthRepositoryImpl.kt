package com.vito.app.data.supabase

import com.vito.app.data.model.SessionData
import com.vito.app.data.model.VitoUser
import io.github.jan.supabase.SupabaseClient

/**
 * Auth Repository Implementation - Uses Supabase RPC for Edge Function calls
 */
class AuthRepositoryImpl(
    private val supabaseClient: SupabaseClient = SupabaseProvider.client
) : AuthRepository {

    // Use mock for now - edge functions handle authentication
    override suspend fun signUp(
        username: String,
        pinHash: String,
        displayName: String,
        referralDriverId: String?,
        language: String
    ): Result<SessionData> = runCatching {
        // Call edge function via RPC
        val response = supabaseClient.postgrest.rpc(
            fn = "auth_signup",
            parameters = mapOf(
                "username" to username,
                "pin_hash" to pinHash,
                "display_name" to displayName,
                "referral_driver_id" to referralDriverId,
                "language" to language
            )
        )
        
        // Parse response as SessionData
        SessionData(
            id = response.toString(),
            phone = username,
            displayName = displayName,
            userType = "CLIENT",
            referralDriverId = referralDriverId,
            language = language,
            pinHash = pinHash,
            createdAt = System.currentTimeMillis()
        )
    }
    
    override suspend fun signIn(username: String, pinHash: String): Result<SessionData> = runCatching {
        // Query user by phone
        val userQuery = supabaseClient.postgrest["vito_users"]
            .select()
            .eq("phone", username)
            .single()
        
        val user = userQuery.decodeToType<VitoUser>()
        
        // Verify PIN match
        if (user.pinHash != pinHash) {
            throw IllegalStateException("Invalid credentials")
        }
        
        SessionData(
            id = user.id,
            phone = user.phone,
            displayName = user.displayName,
            userType = user.userType,
            referralDriverId = user.referralDriverId,
            language = user.language,
            pinHash = user.pinHash,
            createdAt = user.createdAt
        )
    }
    
    override suspend fun signOut() {
        // Clear local session
    }
    
    override suspend fun getCurrentSession(): SessionData? = null
    
    override fun authStateFlow(): kotlinx.coroutines.flow.Flow<Boolean> = 
        kotlinx.coroutines.flow.MutableStateFlow(false)
    
    override fun isAuthenticated(): Boolean = false
}