package com.vito.app.data.supabase

import com.vito.app.data.model.VitoAdmin
import com.vito.app.data.model.VitoDriver
import com.vito.app.data.model.VitoUser
import com.vito.app.data.model.VitoRide
import com.vito.app.data.model.MartOrder

/**
 * Admin Repository - handles admin operations
 */
interface AdminRepository {
    /**
     * Verify admin PIN login
     */
    suspend fun signIn(username: String, pinHash: String): Result<VitoAdmin?>
    
    /**
     * Get all users
     */
    suspend fun getAllUsers(): Result<List<VitoUser>>
    
    /**
     * Get all drivers
     */
    suspend fun getAllDrivers(): Result<List<VitoDriver>>
    
    /**
     * Suspend/unsuspend user
     */
    suspend fun setUserSuspended(userId: String, suspended: Boolean): Result<Boolean>
    
    /**
     * Suspend/unsuspend driver
     */
    suspend fun setDriverSuspended(driverId: String, suspended: Boolean): Result<Boolean>
    
    /**
     * Approve/reject driver photo
     */
    suspend fun setDriverPhotoApproved(driverId: String, approved: Boolean): Result<Boolean>
    
    /**
     * Get all rides
     */
    suspend fun getAllRides(): Result<List<VitoRide>>
    
    /**
     * Get all active rides (admin dashboard)
     */
    suspend fun getActiveRides(): Result<List<VitoRide>>
    
    /**
     * Get all mart orders
     */
    suspend fun getAllMartOrders(): Result<List<MartOrder>>
    
    /**
     * Get pending mart orders
     */
    suspend fun getPendingMartOrders(): Result<List<MartOrder>>
    
    /**
     * Get admin dashboard stats
     */
    suspend fun getDashboardStats(): Result<DashboardStats>
}

data class DashboardStats(
    val totalUsers: Int = 0,
    val totalDrivers: Int = 0,
    val activeDrivers: Int = 0,
    val totalRides: Int = 0,
    val ridesToday: Int = 0,
    val totalRevenue: Double = 0.0,
    val revenueToday: Double = 0.0,
    val pendingPayouts: Double = 0.0
)