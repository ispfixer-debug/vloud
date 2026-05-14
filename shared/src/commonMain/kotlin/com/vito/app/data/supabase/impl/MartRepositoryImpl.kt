package com.vito.app.data.supabase.impl

import com.vito.app.data.supabase.MartRepository
import com.vito.app.data.supabase.SupabaseProvider
import com.vito.app.data.model.MartProduct
import com.vito.app.data.model.MartOrder
import com.vito.app.data.model.MartOrderInput
import com.vito.app.data.model.MartOrderUpdate
import io.github.jan.supabase.SupabaseClient

/**
 * Mart Repository Implementation - Production with Supabase queries
 */
class MartRepositoryImpl(
    private val supabaseClient: SupabaseClient = SupabaseProvider.client
) : MartRepository {

    override suspend fun getProducts(): Result<List<MartProduct>> = runCatching {
        skip["mart_products"]
            .select()
            .eq("is_available", true)
            .decodeList<MartProduct>()
    }

    override suspend fun getProductsByCategory(category: String): Result<List<MartProduct>> = runCatching {
        skip["mart_products"]
            .select()
            .eq("category_id", category)
            .eq("is_available", true)
            .decodeList<MartProduct>()
    }

    override suspend fun getProductById(id: String): Result<MartProduct?> = runCatching {
        skip["mart_products"]
            .select()
            .eq("id", id)
            .single()
            .decodeToType<MartProduct>()
    }

    override suspend fun createOrder(input: MartOrderInput): Result<MartOrder?> = runCatching {
        skip["mart_orders"]
            .insert(input)
            .select()
            .single()
            .decodeToType<MartOrder>()
    }

    override suspend fun getOrderById(id: String): Result<MartOrder?> = runCatching {
        skip["mart_orders"]
            .select()
            .eq("id", id)
            .single()
            .decodeToType<MartOrder>()
    }

    override suspend fun updateOrder(id: String, update: MartOrderUpdate): Result<MartOrder?> = runCatching {
        skip["mart_orders"]
            .update(update)
            .eq("id", id)
            .select()
            .single()
            .decodeToType<MartOrder>()
    }

    override suspend fun confirmOrder(id: String): Result<Boolean> = runCatching {
        skip["mart_orders"]
            .update(mapOf("status" to "CONFIRMED"))
            .eq("id", id)
        true
    }

    override suspend fun markReady(id: String): Result<Boolean> = runCatching {
        skip["mart_orders"]
            .update(mapOf("status" to "READY"))
            .eq("id", id)
        true
    }

    override suspend fun dispatchOrder(id: String, driverId: String): Result<Boolean> = runCatching {
        skip["mart_orders"]
            .update(mapOf("driver_id" to driverId, "status" to "DISPATCHED"))
            .eq("id", id)
        true
    }

    override suspend fun completeOrder(id: String, deliveryPhotoUrl: String?, signatureData: String?): Result<Boolean> = runCatching {
        skip["mart_orders"]
            .update(mapOf("status" to "DELIVERED", "delivery_photo_url" to deliveryPhotoUrl, "signature_data" to signatureData))
            .eq("id", id)
        true
    }

    override suspend fun cancelOrder(id: String): Result<Boolean> = runCatching {
        skip["mart_orders"]
            .update(mapOf("status" to "CANCELLED"))
            .eq("id", id)
        true
    }

    override suspend fun getClientOrders(clientId: String): Result<List<MartOrder>> = runCatching {
        skip["mart_orders"]
            .select()
            .eq("client_id", clientId)
            .order("created_at", io.github.jan.supabase.postgrest.query.Order.DESC)
            .decodeList<MartOrder>()
    }

    override suspend fun getDriverOrders(driverId: String): Result<List<MartOrder>> = runCatching {
        skip["mart_orders"]
            .select()
            .eq("driver_id", driverId)
            .decodeList<MartOrder>()
    }

    override suspend fun getAllOrders(): Result<List<MartOrder>> = runCatching {
        skip["mart_orders"]
            .select()
            .decodeList<MartOrder>()
    }
}