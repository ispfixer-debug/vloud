package com.vito.app.viewModel

import androidx.lifecycle.ViewModel
import com.vito.app.data.model.VitoUser
import com.vito.app.data.supabase.AuthRepository
import kotlinx.coroutines.flow.MutableStateFlow

/**
 * Ride Booking ViewModel - Create/manage rides
 */
class RideBookingViewModel(
    private val authRepository: AuthRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(RideBookingUiState())
    
    fun updatePickupLocation(lat: Double, lng: Double, address: String) {
        _uiState.value = _uiState.value.copy(
            pickupLat = lat,
            pickupLng = lng,
            pickupAddress = address
        )
    }

    fun updateDropoffLocation(lat: Double, lng: Double, address: String) {
        _uiState.value = _uiState.value.copy(
            dropoffLat = lat,
            dropoffLng = lng,
            dropoffAddress = address
        )
    }

    fun selectVehicleType(type: String) {
        _uiState.value = _uiState.value.copy(selectedVehicleType = type)
    }
}

data class RideBookingUiState(
    val pickupLat: Double = 0.0,
    val pickupLng: Double = 0.0,
    val pickupAddress: String = "",
    val dropoffLat: Double = 0.0,
    val dropoffLng: Double = 0.0,
    val dropoffAddress: String = "",
    val selectedVehicleType: String = "STANDARD",
    val estimatedFare: Double = 0.0,
    val estimatedTime: Int = 0
)