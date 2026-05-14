package com.vito.app.data.supabase

import com.vito.app.data.model.VitoUser
import com.vito.app.data.model.VitoUserInput
import com.vito.app.data.model.VitoUserUpdate

/**
 * User Repository - handles user data operations
 */
interface UserRepository {
    /**
     * Get user by ID
     */
    suspend fun getUserById(id: String): Result<VitoUser?>
    
    /**
     * Get user by username
     */
    suspend fun getUserByUsername(username: String): Result<VitoUser?>
    
    /**
     * Get user by auth ID (from Supabase Auth)
     */
    suspend fun getUserByAuthId(authId: String): Result<VitoUser?>
    
    /**
     * Create new user
     */
    suspend fun createUser(input: VitoUserInput): Result<VitoUser?>
    
    /**
     * Update user
     */
    suspend fun updateUser(id: String, update: VitoUserUpdate): Result<VitoUser?>
    
    /**
     * Update wallet balance (atomic operation)
     */
    suspend fun updateWalletBalance(id: String, amount: Double, isTopup: Boolean): Result<Boolean>
    
    /**
     * Verify PIN (for PIN-based auth)
     */
    suspend fun verifyPin(username: String, pinHash: String): Result<VitoUser?>
    
    /**
     * Get all users (admin only)
     */
    suspend fun getAllUsers(): Result<List<VitoUser>>
    
    /**
     * Suspend/unsuspend user (admin only)
     */
    suspend fun setUserSuspended(id: String, isSuspended: Boolean): Result<Boolean>
    
    /**
     * Get referral stats
     */
    suspend fun getReferralStats(userId: String): Result<ReferralStats>
}

data class ReferralStats(
    val totalReferrals: Int = 0,
    val activeReferrals: Int = 0,
    val totalEarnings: Double = 0.0
)