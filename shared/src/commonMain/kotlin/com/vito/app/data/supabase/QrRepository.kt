package com.vito.app.data.supabase

import com.vito.app.data.model.QrToken
import com.vito.app.data.model.QrTokenType

/**
 * QR Token Repository - handles referral/invite tokens
 */
interface QrRepository {
    /**
     * Generate new QR token
     * @param type Token type (client_referral or driver_onboarding)
     * @param driverId Only for client_referral type
     */
    suspend fun generateToken(type: QrTokenType, driverId: String? = null): Result<QrToken?>
    
    /**
     * Validate and consume token
     */
    suspend fun validateToken(token: String): Result<QrToken?>
    
    /**
     * Revoke token
     */
    suspend fun revokeToken(tokenId: String): Result<Boolean>
    
    /**
     * Get all tokens (admin)
     */
    suspend fun getAllTokens(): Result<List<QrToken>>
    
    /**
     * Get tokens by type
     */
    suspend fun getTokensByType(type: QrTokenType): Result<List<QrToken>>
    
    /**
     * Get token info (public - for landing page)
     */
    suspend fun getTokenInfo(token: String): Result<QrToken?>
}