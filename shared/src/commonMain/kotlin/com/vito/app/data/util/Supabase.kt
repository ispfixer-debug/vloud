package com.vito.app.data.util

import com.vito.app.global.util.loggerError

/**
 * Supabase helper functions stub
 * Replace with actual implementation when credentials are configured
 */

/**
 * Generic Supabase operation wrapper for error handling
 * TODO: Implement with actual Supabase client
 */
suspend inline fun <reified T : Any> supabase(
    crossinline operation: suspend () -> T?,
): T? {
    return try {
        operation()
    } catch (e: Exception) {
        loggerError("supabase", e)
        null
    }
}

/**
 * Generic Supabase operation wrapper with failure callback
 * TODO: Implement with actual Supabase client
 */
suspend inline fun <reified T : Any> supabase(
    crossinline operation: suspend () -> T?,
    failed: (String) -> Unit,
): T? {
    return try {
        operation()
    } catch (e: Exception) {
        failed(e.toString())
        loggerError("supabase", e)
        null
    }
}
