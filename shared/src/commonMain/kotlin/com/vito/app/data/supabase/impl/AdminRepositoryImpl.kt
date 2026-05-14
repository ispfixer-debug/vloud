package com.vito.app.data.supabase.impl

import com.vito.app.data.supabase.AdminRepository
import com.vito.app.data.model.VitoAdmin
import com.vito.app.data.model.VitoUser
import com.vito.app.data.model.VitoDriver
import com.vito.app.data.model.VitoRide
import com.vito.app.data.model.MartOrder
import com.vito.app.data.supabase.DashboardStats

/**
 * Admin Repository Implementation 
 */
@Suppress("UNCHECKED_CAST")
class AdminRepositoryImpl : AdminRepository {
    override suspend fun signIn(username: String, pinHash: String): Result<VitoAdmin?> = Result.success(null)
    override suspend fun getAllUsers(): Result<List<VitoUser>> = Result.success(emptyList())
    override suspend fun getAllDrivers(): Result<List<VitoDriver>> = Result.success(emptyList())
    override suspend fun setUserSuspended(userId: String, suspended: Boolean): Result<Boolean> = Result.success(false)
    override suspend fun setDriverSuspended(driverId: String, suspended: Boolean): Result<Boolean> = Result.success(false)
    override suspend fun setDriverPhotoApproved(driverId: String, approved: Boolean): Result<Boolean> = Result.success(false)
    override suspend fun getAllRides(): Result<List<VitoRide>> = Result.success(emptyList())
    override suspend fun getActiveRides(): Result<List<VitoRide>> = Result.success(emptyList())
    override suspend fun getAllMartOrders(): Result<List<MartOrder>> = Result.success(emptyList())
    override suspend fun getPendingMartOrders(): Result<List<MartOrder>> = Result.success(emptyList())
    override suspend fun getDashboardStats(): Result<DashboardStats> = Result.success(
        DashboardStats(
            totalUsers = 0, 
            totalDrivers = 0, 
            activeDrivers = 0,
            totalRides = 0,
            ridesToday = 0,
            totalRevenue = 0.0,
            revenueToday = 0.0,
            pendingPayouts = 0.0
        )
    ) as Result<DashboardStats>
}