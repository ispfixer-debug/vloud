package com.vito.app.data.supabase

import com.vito.app.data.model.VitoUser
import com.vito.app.data.model.VitoUserInput
import com.vito.app.data.model.VitoUserUpdate

/**
 * User Repository Implementation - stub
 */
class UserRepositoryImpl : UserRepository {
    override suspend fun getUserById(id: String): Result<VitoUser?> = Result.success(null)
    override suspend fun getUserByUsername(username: String): Result<VitoUser?> = Result.success(null)
    override suspend fun getUserByAuthId(authId: String): Result<VitoUser?> = Result.success(null)
    override suspend fun createUser(input: VitoUserInput): Result<VitoUser?> = Result.success(null)
    override suspend fun updateUser(id: String, update: VitoUserUpdate): Result<VitoUser?> = Result.success(null)
    override suspend fun updateWalletBalance(id: String, amount: Double, isTopup: Boolean): Result<Boolean> = Result.success(false)
    override suspend fun verifyPin(username: String, pinHash: String): Result<VitoUser?> = Result.success(null)
    override suspend fun getAllUsers(): Result<List<VitoUser>> = Result.success(emptyList())
    override suspend fun setUserSuspended(id: String, isSuspended: Boolean): Result<Boolean> = Result.success(false)
    override suspend fun getReferralStats(userId: String): Result<ReferralStats> = Result.success(ReferralStats())
}