package com.vito.app.ui.navigation

/**
 * Navigation routes for VITO app
 */
object Routes {
    // Auth
    const val ONBOARDING = "onboarding"
    const val REGISTER = "register"
    const val LOGIN = "login"
    
    // Client
    const val CLIENT_HOME = "client_home"
    const val RIDE_BOOKING = "ride_booking"
    const val ACTIVE_RIDE = "active_ride"
    const val SEND = "send"
    const val MART = "mart"
    const val WALLET = "wallet"
    const val ACTIVITY = "activity"
    const val PROFILE = "profile"
    
    // Driver
    const val DRIVER_HOME = "driver_home"
    const val ACTIVE_JOB = "active_job"
    const val EARNINGS = "earnings"
    const val QR_SCAN = "qr_scan"
    const val DRIVER_PROFILE = "driver_profile"
    
    // Admin
    const val ADMIN_DASHBOARD = "admin_dashboard"
    const val ADMIN_QR = "admin_qr"
    const val ADMIN_DRIVERS = "admin_drivers"
    const val ADMIN_CLIENTS = "admin_clients"
    const val ADMIN_LIVE_ORDERS = "admin_live_orders"
    const val ADMIN_MART = "admin_mart"
    const val ADMIN_FINANCE = "admin_finance"
    const val ADMIN_AUDIT = "admin_audit"
}