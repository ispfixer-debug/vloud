package com.vito.app.data.supabase.impl

import com.vito.app.data.supabase.QrRepository
import com.vito.app.data.model.QrToken
import com.vito.app.data.model.QrTokenType

/**
 * QR Repository Implementation 
 */
class QrRepositoryImpl : QrRepository {
    override suspend fun generateToken(type: QrTokenType, driverId: String?): Result<QrToken?> = Result.success(null)
    override suspend fun validateToken(token: String): Result<QrToken?> = Result.success(null)
    override suspend fun revokeToken(tokenId: String): Result<Boolean> = Result.success(false)
    override suspend fun getAllTokens(): Result<List<QrToken>> = Result.success(emptyList())
    override suspend fun getTokensByType(type: QrTokenType): Result<List<QrToken>> = Result.success(emptyList())
    override suspend fun getTokenInfo(token: String): Result<QrToken?> = Result.success(null)
}