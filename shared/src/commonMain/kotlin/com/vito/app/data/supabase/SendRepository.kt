package com.vito.app.data.supabase

import com.vito.app.data.model.VitoSend
import com.vito.app.data.model.VitoSendInput
import com.vito.app.data.model.SendStatus

/**
 * Send Repository - handles package delivery (Send) operations
 */
interface SendRepository {
    /**
     * Create new send request
     */
    suspend fun createSend(input: VitoSendInput): Result<VitoSend?>
    
    /**
     * Get send by ID
     */
    suspend fun getSendById(id: String): Result<VitoSend?>
    
    /**
     * Update send
     */
    suspend fun updateSend(id: String, update: com.vito.app.data.model.VitoSendUpdate): Result<VitoSend?>
    
    /**
     * Accept send (driver only)
     */
    suspend fun acceptSend(sendId: String, driverId: String): Result<Boolean>
    
    /**
     * Cancel send
     */
    suspend fun cancelSend(id: String, reason: String): Result<Boolean>
    
    /**
     * Mark as picked up
     */
    suspend fun markPickedUp(id: String): Result<Boolean>
    
    /**
     * Mark as delivered
     */
    suspend fun markDelivered(id: String): Result<Boolean>
    
    /**
     * Get sender's sends
     */
    suspend fun getSenderSends(senderId: String): Result<List<VitoSend>>
    
    /**
     * Get sender's active send
     */
    suspend fun getSenderActiveSend(senderId: String): Result<VitoSend?>
    
    /**
     * Get driver's sends
     */
    suspend fun getDriverSends(driverId: String): Result<List<VitoSend>>
    
    /**
     * Get all pending sends near location
     */
    suspend fun getPendingSendsNearLocation(lat: Double, lng: Double, radiusKm: Double): Result<List<VitoSend>>
}