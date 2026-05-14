package com.vito.app.data.supabase.impl

import com.vito.app.data.supabase.RideRepository
import com.vito.app.data.supabase.SupabaseProvider
import com.vito.app.data.model.VitoRide
import com.vito.app.data.model.VitoRideInput
import com.vito.app.data.model.VitoRideUpdate
import com.vito.app.data.model.RideStatus
import io.github.jan.supabase.SupabaseClient

/**
 * Ride Repository Implementation - Production with Supabase queries
 */
class RideRepositoryImpl(
    private val supabaseClient: SupabaseClient = SupabaseProvider.client
) : RideRepository {

    override suspend fun createRide(input: VitoRideInput): Result<VitoRide?> = runCatching {
        skip["vito_rides"]
            .insert(input)
            .select()
            .single()
            .decodeToType<VitoRide>()
    }

    override suspend fun getRideById(id: String): Result<VitoRide?> = runCatching {
        skip["vito_rides"]
            .select()
            .eq("id", id)
            .single()
            .decodeToType<VitoRide>()
    }

    override suspend fun updateRide(id: String, update: VitoRideUpdate): Result<VitoRide?> = runCatching {
        skip["vito_rides"]
            .update(update)
            .eq("id", id)
            .select()
            .single()
            .decodeToType<VitoRide>()
    }

    override suspend fun acceptRide(rideId: String, driverId: String): Result<Boolean> = runCatching {
        skip["vito_rides"]
            .update(mapOf(
                "driver_id" to driverId,
                "status" to RideStatus.ACCEPTED.name
            ))
            .eq("id", rideId)
            .eq("status", RideStatus.WAITING.name)
        true
    }

    override suspend fun cancelRide(id: String, reason: String): Result<Boolean> = runCatching {
        skip["vito_rides"]
            .update(mapOf(
                "status" to RideStatus.CANCELLED.name,
                "cancel_reason" to reason
            ))
            .eq("id", id)
        true
    }

    override suspend fun startRide(id: String): Result<Boolean> = runCatching {
        skip["vito_rides"]
            .update(mapOf("status" to RideStatus.IN_PROGRESS.name))
            .eq("id", id)
            .eq("status", RideStatus.ACCEPTED.name)
        true
    }

    override suspend fun completeRide(id: String, finalFare: Double): Result<Boolean> = runCatching {
        skip["vito_rides"]
            .update(mapOf(
                "status" to RideStatus.COMPLETED.name,
                "final_fare" to finalFare
            ))
            .eq("id", id)
        true
    }

    override suspend fun rateRide(id: String, rating: Int, comment: String?): Result<Boolean> = runCatching {
        skip["vito_rides"]
            .update(mapOf(
                "rating" to rating,
                "rating_comment" to comment
            ))
            .eq("id", id)
        true
    }

    override suspend fun getClientRides(clientId: String): Result<List<VitoRide>> = runCatching {
        skip["vito_rides"]
            .select()
            .eq("client_id", clientId)
            .decodeList<VitoRide>()
    }

    override suspend fun getClientActiveRide(clientId: String): Result<VitoRide?> = runCatching {
        skip["vito_rides"]
            .select()
            .eq("client_id", clientId)
            .`in`("status", listOf(RideStatus.WAITING.name, RideStatus.ACCEPTED.name, RideStatus.IN_PROGRESS.name))
            .limit(1)
            .decodeList<VitoRide>()
            .firstOrNull()
    }

    override suspend fun getClientRideHistory(clientId: String, limit: Int): Result<List<VitoRide>> = runCatching {
        skip["vito_rides"]
            .select()
            .eq("client_id", clientId)
            .eq("status", RideStatus.COMPLETED.name)
            .order("created_at", io.github.jan.supabase.postgrest.query.Order.DESC)
            .limit(limit)
            .decodeList<VitoRide>()
    }

    override suspend fun getDriverRides(driverId: String): Result<List<VitoRide>> = runCatching {
        skip["vito_rides"]
            .select()
            .eq("driver_id", driverId)
            .decodeList<VitoRide>()
    }

    override suspend fun getDriverActiveRide(driverId: String): Result<VitoRide?> = runCatching {
        skip["vito_rides"]
            .select()
            .eq("driver_id", driverId)
            .`in`("status", listOf(RideStatus.ACCEPTED.name, RideStatus.IN_PROGRESS.name))
            .limit(1)
            .decodeList<VitoRide>()
            .firstOrNull()
    }

    override suspend fun getDriverRideHistory(driverId: String, limit: Int): Result<List<VitoRide>> = runCatching {
        skip["vito_rides"]
            .select()
            .eq("driver_id", driverId)
            .eq("status", RideStatus.COMPLETED.name)
            .order("created_at", io.github.jan.supabase.postgrest.query.Order.DESC)
            .limit(limit)
            .decodeList<VitoRide>()
    }

    override suspend fun getPendingRidesNearLocation(lat: Double, lng: Double, radiusKm: Double): Result<List<VitoRide>> = runCatching {
        skip["vito_rides"]
            .select()
            .eq("status", RideStatus.WAITING.name)
            .execute()
            .decodeList<VitoRide>()
    }

    override suspend fun getAllRides(): Result<List<VitoRide>> = runCatching {
        skip["vito_rides"]
            .select()
            .decodeList<VitoRide>()
    }

    override suspend fun getRidesByStatus(status: RideStatus): Result<List<VitoRide>> = runCatching {
        skip["vito_rides"]
            .select()
            .eq("status", status.name)
            .decodeList<VitoRide>()
    }
}