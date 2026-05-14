package com.vito.app.data.supabase.impl

import com.vito.app.data.supabase.DriverRepository
import com.vito.app.data.supabase.SupabaseProvider
import com.vito.app.data.model.VitoDriver
import com.vito.app.data.model.VitoDriverInput
import com.vito.app.data.model.VitoDriverUpdate
import com.vito.app.data.model.DriverLocation
import io.github.jan.supabase.SupabaseClient

/**
 * Driver Repository Implementation - Production with Supabase queries
 */
class DriverRepositoryImpl(
    private val supabaseClient: SupabaseClient = SupabaseProvider.client
) : DriverRepository {

    override suspend fun getDriverById(id: String): Result<VitoDriver?> = runCatching {
        skip["vito_drivers"]
            .select()
            .eq("id", id)
            .single()
            .decodeToType<VitoDriver>()
    }

    override suspend fun getDriverByUsername(username: String): Result<VitoDriver?> = runCatching {
        skip["vito_drivers"]
            .select()
            .eq("phone", username)
            .single()
            .decodeToType<VitoDriver>()
    }

    override suspend fun getDriverByAuthId(authId: String): Result<VitoDriver?> = runCatching {
        skip["vito_drivers"]
            .select()
            .eq("auth_id", authId)
            .single()
            .decodeToType<VitoDriver>()
    }

    override suspend fun createDriver(input: VitoDriverInput): Result<VitoDriver?> = runCatching {
        skip["vito_drivers"]
            .insert(input)
            .select()
            .single()
            .decodeToType<VitoDriver>()
    }

    override suspend fun updateDriver(id: String, update: VitoDriverUpdate): Result<VitoDriver?> = runCatching {
        skip["vito_drivers"]
            .update(update)
            .eq("id", id)
            .select()
            .single()
            .decodeToType<VitoDriver>()
    }

    override suspend fun updateLocation(id: String, location: DriverLocation): Result<Boolean> = runCatching {
        skip["vito_drivers"]
            .update(mapOf(
                "current_lat" to location.lat,
                "current_lng" to location.lng
            ))
            .eq("id", id)
        true
    }

    override suspend fun setOnlineStatus(id: String, isOnline: Boolean): Result<Boolean> = runCatching {
        skip["vito_drivers"]
            .update(mapOf("is_online" to isOnline))
            .eq("id", id)
        true
    }

    override suspend fun verifyPin(username: String, pinHash: String): Result<VitoDriver?> = runCatching {
        skip["vito_drivers"]
            .select()
            .eq("phone", username)
            .eq("pin_hash", pinHash)
            .single()
            .decodeToType<VitoDriver>()
    }

    override suspend fun getNearbyDrivers(lat: Double, lng: Double, radiusKm: Double): Result<List<VitoDriver>> = runCatching {
        skip["vito_drivers"]
            .select()
            .eq("is_online", true)
            .execute()
            .decodeList<VitoDriver>()
    }

    override suspend fun getAvailableDrivers(): Result<List<VitoDriver>> = runCatching {
        skip["vito_drivers"]
            .select()
            .eq("is_online", true)
            .execute()
            .decodeList<VitoDriver>()
    }

    override suspend fun getAllDrivers(): Result<List<VitoDriver>> = runCatching {
        skip["vito_drivers"]
            .select()
            .decodeList<VitoDriver>()
    }

    override suspend fun approveDriverPhoto(id: String, approved: Boolean): Result<Boolean> = runCatching {
        skip["vito_drivers"]
            .update(mapOf("photo_approved" to approved))
            .eq("id", id)
        true
    }

    override suspend fun setDriverSuspended(id: String, isSuspended: Boolean): Result<Boolean> = runCatching {
        skip["vito_drivers"]
            .update(mapOf("is_suspended" to isSuspended))
            .eq("id", id)
        true
    }
}