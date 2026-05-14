package com.vito.app.data.supabase

import io.github.jan.supabase.SupabaseClient
import io.github.jan.supabase.createSupabaseClient
import io.github.jan.supabase.gotrue.GoTrue
import io.github.jan.supabase.postgrest.Postgrest
import io.github.jan.supabase.realtime.Realtime
import io.github.jan.supabase.storage.Storage

/**
 * Supabase Client Provider
 * Provides a singleton SupabaseClient for all repositories
 */
object SupabaseProvider {

    private const val SUPABASE_URL = "https://qxyezswhknovainoxhfz.supabase.co"
    private const val SUPABASE_ANON_KEY = "sb_publishable_GNXNYtbtQCQrlDoAEYT2Mw_PtvOiPEF"

    val client: SupabaseClient by lazy {
        createSupabaseClient(
            supabaseUrl = SUPABASE_URL,
            supabaseKey = SUPABASE_ANON_KEY
        ) {
            install(GoTrue)
            install(Postgrest)
            install(Realtime)
            install(Storage)
        }
    }

    // Access to individual services
    val goTrue: GoTrue get() = client.gotrue
    val postgrest: Postgrest get() = client.postgrest
    val realtime: Realtime get() = client.realtime
    val storage: Storage get() = client.storage
}