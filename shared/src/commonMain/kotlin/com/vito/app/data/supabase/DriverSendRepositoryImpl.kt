package com.vito.app.data.supabase

import com.vito.app.data.model.DriverLocation
import com.vito.app.data.model.VitoDriver
import com.vito.app.data.model.VitoDriverInput
import com.vito.app.data.model.VitoDriverUpdate
import com.vito.app.data.model.VitoSend
import com.vito.app.data.model.VitoSendInput
import com.vito.app.data.model.VitoSendUpdate

class DriverRepositoryImpl : DriverRepository {
    override suspend fun getDriverById(id: String): Result<VitoDriver?> = runCatching { null }
    override suspend fun getDriverByUsername(username: String): Result<VitoDriver?> = runCatching { null }
    override suspend fun getDriverByAuthId(authId: String): Result<VitoDriver?> = runCatching { null }
    override suspend fun createDriver(input: VitoDriverInput): Result<VitoDriver?> = runCatching { null }
    override suspend fun updateDriver(id: String, update: VitoDriverUpdate): Result<VitoDriver?> = runCatching { null }
    override suspend fun updateLocation(id: String, location: DriverLocation): Result<Boolean> = runCatching { false }
    override suspend fun setOnlineStatus(id: String, isOnline: Boolean): Result<Boolean> = runCatching { false }
    override suspend fun verifyPin(username: String, pinHash: String): Result<VitoDriver?> = runCatching { null }
    override suspend fun getNearbyDrivers(lat: Double, lng: Double, radiusKm: Double): Result<List<VitoDriver>> = runCatching { emptyList() }
    override suspend fun getAvailableDrivers(): Result<List<VitoDriver>> = runCatching { emptyList() }
    override suspend fun getAllDrivers(): Result<List<VitoDriver>> = runCatching { emptyList() }
    override suspend fun approveDriverPhoto(id: String, approved: Boolean): Result<Boolean> = runCatching { false }
    override suspend fun setDriverSuspended(id: String, isSuspended: Boolean): Result<Boolean> = runCatching { false }
}

class SendRepositoryImpl : SendRepository {
    override suspend fun createSend(input: VitoSendInput): Result<VitoSend?> = runCatching { null }
    override suspend fun getSendById(id: String): Result<VitoSend?> = runCatching { null }
    override suspend fun updateSend(id: String, update: VitoSendUpdate): Result<VitoSend?> = runCatching { null }
    override suspend fun acceptSend(sendId: String, driverId: String): Result<Boolean> = runCatching { false }
    override suspend fun cancelSend(id: String, reason: String): Result<Boolean> = runCatching { false }
    override suspend fun markPickedUp(id: String): Result<Boolean> = runCatching { false }
    override suspend fun markDelivered(id: String): Result<Boolean> = runCatching { false }
    override suspend fun getSenderSends(senderId: String): Result<List<VitoSend>> = runCatching { emptyList() }
    override suspend fun getSenderActiveSend(senderId: String): Result<VitoSend?> = runCatching { null }
    override suspend fun getDriverSends(driverId: String): Result<List<VitoSend>> = runCatching { emptyList() }
    override suspend fun getPendingSendsNearLocation(lat: Double, lng: Double, radiusKm: Double): Result<List<VitoSend>> = runCatching { emptyList() }
}