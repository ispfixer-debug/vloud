package com.vito.app.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MartProduct(
    @SerialName("id") val id: String = "",
    @SerialName("name") val name: String = "",
    @SerialName("description") val description: String = "",
    @SerialName("image_url") val imageUrl: String? = null,
    @SerialName("price_usd") val priceUsd: Double = 0.0,
    @SerialName("stock_count") val stockCount: Int = 0,
    @SerialName("category") val category: String = "",
    @SerialName("is_active") val isActive: Boolean = true,
    @SerialName("created_at") val createdAt: String = "",
    @SerialName("updated_at") val updatedAt: String = ""
) {
    // Computed field: active if stock > 0
    val isInStock: Boolean get() = stockCount > 0
}

@Serializable
data class MartProductInput(
    @SerialName("name") val name: String,
    @SerialName("description") val description: String,
    @SerialName("image_url") val imageUrl: String? = null,
    @SerialName("price_usd") val priceUsd: Double,
    @SerialName("stock_count") val stockCount: Int,
    @SerialName("category") val category: String
)

@Serializable
enum class MartOrderStatus {
    @SerialName("placed") PLACED,
    @SerialName("confirmed") CONFIRMED,
    @SerialName("preparing") PREPARING,
    @SerialName("ready") READY,
    @SerialName("dispatched") DISPATCHED,
    @SerialName("delivered") DELIVERED,
    @SerialName("cancelled") CANCELLED
}

@Serializable
data class MartOrderItem(
    @SerialName("product_id") val productId: String = "",
    @SerialName("product_name") val productName: String = "",
    @SerialName("quantity") val quantity: Int = 1,
    @SerialName("unit_price") val unitPrice: Double = 0.0,
    @SerialName("subtotal") val subtotal: Double = 0.0
)

@Serializable
data class MartOrder(
    @SerialName("id") val id: String = "",
    @SerialName("client_id") val clientId: String = "",
    @SerialName("driver_id") val driverId: String? = null,
    @SerialName("items") val items: List<MartOrderItem> = emptyList(),
    @SerialName("subtotal") val subtotal: Double = 0.0,
    @SerialName("delivery_fee") val deliveryFee: Double = 0.0,
    @SerialName("total") val total: Double = 0.0,
    @SerialName("delivery_address") val deliveryAddress: String = "",
    @SerialName("delivery_lat") val deliveryLat: Double = 0.0,
    @SerialName("delivery_lng") val deliveryLng: Double = 0.0,
    @SerialName("status") val status: MartOrderStatus = MartOrderStatus.PLACED,
    @SerialName("delivery_photo_url") val deliveryPhotoUrl: String? = null,
    @SerialName("signature_data") val signatureData: String? = null,
    @SerialName("payment_method") val paymentMethod: String = "wallet",
    @SerialName("is_paid") val isPaid: Boolean = false,
    @SerialName("placed_at") val placedAt: String = "",
    @SerialName("confirmed_at") val confirmedAt: String? = null,
    @SerialName("ready_at") val readyAt: String? = null,
    @SerialName("dispatched_at") val dispatchedAt: String? = null,
    @SerialName("delivered_at") val deliveredAt: String? = null,
    @SerialName("cancelled_at") val cancelledAt: String? = null,
    @SerialName("created_at") val createdAt: String = "",
    @SerialName("updated_at") val updatedAt: String = ""
)

@Serializable
data class MartOrderInput(
    @SerialName("client_id") val clientId: String,
    @SerialName("items") val items: List<MartOrderItem>,
    @SerialName("delivery_fee") val deliveryFee: Double,
    @SerialName("delivery_address") val deliveryAddress: String,
    @SerialName("delivery_lat") val deliveryLat: Double,
    @SerialName("delivery_lng") val deliveryLng: Double,
    @SerialName("payment_method") val paymentMethod: String = "wallet"
)

@Serializable
data class MartOrderUpdate(
    @SerialName("driver_id") val driverId: String? = null,
    @SerialName("status") val status: MartOrderStatus? = null,
    @SerialName("delivery_photo_url") val deliveryPhotoUrl: String? = null,
    @SerialName("signature_data") val signatureData: String? = null,
    @SerialName("is_paid") val isPaid: Boolean? = null,
    @SerialName("confirmed_at") val confirmedAt: String? = null,
    @SerialName("ready_at") val readyAt: String? = null,
    @SerialName("dispatched_at") val dispatchedAt: String? = null,
    @SerialName("delivered_at") val deliveredAt: String? = null,
    @SerialName("cancelled_at") val cancelledAt: String? = null,
    @SerialName("updated_at") val updatedAt: String = ""
)