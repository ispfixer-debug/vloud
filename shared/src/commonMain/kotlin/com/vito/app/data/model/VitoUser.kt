package com.vito.app.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class VitoUser(
    @SerialName("id")
    val id: String = "",
    @SerialName("username")
    val username: String = "",
    @SerialName("display_name")
    val displayName: String = "",
    @SerialName("pin_hash")
    val pinHash: String = "",
    @SerialName("referral_driver_id")
    val referralDriverId: String? = null,
    @SerialName("wallet_balance")
    val walletBalance: Double = 0.0,
    @SerialName("stripe_customer_id")
    val stripeCustomerId: String? = null,
    @SerialName("is_suspended")
    val isSuspended: Boolean = false,
    @SerialName("language")
    val language: String = "en",
    @SerialName("created_at")
    val createdAt: String = "",
    @SerialName("updated_at")
    val updatedAt: String = ""
)

@Serializable
data class VitoUserInput(
    @SerialName("username")
    val username: String,
    @SerialName("display_name")
    val displayName: String,
    @SerialName("pin_hash")
    val pinHash: String,
    @SerialName("referral_driver_id")
    val referralDriverId: String? = null,
    @SerialName("language")
    val language: String = "en"
)

@Serializable
data class VitoUserUpdate(
    @SerialName("display_name")
    val displayName: String? = null,
    @SerialName("pin_hash")
    val pinHash: String? = null,
    @SerialName("referral_driver_id")
    val referralDriverId: String? = null,
    @SerialName("wallet_balance")
    val walletBalance: Double? = null,
    @SerialName("stripe_customer_id")
    val stripeCustomerId: String? = null,
    @SerialName("is_suspended")
    val isSuspended: Boolean? = null,
    @SerialName("language")
    val language: String? = null,
    @SerialName("updated_at")
    val updatedAt: String = ""
)