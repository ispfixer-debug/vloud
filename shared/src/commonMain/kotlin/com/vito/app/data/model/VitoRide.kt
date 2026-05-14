package com.vito.app.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
enum class RideStatus {
    @SerialName("pending") PENDING,
    @SerialName("accepted") ACCEPTED,
    @SerialName("arriving") ARRIVING,
    @SerialName("in_progress") IN_PROGRESS,
    @SerialName("completed") COMPLETED,
    @SerialName("cancelled") CANCELLED
}

@Serializable
enum class ServiceType {
    @SerialName("ride") RIDE,
    @SerialName("send") SEND,
    @SerialName("mart") MART
}

@Serializable
data class VitoRide(
    @SerialName("id")
    val id: String = "",
    @SerialName("client_id")
    val clientId: String = "",
    @SerialName("driver_id")
    val driverId: String? = null,
    @SerialName("service_type")
    val serviceType: ServiceType = ServiceType.RIDE,
    
    // Pickup location
    @SerialName("pickup_lat")
    val pickupLat: Double = 0.0,
    @SerialName("pickup_lng")
    val pickupLng: Double = 0.0,
    @SerialName("pickup_address")
    val pickupAddress: String = "",
    
    // Destination
    @SerialName("destination_lat")
    val destinationLat: Double = 0.0,
    @SerialName("destination_lng")
    val destinationLng: Double = 0.0,
    @SerialName("destination_address")
    val destinationAddress: String = "",
    
    // Fare
    @SerialName("fare_amount")
    val fareAmount: Double = 0.0,
    @SerialName("distance_km")
    val distanceKm: Double = 0.0,
    @SerialName("duration_minutes")
    val durationMinutes: Int? = null,
    
    // Status
    @SerialName("status")
    val status: RideStatus = RideStatus.PENDING,
    
    // Payment
    @SerialName("payment_method")
    val paymentMethod: String = "wallet",
    @SerialName("payment_intent_id")
    val paymentIntentId: String? = null,
    @SerialName("is_paid")
    val isPaid: Boolean = false,
    
    // Timestamps
    @SerialName("requested_at")
    val requestedAt: String = "",
    @SerialName("accepted_at")
    val acceptedAt: String? = null,
    @SerialName("started_at")
    val startedAt: String? = null,
    @SerialName("completed_at")
    val completedAt: String? = null,
    @SerialName("cancelled_at")
    val cancelledAt: String? = null,
    @SerialName("cancel_reason")
    val cancelReason: String? = null,
    
    // Ratings
    @SerialName("client_rating")
    val clientRating: Int? = null,
    @SerialName("client_rating_comment")
    val clientRatingComment: String? = null,
    @SerialName("driver_rating")
    val driverRating: Int? = null,
    
    @SerialName("created_at")
    val createdAt: String = "",
    @SerialName("updated_at")
    val updatedAt: String = ""
)

@Serializable
data class VitoRideInput(
    @SerialName("client_id")
    val clientId: String,
    @SerialName("service_type")
    val serviceType: ServiceType = ServiceType.RIDE,
    @SerialName("pickup_lat")
    val pickupLat: Double,
    @SerialName("pickup_lng")
    val pickupLng: Double,
    @SerialName("pickup_address")
    val pickupAddress: String,
    @SerialName("destination_lat")
    val destinationLat: Double,
    @SerialName("destination_lng")
    val destinationLng: Double,
    @SerialName("destination_address")
    val destinationAddress: String,
    @SerialName("fare_amount")
    val fareAmount: Double,
    @SerialName("distance_km")
    val distanceKm: Double,
    @SerialName("duration_minutes")
    val durationMinutes: Int? = null,
    @SerialName("payment_method")
    val paymentMethod: String = "wallet"
)

@Serializable
data class VitoRideUpdate(
    @SerialName("driver_id")
    val driverId: String? = null,
    @SerialName("status")
    val status: RideStatus? = null,
    @SerialName("fare_amount")
    val fareAmount: Double? = null,
    @SerialName("accepted_at")
    val acceptedAt: String? = null,
    @SerialName("started_at")
    val startedAt: String? = null,
    @SerialName("completed_at")
    val completedAt: String? = null,
    @SerialName("cancelled_at")
    val cancelledAt: String? = null,
    @SerialName("cancel_reason")
    val cancelReason: String? = null,
    @SerialName("payment_intent_id")
    val paymentIntentId: String? = null,
    @SerialName("is_paid")
    val isPaid: Boolean? = null,
    @SerialName("client_rating")
    val clientRating: Int? = null,
    @SerialName("client_rating_comment")
    val clientRatingComment: String? = null,
    @SerialName("driver_rating")
    val driverRating: Int? = null,
    @SerialName("updated_at")
    val updatedAt: String = ""
)