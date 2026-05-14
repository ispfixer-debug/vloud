package com.vito.app.data.supabase

import com.vito.app.data.model.VitoUser
import com.vito.app.data.model.VitoUserInput
import com.vito.app.data.model.VitoUserUpdate
import io.github.jan.supabase.SupabaseClient

/**
 * User Repository Implementation - Production-ready with Supabase queries
 */
class UserRepositoryImpl(
    private val supabaseClient: SupabaseClient = SupabaseProvider.client
) : UserRepository {

    override suspend fun getUserById(id: String): Result<VitoUser?> = runCatching {
        supabaseClient.postgrest["vito_users"]
            .select()
            .eq("id", id)
            .single()
            .decodeToType<VitoUser>()
    }

    override suspend fun getUserByUsername(username: String): Result<VitoUser?> = runCatching {
        supabaseClient.postgrest["vito_users"]
            .select()
            .eq("phone", username)
            .single()
            .decodeToType<VitoUser>()
    }

    override suspend fun getUserByAuthId(authId: String): Result<VitoUser?> = runCatching {
        supabaseClient.postgrest["vito_users"]
            .select()
            .eq("auth_id", authId)
            .single()
            .decodeToType<VitoUser>()
    }

    override suspend fun createUser(input: VitoUserInput): Result<VitoUser?> = runCatching {
        supabaseClient.postgrest["vito_users"]
            .insert(input)
            .select()
            .single()
            .decodeToType<VitoUser>()
    }

    override suspend fun updateUser(id: String, update: VitoUserUpdate): Result<VitoUser?> = runCatching {
        supabaseClient.postgrest["vito_users"]
            .update(update)
            .eq("id", id)
            .select()
            .single()
            .decodeToType<VitoUser>()
    }

    override suspend fun updateWalletBalance(id: String, amount: Double, isTopup: Boolean): Result<Boolean> = runCatching {
        // Use RPC for atomic operation
        supabaseClient.postgrest.rpc(
            fn = "update_user_wallet",
            parameters = mapOf(
                "user_id" to id,
                "amount" to amount,
                "is_topup" to isTopup
            )
        )
        true
    }

    override suspend fun verifyPin(username: String, pinHash: String): Result<VitoUser?> = runCatching {
        supabaseClient.postgrest["vito_users"]
            .select()
            .eq("phone", username)
            .eq("pin_hash", pinHash)
            .single()
            .decodeToType<VitoUser>()
    }

    override suspend fun getAllUsers(): Result<List<VitoUser>> = runCatching {
        supabaseClient.postgrest["vito_users"]
            .select()
            .decodeList<VitoUser>()
    }

    override suspend fun setUserSuspended(id: String, isSuspended: Boolean): Result<Boolean> = runCatching {
        supabaseClient.postgrest["vito_users"]
            .update(mapOf("is_suspended" to isSuspended))
            .eq("id", id)
        true
    }

    override suspend fun getReferralStats(userId: String): Result<ReferralStats> = runCatching {
        supabaseClient.postgrest["vito_users"]
            .select()
            .eq("referral_driver_id", userId)
            .decodeList<VitoUser>()
            .let { referrers ->
                ReferralStats(
                    totalReferrals = referrers.size,
                    activeReferrals = referrers.count { !it.isSuspended }
                )
            }
    }
}