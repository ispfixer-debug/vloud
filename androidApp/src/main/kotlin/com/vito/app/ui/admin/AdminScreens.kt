package com.vito.app.ui.admin

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

/**
 * Admin Dashboard - Main admin screen
 */
@Composable
fun AdminDashboardScreen(
    onNavigateToQr: () -> Unit,
    onNavigateToDrivers: () -> Unit,
    onNavigateToClients: () -> Unit,
    onNavigateToLiveOrders: () -> Unit,
    onNavigateToMart: () -> Unit,
    onNavigateToFinance: () -> Unit,
    onNavigateToAudit: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = "Admin",
            style = MaterialTheme.typography.headlineLarge
        )
        
        Spacer(modifier = Modifier.height(24.dp))
        
        // Stats cards
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            AdminStatCard(
                title = "Drivers",
                value = "0",
                modifier = Modifier.weight(1f)
            )
            AdminStatCard(
                title = "Clients", 
                value = "0",
                modifier = Modifier.weight(1f)
            )
        }
        
        Spacer(modifier = Modifier.height(24.dp))
        
        // Menu
        AdminMenuButton("QR Codes", onClick = onNavigateToQr)
        AdminMenuButton("Live Orders", onClick = onNavigateToLiveOrders)
        AdminMenuButton("Finance", onClick = onNavigateToFinance)
    }
}

@Composable
private fun AdminStatCard(
    title: String,
    value: String,
    modifier: Modifier = Modifier
) {
    Card(modifier = modifier) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Text(title, style = MaterialTheme.typography.bodySmall)
            Text(value, style = MaterialTheme.typography.headlineMedium)
        }
    }
}

@Composable
private fun AdminMenuButton(
    text: String,
    onClick: () -> Unit
) {
    Button(
        onClick = onClick,
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp)
    ) {
        Text(text)
    }
}

/**
 * Admin QR Management
 */
@Composable
fun AdminQrScreen(onBack: () -> Unit) {
    Column(modifier = Modifier.padding(16.dp)) {
        Text("QR Codes")
    }
}

/**
 * Admin Drivers Management
 */
@Composable
fun AdminDriversScreen(onBack: () -> Unit) {
    Column(modifier = Modifier.padding(16.dp)) {
        Text("Drivers")
    }
}

/**
 * Admin Clients Management  
 */
@Composable
fun AdminClientsScreen(onBack: () -> Unit) {
    Column(modifier = Modifier.padding(16.dp)) {
        Text("Clients")
    }
}

/**
 * Admin Live Orders
 */
@Composable
fun AdminLiveOrdersScreen(onBack: () -> Unit) {
    Column(modifier = Modifier.padding(16.dp)) {
        Text("Live Orders")
    }
}

/**
 * Admin Mart Management
 */
@Composable
fun AdminMartScreen(onBack: () -> Unit) {
    Column(modifier = Modifier.padding(16.dp)) {
        Text("Mart")
    }
}

/**
 * Admin Finance
 */
@Composable
fun AdminFinanceScreen(onBack: () -> Unit) {
    Column(modifier = Modifier.padding(16.dp)) {
        Text("Finance")
    }
}

/**
 * Admin Audit Log
 */
@Composable
fun AdminAuditScreen(onBack: () -> Unit) {
    Column(modifier = Modifier.padding(16.dp)) {
        Text("Audit")
    }
}