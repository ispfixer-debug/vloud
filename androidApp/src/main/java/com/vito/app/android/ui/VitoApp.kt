package com.vito.app.android.ui

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.vito.app.android.ui.screens.client.ClientHomeScreen
import com.vito.app.android.ui.screens.client.RideBookingScreen

/**
 * VITO Main Application composable
 */
@Composable
fun VitoApp() {
    val navController = rememberNavController()
    
    Scaffold { paddingValues ->
        NavHost(
            navController = navController,
            startDestination = "client_home",
            modifier = Modifier.padding(paddingValues)
        ) {
            composable("client_home") {
                ClientHomeScreen(
                    onBookRide = {
                        navController.navigate("ride_booking")
                    }
                )
            }
            
            composable("ride_booking") {
                RideBookingScreen(
                    onBack = {
                        navController.popBackStack()
                    },
                    onRideRequested = {
                        // Handle ride request
                    }
                )
            }
        }
    }
}