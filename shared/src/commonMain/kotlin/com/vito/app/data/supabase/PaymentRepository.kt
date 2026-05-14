package com.vito.app.data.supabase

import com.vito.app.data.model.WalletTransaction
import com.vito.app.data.model.PayoutRequest

/**
 * Payment/Finance Repository - handles wallet and Stripe payments
 */
interface PaymentRepository {
    /**
     * Get user's wallet transactions
     */
    suspend fun getTransactions(userId: String): Result<List<WalletTransaction>>
    
    /**
     * Create Stripe payment intent for wallet top-up
     */
    suspend fun createPaymentIntent(userId: String, amount: Double): Result<String>  // Returns client_secret
    
    /**
     * Confirm top-up after Stripe payment succeeds
     */
    suspend fun confirmTopup(userId: String, amount: Double, stripePaymentId: String): Result<Boolean>
    
    /**
     * Create payout request (driver)
     */
    suspend fun createPayoutRequest(driverId: String, amount: Double): Result<PayoutRequest?>
    
    /**
     * Get driver's payout requests
     */
    suspend fun getPayoutRequests(driverId: String): Result<List<PayoutRequest>>
    
    /**
     * Get payout request by ID
     */
    suspend fun getPayoutRequestById(id: String): Result<PayoutRequest?>
    
    /**
     * Get all payout requests (admin)
     */
    suspend fun getAllPayoutRequests(): Result<List<PayoutRequest>>
    
    /**
     * Process payout (admin) - calls Stripe transfer
     */
    suspend fun processPayout(payoutId: String): Result<Boolean>
}