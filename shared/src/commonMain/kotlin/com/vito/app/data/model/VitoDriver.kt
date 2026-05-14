package com.vito.app.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class VitoDriver(
    @SerialName("id")
    val id: String = "",
    @SerialName("username")
    val username: String = "",
    @SerialName("pin_hash")
    val pinHash: String = "",
    @SerialName("plate_number")
    val plateNumber: String = "",
    @SerialName("car_photo_url")
    val carPhotoUrl: String? = null,
    @SerialName("car_photo_approved")
    val carPhotoApproved: Boolean = false,
    @SerialName("is_online")
    val isOnline: Boolean = false,
    @SerialName("service_ride")
    val serviceRide: Boolean = true,
    @SerialName("service_send")
    val serviceSend: Boolean = false,
    @SerialName("service_mart")
    val serviceMart: Boolean = false,
    @SerialName("current_lat")
    val currentLat: Double? = null,
    @SerialName("current_lng")
    val currentLng: Double? = null,
    @SerialName("rating")
    val rating: Double = 5.0,
    @SerialName("total_ratings")
    val totalRatings: Int = 0,
    @SerialName("earnings_total")
    val earningsTotal: Double = 0.0,
    @SerialName("is_suspended")
    val isSuspended: Boolean = false,
    @SerialName("stripe_account_id")
    val stripeAccountId: String? = null,
    @SerialName("created_at")
    val createdAt: String = "",
    @SerialName("updated_at")
    val updatedAt: String = ""
)

@Serializable
data class VitoDriverInput(
    @SerialName("username")
    val username: String,
    @SerialName("pin_hash")
    val pinHash: String,
    @SerialName("plate_number")
    val plateNumber: String,
    @SerialName("car_photo_url")
    val carPhotoUrl: String? = null,
    @SerialName("service_ride")
    val serviceRide: Boolean = true,
    @SerialName("service_send")
    val serviceSend: Boolean = false,
    @SerialName("service_mart")
    val serviceMart: Boolean = false
)

@Serializable
data class VitoDriverUpdate(
    @SerialName("username")
    val username: String? = null,
    @SerialName("pin_hash")
    val pinHash: String? = null,
    @SerialName("plate_number")
    val plateNumber: String? = null,
    @SerialName("car_photo_url")
    val carPhotoUrl: String? = null,
    @SerialName("car_photo_approved")
    val carPhotoApproved: Boolean? = null,
    @SerialName("is_online")
    val isOnline: Boolean? = null,
    @SerialName("service_ride")
    val serviceRide: Boolean? = null,
    @SerialName("service_send")
    val serviceSend: Boolean? = null,
    @SerialName("service_mart")
    val serviceMart: Boolean? = null,
    @SerialName("current_lat")
    val currentLat: Double? = null,
    @SerialName("current_lng")
    val currentLng: Double? = null,
    @SerialName("rating")
    val rating: Double? = null,
    @SerialName("total_ratings")
    val totalRatings: Int? = null,
    @SerialName("earnings_total")
    val earningsTotal: Double? = null,
    @SerialName("is_suspended")
    val isSuspended: Boolean? = null,
    @SerialName("stripe_account_id")
    val stripeAccountId: String? = null,
    @SerialName("updated_at")
    val updatedAt: String = ""
)

@Serializable
data class DriverLocation(
    @SerialName("current_lat")
    val currentLat: Double,
    @SerialName("current_lng")
    val currentLng: Double
)