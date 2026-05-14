package com.vito.app.service

import android.app.Service
import android.content.Intent
import android.os.IBinder

/**
 * Driver Location Service - Foreground service for location tracking
 */
class DriverLocationService : Service() {
    
    override fun onBind(intent: Intent?): IBinder? = null
    
    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        // Start foreground with notification
        return START_STICKY
    }
    
    override fun onDestroy() {
        super.onDestroy()
    }
}