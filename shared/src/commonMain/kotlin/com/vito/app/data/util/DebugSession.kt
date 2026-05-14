package com.vito.app.data.util

import com.vito.app.data.model.VitoUser
import com.vito.app.data.model.VitoDriver

/**
 * Utility for creating debug/mock sessions in debug builds.
 * This enables bypass of authentication for testing.
 */
object DebugSession {
    
    // Debug user for Client flavor
    fun getDebugUser(): VitoUser {
        return VitoUser(
            id = "debug-client-001",
            username = "debug_client",
            displayName = "Debug Client",
            walletBalance = 99.99,
            language = "en"
        )
    }
    
    // Debug driver for Driver flavor
    fun getDebugDriver(): VitoDriver {
        return VitoDriver(
            id = "debug-driver-001",
            username = "debug_driver",
            plateNumber = "ABC-123",
            isOnline = true,
            serviceRide = true,
            serviceSend = false,
            serviceMart = false,
            currentLat = 25.7617,
            currentLng = -80.1918,
            rating = 5.0,
            totalRatings = 0,
            earningsTotal = 0.0
        )
    }
    
    // Mock rides for Client debug
    fun getMockRides(): List<com.vito.app.data.model.VitoRide> {
        return listOf(
            com.vito.app.data.model.VitoRide(
                id = "mock-ride-001",
                clientId = "debug-client-001",
                pickupLat = 25.7617,
                pickupLng = -80.1918,
                pickupAddress = "Miami, FL",
                destinationLat = 25.7780,
                destinationLng = -80.1870,
                destinationAddress = "Brickell Ave, Miami, FL",
                fareAmount = 12.50,
                distanceKm = 2.5f.toDouble(),
                status = com.vito.app.data.model.RideStatus.COMPLETED,
                requestedAt = "2024-01-10T10:00:00Z",
                completedAt = "2024-01-10T10:15:00Z"
            ),
            com.vito.app.data.model.VitoRide(
                id = "mock-ride-002",
                clientId = "debug-client-001",
                pickupLat = 25.7780,
                pickupLng = -80.1870,
                pickupAddress = "Brickell Ave, Miami, FL",
                destinationLat = 25.7900,
                destinationLng = -80.1800,
                destinationAddress = "Downtown Miami, FL",
                fareAmount = 8.75,
                distanceKm = 1.8,
                status = com.vito.app.data.model.RideStatus.COMPLETED,
                requestedAt = "2024-01-09T14:30:00Z",
                completedAt = "2024-01-09T14:45:00Z"
            ),
            com.vito.app.data.model.VitoRide(
                id = "mock-ride-003",
                clientId = "debug-client-001",
                pickupLat = 25.7900,
                pickupLng = -80.1800,
                pickupAddress = "Downtown Miami, FL",
                destinationLat = 25.7650,
                destinationLng = -80.1950,
                destinationAddress = "Wynwood, Miami, FL",
                fareAmount = 15.25,
                distanceKm = 3.2,
                status = com.vito.app.data.model.RideStatus.COMPLETED,
                requestedAt = "2024-01-08T18:00:00Z",
                completedAt = "2024-01-08T18:25:00Z"
            )
        )
    }
}