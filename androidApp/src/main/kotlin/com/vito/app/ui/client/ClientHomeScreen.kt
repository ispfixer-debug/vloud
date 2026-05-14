package com.vito.app.ui.client

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

/**
 * Client Home Screen - Main dashboard
 */
@Composable
fun ClientHomeScreen(
    onNavigateToBooking: () -> Unit,
    onNavigateToSend: () -> Unit,
    onNavigateToMart: () -> Unit,
    onNavigateToWallet: () -> Unit,
    onNavigateToActivity: () -> Unit,
    onNavigateToProfile: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = "VITO",
            style = MaterialTheme.typography.headlineLarge
        )
        
        Spacer(modifier = Modifier.height(24.dp))
        
        // Quick actions
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            QuickActionButton(
                text = "Ride",
                onClick = onNavigateToBooking,
                modifier = Modifier.weight(1f)
            )
            QuickActionButton(
                text = "Send",
                onClick = onNavigateToSend,
                modifier = Modifier.weight(1f)
            )
        }
        
        Spacer(modifier = Modifier.height(12.dp))
        
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            QuickActionButton(
                text = "Mart",
                onClick = onNavigateToMart,
                modifier = Modifier.weight(1f)
            )
            QuickActionButton(
                text = "Wallet",
                onClick = onNavigateToWallet,
                modifier = Modifier.weight(1f)
            )
        }
        
        Spacer(modifier = Modifier.height(24.dp))
        
        // Recent activity
        Text(
            text = "Recent Activity",
            style = MaterialTheme.typography.titleMedium
        )
    }
}

@Composable
private fun QuickActionButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Button(
        onClick = onClick,
        modifier = modifier.height(100.dp)
    ) {
        Text(text = text)
    }
}