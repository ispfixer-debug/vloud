package com.vito.app.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
enum class QrTokenType {
    @SerialName("client_referral") CLIENT_REFERRAL,
    @SerialName("driver_onboarding") DRIVER_ONBOARDING
}

@Serializable
data class QrToken(
    @SerialName("id") val id: String = "",
    @SerialName("token") val token: String = "",
    @SerialName("type") val type: QrTokenType = QrTokenType.CLIENT_REFERRAL,
    @SerialName("driver_id") val driverId: String? = null,  // Only for client_referral
    @SerialName("uses_total") val usesTotal: Int = 1,
    @SerialName("uses_count") val usesCount: Int = 0,
    @SerialName("expires_at") val expiresAt: String = "",
    @SerialName("is_revoked") val isRevoked: Boolean = false,
    @SerialName("created_at") val createdAt: String = "",
    @SerialName("created_by") val createdBy: String = ""
)

@Serializable
data class QrTokenInput(
    @SerialName("type") val type: QrTokenType,
    @SerialName("driver_id") val driverId: String? = null,
    @SerialName("uses_total") val usesTotal: Int = 1
)

@Serializable
enum class TransactionType {
    @SerialName("topup") TOPUP,
    @SerialName("ride_payment") RIDE_PAYMENT,
    @SerialName("send_payment") SEND_PAYMENT,
    @SerialName("mart_payment") MART_PAYMENT,
    @SerialName("payout") PAYOUT,
    @SerialName("referral_bonus") REFERRAL_BONUS
}

@Serializable
data class WalletTransaction(
    @SerialName("id") val id: String = "",
    @SerialName("user_id") val userId: String = "",
    @SerialName("type") val type: TransactionType = TransactionType.TOPUP,
    @SerialName("amount") val amount: Double = 0.0,
    @SerialName("balance_before") val balanceBefore: Double = 0.0,
    @SerialName("balance_after") val balanceAfter: Double = 0.0,
    @SerialName("reference_id") val referenceId: String? = null,  // ride_id, send_id, order_id
    @SerialName("description") val description: String = "",
    @SerialName("stripe_payment_id") val stripePaymentId: String? = null,
    @SerialName("created_at") val createdAt: String = ""
)

@Serializable
data class WalletTransactionInput(
    @SerialName("user_id") val userId: String,
    @SerialName("type") val type: TransactionType,
    @SerialName("amount") val amount: Double,
    @SerialName("reference_id") val referenceId: String? = null,
    @SerialName("description") val description: String = ""
)

@Serializable
enum class PayoutStatus {
    @SerialName("pending") PENDING,
    @SerialName("processing") PROCESSING,
    @SerialName("completed") COMPLETED,
    @SerialName("failed") FAILED
}

@Serializable
data class PayoutRequest(
    @SerialName("id") val id: String = "",
    @SerialName("driver_id") val driverId: String = "",
    @SerialName("amount") val amount: Double = 0.0,
    @SerialName("stripe_transfer_id") val stripeTransferId: String? = null,
    @SerialName("status") val status: PayoutStatus = PayoutStatus.PENDING,
    @SerialName("requested_at") val requestedAt: String = "",
    @SerialName("processed_at") val processedAt: String? = null,
    @SerialName("failed_reason") val failedReason: String? = null,
    @SerialName("created_at") val createdAt: String = "",
    @SerialName("updated_at") val updatedAt: String = ""
)

@Serializable
data class PayoutRequestInput(
    @SerialName("driver_id") val driverId: String,
    @SerialName("amount") val amount: Double
)