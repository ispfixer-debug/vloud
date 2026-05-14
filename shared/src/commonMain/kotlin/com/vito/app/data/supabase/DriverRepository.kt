package com.vito.app.data.supabase

import com.vito.app.data.model.VitoDriver
import com.vito.app.data.model.VitoDriverInput
import com.vito.app.data.model.VitoDriverUpdate
import com.vito.app.data.model.DriverLocation

/**
 * Driver Repository - handles driver data operations
 */
interface DriverRepository {
    /**
     * Get driver by ID
     */
    suspend fun getDriverById(id: String): Result<VitoDriver?>
    
    /**
     * Get driver by username
     */
    suspend fun getDriverByUsername(username: String): Result<VitoDriver?>
    
    /**
     * Get driver by auth ID
     */
    suspend fun getDriverByAuthId(authId: String): Result<VitoDriver?>
    
    /**
     * Create new driver
     */
    suspend fun createDriver(input: VitoDriverInput): Result<VitoDriver?>
    
    /**
     * Update driver
     */
    suspend fun updateDriver(id: String, update: VitoDriverUpdate): Result<VitoDriver?>
    
    /**
     * Update driver location
     */
    suspend fun updateLocation(id: String, location: DriverLocation): Result<Boolean>
    
    /**
     * Set online/offline status
     */
    suspend fun setOnlineStatus(id: String, isOnline: Boolean): Result<Boolean>
    
    /**
     * Verify PIN
     */
    suspend fun verifyPin(username: String, pinHash: String): Result<VitoDriver?>
    
    /**
     * Get nearby available drivers
     */
    suspend fun getNearbyDrivers(lat: Double, lng: Double, radiusKm: Double): Result<List<VitoDriver>>
    
    /**
     * Get all available drivers (online + photo approved)
     */
    suspend fun getAvailableDrivers(): Result<List<VitoDriver>>
    
    /**
     * Get all drivers (admin only)
     */
    suspend fun getAllDrivers(): Result<List<VitoDriver>>
    
    /**
     * Approve driver photo (admin only)
     */
    suspend fun approveDriverPhoto(id: String, approved: Boolean): Result<Boolean>
    
    /**
     * Suspend/unsuspend driver (admin only)
     */
    suspend fun setDriverSuspended(id: String, isSuspended: Boolean): Result<Boolean>
}