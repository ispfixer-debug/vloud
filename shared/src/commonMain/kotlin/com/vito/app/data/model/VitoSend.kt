package com.vito.app.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
enum class SendStatus {
    @SerialName("searching") SEARCHING,
    @SerialName("assigned") ASSIGNED,
    @SerialName("picked_up") PICKED_UP,
    @SerialName("delivered") DELIVERED,
    @SerialName("cancelled") CANCELLED
}

@Serializable
data class VitoSend(
    @SerialName("id") val id: String = "",
    @SerialName("sender_id") val senderId: String = "",
    @SerialName("driver_id") val driverId: String? = null,
    @SerialName("recipient_name") val recipientName: String = "",
    @SerialName("recipient_phone") val recipientPhone: String = "",
    
    // Pickup
    @SerialName("pickup_lat") val pickupLat: Double = 0.0,
    @SerialName("pickup_lng") val pickupLng: Double = 0.0,
    @SerialName("pickup_address") val pickupAddress: String = "",
    
    // Delivery
    @SerialName("delivery_lat") val deliveryLat: Double = 0.0,
    @SerialName("delivery_lng") val deliveryLng: Double = 0.0,
    @SerialName("delivery_address") val deliveryAddress: String = "",
    
    // Package info
    @SerialName("package_description") val packageDescription: String = "",
    @SerialName("package_weight_kg") val packageWeightKg: Double = 0.0,
    
    // Fare
    @SerialName("fare_amount") val fareAmount: Double = 0.0,
    
    // Status
    @SerialName("status") val status: SendStatus = SendStatus.SEARCHING,
    
    // Payment
    @SerialName("payment_method") val paymentMethod: String = "wallet",
    @SerialName("is_paid") val isPaid: Boolean = false,
    
    // Timestamps
    @SerialName("requested_at") val requestedAt: String = "",
    @SerialName("accepted_at") val acceptedAt: String? = null,
    @SerialName("picked_up_at") val pickedUpAt: String? = null,
    @SerialName("delivered_at") val deliveredAt: String? = null,
    @SerialName("cancelled_at") val cancelledAt: String? = null,
    @SerialName("cancel_reason") val cancelReason: String? = null,
    
    @SerialName("created_at") val createdAt: String = "",
    @SerialName("updated_at") val updatedAt: String = ""
)

@Serializable
data class VitoSendInput(
    @SerialName("sender_id") val senderId: String,
    @SerialName("recipient_name") val recipientName: String,
    @SerialName("recipient_phone") val recipientPhone: String,
    @SerialName("pickup_lat") val pickupLat: Double,
    @SerialName("pickup_lng") val pickupLng: Double,
    @SerialName("pickup_address") val pickupAddress: String,
    @SerialName("delivery_lat") val deliveryLat: Double,
    @SerialName("delivery_lng") val deliveryLng: Double,
    @SerialName("delivery_address") val deliveryAddress: String,
    @SerialName("package_description") val packageDescription: String,
    @SerialName("package_weight_kg") val packageWeightKg: Double,
    @SerialName("fare_amount") val fareAmount: Double,
    @SerialName("payment_method") val paymentMethod: String = "wallet"
)

@Serializable
data class VitoSendUpdate(
    @SerialName("driver_id") val driverId: String? = null,
    @SerialName("status") val status: SendStatus? = null,
    @SerialName("fare_amount") val fareAmount: Double? = null,
    @SerialName("accepted_at") val acceptedAt: String? = null,
    @SerialName("picked_up_at") val pickedUpAt: String? = null,
    @SerialName("delivered_at") val deliveredAt: String? = null,
    @SerialName("cancelled_at") val cancelledAt: String? = null,
    @SerialName("cancel_reason") val cancelReason: String? = null,
    @SerialName("is_paid") val isPaid: Boolean? = null,
    @SerialName("updated_at") val updatedAt: String = ""
)