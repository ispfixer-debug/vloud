package com.vito.app.data.util

/**
 * VITO Supabase Client Configuration
 * Production backend for VITO ride-sharing platform
 */
object VitoSupabaseClient {
    
    // Production Supabase credentials
    const val SUPABASE_URL = "https://qxyezswhknovainoxhfz.supabase.co"
    const val SUPABASE_ANON_KEY = "sb_publishable_GNXNYtbtQCQrlDoAEYT2Mw_PtvOiPEF"
    
    val isConfigured: Boolean
        get() = SUPABASE_URL.startsWith("https://") && SUPABASE_ANON_KEY.isNotEmpty()
    
    object Tables {
        const val USERS = "vito_users"
        const val DRIVERS = "vito_drivers"
        const val RIDES = "vito_rides"
        const val ADMINS = "vito_admins"
    }
    
    object Endpoints {
        const val REST_API = "/rest/v1"
        const val AUTH = "/auth/v1"
        const val REALTIME = "/realtime/v1"
    }
}