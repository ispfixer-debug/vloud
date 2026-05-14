package com.vito.app.data.supabase.impl

import com.vito.app.data.supabase.PaymentRepository
import com.vito.app.data.model.WalletTransaction
import com.vito.app.data.model.PayoutRequest

/**
 * Payment Repository Implementation 
 */
class PaymentRepositoryImpl : PaymentRepository {
    override suspend fun getTransactions(userId: String): Result<List<WalletTransaction>> = Result.success(emptyList())
    override suspend fun createPaymentIntent(userId: String, amount: Double): Result<String> = Result.success("")
    override suspend fun confirmTopup(userId: String, amount: Double, stripePaymentId: String): Result<Boolean> = Result.success(false)
    override suspend fun createPayoutRequest(driverId: String, amount: Double): Result<PayoutRequest?> = Result.success(null)
    override suspend fun getPayoutRequests(driverId: String): Result<List<PayoutRequest>> = Result.success(emptyList())
    override suspend fun getPayoutRequestById(id: String): Result<PayoutRequest?> = Result.success(null)
    override suspend fun getAllPayoutRequests(): Result<List<PayoutRequest>> = Result.success(emptyList())
    override suspend fun processPayout(payoutId: String): Result<Boolean> = Result.success(false)
}