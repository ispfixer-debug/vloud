package com.vito.app.data.util

object FareCalculator {
    // All amounts in USD (dollars, 2 decimal precision)
    
    private const val BASE_FARE = 3.50
    private const val RATE_PER_KM = 1.50
    private const val RATE_PER_MINUTE = 0.25
    private const val MINIMUM_FARE = 5.00
    
    fun calculateFare(distanceKm: Double, durationMinutes: Int): Double {
        val distanceFare = distanceKm * RATE_PER_KM
        val timeFare = durationMinutes * RATE_PER_MINUTE
        val totalFare = BASE_FARE + distanceFare + timeFare
        return if (totalFare < MINIMUM_FARE) MINIMUM_FARE else totalFare
    }
    
    fun calculateDistanceFare(distanceKm: Double): Double {
        val distanceFare = distanceKm * RATE_PER_KM
        val totalFare = BASE_FARE + distanceFare
        return if (totalFare < MINIMUM_FARE) MINIMUM_FARE else totalFare
    }
    
    fun formatFare(amount: Double): String {
        return String.format("$%.2f", amount)
    }
}