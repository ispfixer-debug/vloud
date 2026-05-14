package com.vito.app.data.supabase

import com.vito.app.data.model.MartProduct
import com.vito.app.data.model.MartOrder
import com.vito.app.data.model.MartOrderInput
import com.vito.app.data.model.MartOrderUpdate

/**
 * Mart (Quick Mart) Repository - handles product listings and orders
 */
interface MartRepository {
    /**
     * Get all active products
     */
    suspend fun getProducts(): Result<List<MartProduct>>
    
    /**
     * Get products by category
     */
    suspend fun getProductsByCategory(category: String): Result<List<MartProduct>>
    
    /**
     * Get product by ID
     */
    suspend fun getProductById(id: String): Result<MartProduct?>
    
    /**
     * Create order
     */
    suspend fun createOrder(input: MartOrderInput): Result<MartOrder?>
    
    /**
     * Get order by ID
     */
    suspend fun getOrderById(id: String): Result<MartOrder?>
    
    /**
     * Update order
     */
    suspend fun updateOrder(id: String, update: MartOrderUpdate): Result<MartOrder?>
    
    /**
     * Confirm order (store)
     */
    suspend fun confirmOrder(id: String): Result<Boolean>
    
    /**
     * Mark ready for pickup
     */
    suspend fun markReady(id: String): Result<Boolean>
    
    /**
     * Dispatch order with driver
     */
    suspend fun dispatchOrder(id: String, driverId: String): Result<Boolean>
    
    /**
     * Complete order (confirm delivery with photo/signature)
     */
    suspend fun completeOrder(id: String, deliveryPhotoUrl: String?, signatureData: String?): Result<Boolean>
    
    /**
     * Cancel order
     */
    suspend fun cancelOrder(id: String): Result<Boolean>
    
    /**
     * Get client's orders
     */
    suspend fun getClientOrders(clientId: String): Result<List<MartOrder>>
    
    /**
     * Get driver's orders
     */
    suspend fun getDriverOrders(driverId: String): Result<List<MartOrder>>
    
    /**
     * Get all orders (admin)
     */
    suspend fun getAllOrders(): Result<List<MartOrder>>
}