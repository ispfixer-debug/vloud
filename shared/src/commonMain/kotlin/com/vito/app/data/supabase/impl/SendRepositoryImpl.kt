package com.vito.app.data.supabase.impl

import com.vito.app.data.supabase.SendRepository
import com.vito.app.data.supabase.SupabaseProvider
import com.vito.app.data.model.VitoSend
import com.vito.app.data.model.VitoSendInput
import com.vito.app.data.model.VitoSendUpdate
import io.github.jan.supabase.SupabaseClient

/**
 * Send Repository Implementation - Production with Supabase queries
 */
class SendRepositoryImpl(
    private val supabaseClient: SupabaseClient = SupabaseProvider.client
) : SendRepository {

    override suspend fun createSend(input: VitoSendInput): Result<VitoSend?> = runCatching {
        skip["vito_sends"]
            .insert(input)
            .select()
            .single()
            .decodeToType<VitoSend>()
    }

    override suspend fun getSendById(id: String): Result<VitoSend?> = runCatching {
        skip["vito_sends"]
            .select()
            .eq("id", id)
            .single()
            .decodeToType<VitoSend>()
    }

    override suspend fun updateSend(id: String, update: VitoSendUpdate): Result<VitoSend?> = runCatching {
        skip["vito_sends"]
            .update(update)
            .eq("id", id)
            .select()
            .single()
            .decodeToType<VitoSend>()
    }

    override suspend fun acceptSend(sendId: String, driverId: String): Result<Boolean> = runCatching {
        skip["vito_sends"]
            .update(mapOf("driver_id" to driverId, "status" to "ACCEPTED"))
            .eq("id", sendId)
            .eq("status", "WAITING")
        true
    }

    override suspend fun cancelSend(id: String, reason: String): Result<Boolean> = runCatching {
        skip["vito_sends"]
            .update(mapOf("status" to "CANCELLED", "cancel_reason" to reason))
            .eq("id", id)
        true
    }

    override suspend fun markPickedUp(id: String): Result<Boolean> = runCatching {
        skip["vito_sends"]
            .update(mapOf("status" to "PICKED_UP"))
            .eq("id", id)
            .eq("status", "ACCEPTED")
        true
    }

    override suspend fun markDelivered(id: String): Result<Boolean> = runCatching {
        skip["vito_sends"]
            .update(mapOf("status" to "DELIVERED"))
            .eq("id", id)
        true
    }

    override suspend fun getSenderSends(senderId: String): Result<List<VitoSend>> = runCatching {
        skip["vito_sends"]
            .select()
            .eq("sender_id", senderId)
            .decodeList<VitoSend>()
    }

    override suspend fun getSenderActiveSend(senderId: String): Result<VitoSend?> = runCatching {
        skip["vito_sends"]
            .select()
            .eq("sender_id", senderId)
            .`in`("status", listOf("WAITING", "ACCEPTED", "PICKED_UP"))
            .limit(1)
            .decodeList<VitoSend>()
            .firstOrNull()
    }

    override suspend fun getDriverSends(driverId: String): Result<List<VitoSend>> = runCatching {
        skip["vito_sends"]
            .select()
            .eq("driver_id", driverId)
            .decodeList<VitoSend>()
    }

    override suspend fun getPendingSendsNearLocation(lat: Double, lng: Double, radiusKm: Double): Result<List<VitoSend>> = runCatching {
        skip["vito_sends"]
            .select()
            .eq("status", "WAITING")
            .execute()
            .decodeList<VitoSend>()
    }
}