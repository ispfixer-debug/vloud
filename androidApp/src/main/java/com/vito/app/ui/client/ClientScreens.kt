package com.vito.app.ui.client

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.vito.app.ui.theme.VitoBackground
import com.vito.app.ui.theme.VitoError
import com.vito.app.ui.theme.VitoGreen
import com.vito.app.ui.theme.VitoSurface

/**
 * Client Home Screen
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ClientHomeScreen(
    username: String,
    onNavigateToRide: () -> Unit,
    onNavigateToSend: () -> Unit,
    onNavigateToMart: () -> Unit,
    onNavigateToWallet: () -> Unit,
    onNavigateToProfile: () -> Unit,
    modifier: Modifier = Modifier
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Welcome, $username", color = Color.White) },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = VitoSurface),
                actions = {
                    IconButton(onClick = onNavigateToWallet) {
                        Icon(Icons.Default.AccountBalanceWallet, contentDescription = "Wallet", tint = VitoGreen)
                    }
                    IconButton(onClick = onNavigateToProfile) {
                        Icon(Icons.Default.Person, contentDescription = "Profile", tint = Color.White)
                    }
                }
            )
        },
        modifier = modifier.background(VitoBackground)
    ) { padding ->
        Column(
            modifier = Modifier.fillMaxSize().padding(padding).padding(16.dp)
        ) {
            WalletCard(balance = 25.50)
            
            Spacer(modifier = Modifier.height(24.dp))
            
            Text(text = "Services", color = Color.White, style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold)
            
            Spacer(modifier = Modifier.height(16.dp))
            
            LazyColumn(verticalArrangement = Arrangement.spacedBy(12.dp)) {
                item { ServiceCard(Icons.Default.DirectionsCar, "Ride", "Get a ride anywhere", onNavigateToRide) }
                item { ServiceCard(Icons.Default.LocalShipping, "Send", "Send packages", onNavigateToSend) }
                item { ServiceCard(Icons.Default.ShoppingCart, "Mart", "Quick delivery", onNavigateToMart) }
            }
        }
    }
}

@Composable
fun WalletCard(balance: Double) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = VitoSurface),
        shape = RoundedCornerShape(16.dp)
    ) {
        Column(modifier = Modifier.padding(20.dp)) {
            Text(text = "Wallet Balance", color = Color.Gray, style = MaterialTheme.typography.bodyMedium)
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = "$${String.format("%.2f", balance)}", color = VitoGreen, style = MaterialTheme.typography.headlineLarge, fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.height(16.dp))
            Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                Button(
                    onClick = { },
                    colors = ButtonDefaults.buttonColors(containerColor = VitoGreen, contentColor = Color.Black),
                    shape = RoundedCornerShape(8.dp)
                ) { Text("Top Up") }
                OutlinedButton(
                    onClick = { },
                    colors = ButtonDefaults.outlinedButtonColors(contentColor = Color.White),
                    shape = RoundedCornerShape(8.dp)
                ) { Text("History") }
            }
        }
    }
}

@Composable
fun ServiceCard(icon: ImageVector, title: String, subtitle: String, onClick: () -> Unit) {
    Card(
        modifier = Modifier.fillMaxWidth().clickable { onClick() },
        colors = CardDefaults.cardColors(containerColor = VitoSurface),
        shape = RoundedCornerShape(12.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth().padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier.size(48.dp).background(VitoGreen.copy(alpha = 0.2f), CircleShape),
                contentAlignment = Alignment.Center
            ) { Icon(icon, contentDescription = null, tint = VitoGreen) }
            Spacer(modifier = Modifier.width(16.dp))
            Column(modifier = Modifier.weight(1f)) {
                Text(text = title, color = Color.White, style = MaterialTheme.typography.titleMedium)
                Text(text = subtitle, color = Color.Gray, style = MaterialTheme.typography.bodyMedium)
            }
            Icon(Icons.Default.ChevronRight, contentDescription = null, tint = Color.Gray)
        }
    }
}

/**
 * Ride Booking Screen
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RideBookingScreen(
    onBookRide: (Double, Double, Double, Double) -> Unit,
    onSelectPickup: () -> Unit,
    onSelectDestination: () -> Unit,
    onCancel: () -> Unit,
    modifier: Modifier = Modifier
) {
    var pickupAddress by remember { mutableStateOf("") }
    var destAddress by remember { mutableStateOf("") }
    
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Request Ride", color = Color.White) },
                navigationIcon = {
                    IconButton(onClick = onCancel) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Back", tint = Color.White)
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = VitoSurface)
            )
        },
        modifier = modifier.background(VitoBackground)
    ) { padding ->
        Column(
            modifier = Modifier.fillMaxSize().padding(padding).padding(16.dp)
        ) {
            LocationCard(Icons.Default.MyLocation, "Pickup", pickupAddress, "Current Location", onSelectPickup)
            
            Box(modifier = Modifier.padding(start = 24.dp, top = 4.dp, bottom = 4.dp).width(2.dp).height(24.dp).background(VitoGreen))
            
            LocationCard(Icons.Default.Place, "Destination", destAddress, "Where to?", onSelectDestination)
            
            Spacer(modifier = Modifier.weight(1f))
            
            Button(
                onClick = { onBookRide(0.0, 0.0, 0.0, 0.0) },
                enabled = pickupAddress.isNotBlank() && destAddress.isNotBlank(),
                modifier = Modifier.fillMaxWidth().height(56.dp),
                colors = ButtonDefaults.buttonColors(containerColor = VitoGreen, contentColor = Color.Black),
                shape = RoundedCornerShape(12.dp)
            ) { Text("Request Ride", style = MaterialTheme.typography.titleMedium) }
        }
    }
}

@Composable
fun LocationCard(icon: ImageVector, label: String, address: String, placeholder: String, onClick: () -> Unit) {
    Card(
        modifier = Modifier.fillMaxWidth().clickable { onClick() },
        colors = CardDefaults.cardColors(containerColor = VitoSurface),
        shape = RoundedCornerShape(12.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth().padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(icon, contentDescription = null, tint = VitoGreen, modifier = Modifier.size(24.dp))
            Spacer(modifier = Modifier.width(12.dp))
            Column(modifier = Modifier.weight(1f)) {
                Text(label, color = Color.Gray, style = MaterialTheme.typography.bodySmall)
                Text(address.ifBlank { placeholder }, color = if (address.isBlank()) Color.Gray else Color.White, style = MaterialTheme.typography.bodyLarge)
            }
            Icon(Icons.Default.ChevronRight, contentDescription = null, tint = Color.Gray)
        }
    }
}

/**
 * Ride Status Screen
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RideStatusScreen(
    rideStatus: String,
    driverName: String?,
    etaMinutes: Int?,
    onCancelRide: () -> Unit,
    onContactDriver: () -> Unit,
    onCompleteRide: () -> Unit,
    modifier: Modifier = Modifier
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Ride Status", color = Color.White) },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = VitoSurface)
            )
        },
        modifier = modifier.background(VitoBackground)
    ) { padding ->
        Column(
            modifier = Modifier.fillMaxSize().padding(padding).padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(32.dp))
            CircularProgressIndicator(modifier = Modifier.size(80.dp), color = VitoGreen, strokeWidth = 6.dp)
            Spacer(modifier = Modifier.height(24.dp))
            Text(text = rideStatus.replace("_", " ").uppercase(), color = VitoGreen, style = MaterialTheme.typography.headlineSmall)
            
            if (etaMinutes != null) {
                Spacer(modifier = Modifier.height(8.dp))
                Text(text = "Arriving in $etaMinutes min", color = Color.Gray, style = MaterialTheme.typography.bodyLarge)
            }
            
            if (driverName != null) {
                Spacer(modifier = Modifier.height(32.dp))
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    colors = CardDefaults.cardColors(containerColor = VitoSurface),
                    shape = RoundedCornerShape(12.dp)
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth().padding(16.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Box(
                            modifier = Modifier.size(48.dp).background(VitoGreen.copy(alpha = 0.2f), CircleShape),
                            contentAlignment = Alignment.Center
                        ) { Icon(Icons.Default.Person, contentDescription = null, tint = VitoGreen) }
                        Spacer(modifier = Modifier.width(12.dp))
                        Column(modifier = Modifier.weight(1f)) {
                            Text(driverName, color = Color.White, style = MaterialTheme.typography.titleMedium)
                            Text("Your Driver", color = Color.Gray, style = MaterialTheme.typography.bodySmall)
                        }
                        IconButton(onClick = onContactDriver) {
                            Icon(Icons.Default.Phone, contentDescription = "Call", tint = VitoGreen)
                        }
                    }
                }
            }
            
            Spacer(modifier = Modifier.weight(1f))
            
            if (rideStatus == "completed") {
                Button(
                    onClick = onCompleteRide,
                    modifier = Modifier.fillMaxWidth().height(56.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = VitoGreen, contentColor = Color.Black),
                    shape = RoundedCornerShape(12.dp)
                ) { Text("Rate Ride") }
            } else if (rideStatus != "cancelled") {
                OutlinedButton(
                    onClick = onCancelRide,
                    modifier = Modifier.fillMaxWidth().height(56.dp),
                    colors = ButtonDefaults.outlinedButtonColors(contentColor = VitoError),
                    shape = RoundedCornerShape(12.dp)
                ) { Text("Cancel Ride") }
            }
        }
    }
}

/**
 * Wallet Screen
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WalletScreen(
    balance: Double,
    transactions: List<WalletTransactionItem>,
    onTopUp: () -> Unit,
    onCancel: () -> Unit,
    modifier: Modifier = Modifier
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Wallet", color = Color.White) },
                navigationIcon = {
                    IconButton(onClick = onCancel) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Back", tint = Color.White)
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = VitoSurface)
            )
        },
        modifier = modifier.background(VitoBackground)
    ) { padding ->
        Column(modifier = Modifier.fillMaxSize().padding(padding)) {
            Card(
                modifier = Modifier.fillMaxWidth().padding(16.dp),
                colors = CardDefaults.cardColors(containerColor = VitoGreen),
                shape = RoundedCornerShape(16.dp)
            ) {
                Column(modifier = Modifier.padding(24.dp), horizontalAlignment = Alignment.CenterHorizontally) {
                    Text("Balance", color = Color.Black.copy(alpha = 0.7f))
                    Text("$${String.format("%.2f", balance)}", color = Color.Black, style = MaterialTheme.typography.displayMedium)
                    Spacer(modifier = Modifier.height(16.dp))
                    Button(
                        onClick = onTopUp,
                        colors = ButtonDefaults.buttonColors(containerColor = Color.Black, contentColor = Color.White),
                        shape = RoundedCornerShape(8.dp)
                    ) { Text("Top Up") }
                }
            }
            
            Text("Transactions", color = Color.White, style = MaterialTheme.typography.titleMedium, modifier = Modifier.padding(16.dp))
            
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(horizontal = 16.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(transactions) { tx ->
                    TransactionItem(tx = tx)
                }
            }
        }
    }
}

data class WalletTransactionItem(val id: String, val type: String, val amount: Double, val description: String, val date: String)

@Composable
fun TransactionItem(tx: WalletTransactionItem) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = VitoSurface)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth().padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column {
                Text(tx.description, color = Color.White)
                Text(tx.date, color = Color.Gray, style = MaterialTheme.typography.bodySmall)
            }
            Text(
                "${if (tx.amount > 0) "+" else ""}$${tx.amount}",
                color = if (tx.amount > 0) VitoGreen else VitoError
            )
        }
    }
}

/**
 * Profile Screen
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileScreen(
    username: String,
    displayName: String,
    onLogout: () -> Unit,
    onCancel: () -> Unit,
    modifier: Modifier = Modifier
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Profile", color = Color.White) },
                navigationIcon = {
                    IconButton(onClick = onCancel) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Back", tint = Color.White)
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = VitoSurface)
            )
        },
        modifier = modifier.background(VitoBackground)
    ) { padding ->
        Column(
            modifier = Modifier.fillMaxSize().padding(padding).padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(32.dp))
            Box(
                modifier = Modifier.size(100.dp).background(VitoGreen, CircleShape),
                contentAlignment = Alignment.Center
            ) { Text(displayName.take(1).uppercase(), color = Color.Black, style = MaterialTheme.typography.displayMedium) }
            Spacer(modifier = Modifier.height(16.dp))
            Text(displayName, color = Color.White, style = MaterialTheme.typography.headlineSmall)
            Text("@$username", color = Color.Gray, style = MaterialTheme.typography.bodyLarge)
            Spacer(modifier = Modifier.height(48.dp))
            
            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(containerColor = VitoSurface),
                shape = RoundedCornerShape(12.dp)
            ) {
                Column {
                    ProfileMenuItem(Icons.Default.Person, "Edit Profile", { })
                    Divider(color = Color.Gray.copy(alpha = 0.2f))
                    ProfileMenuItem(Icons.Default.History, "Ride History", { })
                    Divider(color = Color.Gray.copy(alpha = 0.2f))
                    ProfileMenuItem(Icons.Default.Help, "Help & Support", { })
                    Divider(color = Color.Gray.copy(alpha = 0.2f))
                    ProfileMenuItem(Icons.Default.Logout, "Log Out", onLogout, tint = VitoError)
                }
            }
        }
    }
}

@Composable
fun ProfileMenuItem(icon: ImageVector, title: String, onClick: () -> Unit, tint: Color = Color.White) {
    Row(
        modifier = Modifier.fillMaxWidth().clickable { onClick() }.padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(icon, contentDescription = null, tint = tint)
        Spacer(modifier = Modifier.width(16.dp))
        Text(title, color = tint, style = MaterialTheme.typography.bodyLarge, modifier = Modifier.weight(1f))
        Icon(Icons.Default.ChevronRight, contentDescription = null, tint = Color.Gray)
    }
}