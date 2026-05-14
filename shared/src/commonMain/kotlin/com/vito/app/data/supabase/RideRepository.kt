package com.vito.app.data.supabase

import com.vito.app.data.model.VitoRide
import com.vito.app.data.model.VitoRideInput
import com.vito.app.data.model.RideStatus

/**
 * Ride Repository - handles ride operations
 */
interface RideRepository {
    /**
     * Create new ride request
     */
    suspend fun createRide(input: VitoRideInput): Result<VitoRide?>
    
    /**
     * Get ride by ID
     */
    suspend fun getRideById(id: String): Result<VitoRide?>
    
    /**
     * Update ride status/info
     */
    suspend fun updateRide(id: String, update: com.vito.app.data.model.VitoRideUpdate): Result<VitoRide?>
    
    /**
     * Accept ride (driver only)
     * Uses atomic UPDATE WHERE status='pending' + check rows_affected to handle race condition
     */
    suspend fun acceptRide(rideId: String, driverId: String): Result<Boolean>
    
    /**
     * Cancel ride
     */
    suspend fun cancelRide(id: String, reason: String): Result<Boolean>
    
    /**
     * Mark ride as started
     */
    suspend fun startRide(id: String): Result<Boolean>
    
    /**
     * Complete ride (driver only)
     */
    suspend fun completeRide(id: String, finalFare: Double): Result<Boolean>
    
    /**
     * Rate ride (client or driver)
     */
    suspend fun rateRide(id: String, rating: Int, comment: String? = null): Result<Boolean>
    
    /**
     * Get client's rides
     */
    suspend fun getClientRides(clientId: String): Result<List<VitoRide>>
    
    /**
     * Get client's active ride
     */
    suspend fun getClientActiveRide(clientId: String): Result<VitoRide?>
    
    /**
     * Get client's ride history
     */
    suspend fun getClientRideHistory(clientId: String, limit: Int = 20): Result<List<VitoRide>>
    
    /**
     * Get driver's rides
     */
    suspend fun getDriverRides(driverId: String): Result<List<VitoRide>>
    
    /**
     * Get driver's active ride
     */
    suspend fun getDriverActiveRide(driverId: String): Result<VitoRide?>
    
    /**
     * Get driver's ride history
     */
    suspend fun getDriverRideHistory(driverId: String, limit: Int = 20): Result<List<VitoRide>>
    
    /**
     * Get all pending rides near location
     */
    suspend fun getPendingRidesNearLocation(lat: Double, lng: Double, radiusKm: Double): Result<List<VitoRide>>
    
    /**
     * Get all rides (admin only)
     */
    suspend fun getAllRides(): Result<List<VitoRide>>
    
    /**
     * Get rides by status (admin only)
     */
    suspend fun getRidesByStatus(status: RideStatus): Result<List<VitoRide>>
}