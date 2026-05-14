package com.vito.app.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
enum class ActivityType {
    @SerialName("ride_completed") RIDE_COMPLETED,
    @SerialName("send_delivered") SEND_DELIVERED,
    @SerialName("order_delivered") ORDER_DELIVERED,
    @SerialName("wallet_topup") WALLET_TOPUP,
    @SerialName("referral_bonus") REFERRAL_BONUS,
    @SerialName("payout_received") PAYOUT_RECEIVED,
    @SerialName("driver_started") DRIVER_STARTED,
    @SerialName("driver_ended") DRIVER_ENDED
}

@Serializable
data class ActivityItem(
    @SerialName("id") val id: String = "",
    @SerialName("user_id") val userId: String = "",
    @SerialName("type") val type: ActivityType = ActivityType.RIDE_COMPLETED,
    @SerialName("title") val title: String = "",
    @SerialName("subtitle") val subtitle: String = "",
    @SerialName("amount") val amount: Double? = null,  // For financial activities
    @SerialName("reference_id") val referenceId: String? = null,
    @SerialName("icon") val icon: String = "",
    @SerialName("timestamp") val timestamp: String = "",
    @SerialName("created_at") val createdAt: String = ""
)

@Serializable
data class VitoAdmin(
    @SerialName("id") val id: String = "",
    @SerialName("username") val username: String = "",
    @SerialName("pin_hash") val pinHash: String = "",
    @SerialName("display_name") val displayName: String = "",
    @SerialName("role") val role: String = "admin",
    @SerialName("is_active") val isActive: Boolean = true,
    @SerialName("created_at") val createdAt: String = "",
    @SerialName("updated_at") val updatedAt: String = ""
)

@Serializable
data class VitoAdminInput(
    @SerialName("username") val username: String,
    @SerialName("pin_hash") val pinHash: String,
    @SerialName("display_name") val displayName: String,
    @SerialName("role") val role: String = "admin"
)

@Serializable
data class SessionData(
    @SerialName("id") val id: String = "",
    @SerialName("user_type") val userType: String = "",  // "client", "driver", "admin"
    @SerialName("user_id") val userId: String = "",
    @SerialName("username") val username: String = "",
    @SerialName("display_name") val displayName: String = "",
    @SerialName("auth_token") val authToken: String = "",
    @SerialName("expires_at") val expiresAt: String = ""
)

@Serializable
data class DashboardStats(
    @SerialName("total_users") val totalUsers: Int = 0,
    @SerialName("total_drivers") val totalDrivers: Int = 0,
    @SerialName("active_drivers") val activeDrivers: Int = 0,
    @SerialName("rides_today") val ridesToday: Int = 0,
    @SerialName("revenue_today") val revenueToday: Double = 0.0,
    @SerialName("mart_orders_today") val martOrdersToday: Int = 0
)