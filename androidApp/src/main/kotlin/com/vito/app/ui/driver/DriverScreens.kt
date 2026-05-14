package com.vito.app.ui.driver

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

/**
 * Driver Home Screen - Main driver dashboard
 */
@Composable
fun DriverHomeScreen(
    onNavigateToActiveJob: () -> Unit,
    onNavigateToEarnings: () -> Unit,
    onNavigateToQr: () -> Unit,
    onNavigateToProfile: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = "Driver",
            style = MaterialTheme.typography.headlineLarge
        )
        
        Spacer(modifier = Modifier.height(24.dp))
        
        Button(
            onClick = { /* Toggle online */ },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Go Online")
        }
        
        Spacer(modifier = Modifier.height(24.dp))
        
        Button(
            onClick = onNavigateToEarnings,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Earnings")
        }
        
        Spacer(modifier = Modifier.height(12.dp))
        
        Button(
            onClick = onNavigateToQr,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Scan QR")
        }
    }
}

/**
 * Active Job Screen - Current ride
 */
@Composable
fun ActiveJobScreen(onBack: () -> Unit) {
    Column(modifier = Modifier.padding(16.dp)) {
        Text("Active Job")
    }
}

/**
 * Driver Earnings Screen
 */
@Composable
fun DriverEarningsScreen(onBack: () -> Unit) {
    Column(modifier = Modifier.padding(16.dp)) {
        Text("Earnings")
    }
}

/**
 * Driver QR Scanner Screen
 */
@Composable
fun DriverQrScreen(onBack: () -> Unit) {
    Column(modifier = Modifier.padding(16.dp)) {
        Text("Scan QR")
    }
}

/**
 * Driver Profile Screen
 */
@Composable
fun DriverProfileScreen(onBack: () -> Unit) {
    Column(modifier = Modifier.padding(16.dp)) {
        Text("Profile")
    }
}