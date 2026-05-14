package com.vito.app.ui.client

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

/**
 * Ride Booking Screen - Request a ride
 */
@Composable
fun RideBookingScreen(
    onBack: () -> Unit,
    onRideRequested: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = "Book a Ride",
            style = MaterialTheme.typography.headlineMedium
        )
        
        Spacer(modifier = Modifier.height(24.dp))
        
        OutlinedTextField(
            value = "",
            onValueChange = { },
            label = { Text("Pickup Location") },
            modifier = Modifier.fillMaxWidth()
        )
        
        Spacer(modifier = Modifier.height(16.dp))
        
        OutlinedTextField(
            value = "",
            onValueChange = { },
            label = { Text("Dropoff Location") },
            modifier = Modifier.fillMaxWidth()
        )
        
        Spacer(modifier = Modifier.height(24.dp))
        
        Button(
            onClick = onRideRequested,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Request Ride")
        }
    }
}

/**
 * Active Ride Screen - Track ride
 */
@Composable
fun ActiveRideScreen(
    onBack: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = "Your Ride",
            style = MaterialTheme.typography.headlineMedium
        )
        
        Spacer(modifier = Modifier.height(24.dp))
        
        Card(
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(
                modifier = Modifier.padding(16.dp)
            ) {
                Text("Driver arriving in 5 min")
                Text("Toyota Vios • ABC 1234")
            }
        }
    }
}

/**
 * Send Screen - Send package
 */
@Composable
fun SendScreen(onBack: () -> Unit) {
    Column(modifier = Modifier.padding(16.dp)) {
        Text("Send Package")
    }
}

/**
 * Mart Screen - Quick Mart
 */
@Composable
fun MartScreen(onBack: () -> Unit) {
    Column(modifier = Modifier.padding(16.dp)) {
        Text("Quick Mart")
    }
}

/**
 * Wallet Screen
 */
@Composable
fun WalletScreen(onBack: () -> Unit) {
    Column(modifier = Modifier.padding(16.dp)) {
        Text("Wallet")
    }
}

/**
 * Activity Screen - History
 */
@Composable
fun ActivityScreen(onBack: () -> Unit) {
    Column(modifier = Modifier.padding(16.dp)) {
        Text("Activity")
    }
}

/**
 * Client Profile Screen
 */
@Composable
fun ClientProfileScreen(onBack: () -> Unit) {
    Column(modifier = Modifier.padding(16.dp)) {
        Text("Profile")
    }
}