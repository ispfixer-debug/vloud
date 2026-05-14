package com.vito.app.ui.driver

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
 * Driver Home Screen
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DriverHomeScreen(
    username: String,
    isOnline: Boolean,
    earnings: Double,
    rating: Double,
    totalRides: Int,
    onToggleOnline: () -> Unit,
    onNavigateToRides: () -> Unit,
    onNavigateToEarnings: () -> Unit,
    onNavigateToProfile: () -> Unit,
    modifier: Modifier = Modifier
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Driver Panel", color = Color.White) },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = VitoSurface),
                actions = {
                    Text(
                        text = if (isOnline) "ONLINE" else "OFFLINE",
                        color = if (isOnline) VitoGreen else Color.Gray,
                        style = MaterialTheme.typography.labelMedium,
                        modifier = Modifier.padding(end = 8.dp)
                    )
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
            DriverStatusCard(username, isOnline, rating, totalRides, onToggleOnline)
            Spacer(modifier = Modifier.height(24.dp))
            EarningsCard(earnings, onNavigateToEarnings)
            Spacer(modifier = Modifier.weight(1f))
            Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
                Button(
                    onClick = onNavigateToRides,
                    modifier = Modifier.fillMaxWidth().height(56.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = VitoGreen, contentColor = Color.Black),
                    shape = RoundedCornerShape(12.dp)
                ) {
                    Icon(Icons.Default.DirectionsCar, contentDescription = null)
                    Spacer(modifier = Modifier.width(8.dp))
                    Text("View Rides", style = MaterialTheme.typography.titleMedium)
                }
            }
        }
    }
}

@Composable
fun DriverStatusCard(
    username: String,
    isOnline: Boolean,
    rating: Double,
    totalRides: Int,
    onToggleOnline: () -> Unit
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = VitoSurface),
        shape = RoundedCornerShape(16.dp)
    ) {
        Column(modifier = Modifier.padding(20.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column {
                    Text(username, color = Color.White, style = MaterialTheme.typography.titleLarge)
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            if (isOnline) Icons.Default.CheckCircle else Icons.Default.Cancel,
                            contentDescription = null,
                            tint = if (isOnline) VitoGreen else Color.Gray,
                            modifier = Modifier.size(16.dp)
                        )
                        Spacer(modifier = Modifier.width(4.dp))
                        Text(if (isOnline) "Online" else "Offline", color = if (isOnline) VitoGreen else Color.Gray)
                    }
                }
                Switch(
                    checked = isOnline,
                    onCheckedChange = { onToggleOnline() },
                    colors = SwitchDefaults.colors(
                        checkedThumbColor = VitoGreen,
                        checkedTrackColor = VitoGreen.copy(alpha = 0.3f),
                        uncheckedThumbColor = Color.Gray,
                        uncheckedTrackColor = Color.Gray.copy(alpha = 0.3f)
                    )
                )
            }
            Spacer(modifier = Modifier.height(16.dp))
            Divider(color = Color.Gray.copy(alpha = 0.2f))
            Spacer(modifier = Modifier.height(16.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text("$rating", color = Color.White, style = MaterialTheme.typography.titleLarge, fontWeight = FontWeight.Bold)
                    Text("Rating", color = Color.Gray, style = MaterialTheme.typography.bodySmall)
                }
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text("$totalRides", color = Color.White, style = MaterialTheme.typography.titleLarge, fontWeight = FontWeight.Bold)
                    Text("Rides", color = Color.Gray, style = MaterialTheme.typography.bodySmall)
                }
            }
        }
    }
}

@Composable
fun EarningsCard(earnings: Double, onViewDetails: () -> Unit) {
    Card(
        modifier = Modifier.fillMaxWidth().clickable { onViewDetails() },
        colors = CardDefaults.cardColors(containerColor = VitoSurface),
        shape = RoundedCornerShape(16.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth().padding(20.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column {
                Text("Today's Earnings", color = Color.Gray, style = MaterialTheme.typography.bodyMedium)
                Text("$${String.format("%.2f", earnings)}", color = VitoGreen, style = MaterialTheme.typography.headlineMedium, fontWeight = FontWeight.Bold)
            }
            Icon(Icons.Default.ChevronRight, contentDescription = "View", tint = Color.Gray)
        }
    }
}

/**
 * Available Rides Screen
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AvailableRidesScreen(
    rides: List<AvailableRideItem>,
    onAcceptRide: (String) -> Unit,
    onCancel: () -> Unit,
    modifier: Modifier = Modifier
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Available Rides", color = Color.White) },
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
        if (rides.isEmpty()) {
            Box(modifier = Modifier.fillMaxSize().padding(padding), contentAlignment = Alignment.Center) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Icon(Icons.Default.DirectionsCar, contentDescription = null, tint = Color.Gray, modifier = Modifier.size(64.dp))
                    Spacer(modifier = Modifier.height(16.dp))
                    Text("No rides available", color = Color.Gray, style = MaterialTheme.typography.bodyLarge)
                }
            }
        } else {
            LazyColumn(
                modifier = Modifier.fillMaxSize().padding(padding),
                contentPadding = PaddingValues(16.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                items(rides) { ride ->
                    AvailableRideCard(ride = ride, onAccept = { onAcceptRide(ride.id) })
                }
            }
        }
    }
}

data class AvailableRideItem(val id: String, val pickupAddress: String, val destAddress: String, val distance: Double, val fare: Double, val riderName: String)

@Composable
fun AvailableRideCard(ride: AvailableRideItem, onAccept: () -> Unit) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = VitoSurface),
        shape = RoundedCornerShape(12.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(ride.riderName, color = Color.White, style = MaterialTheme.typography.titleMedium)
                Text("$${ride.fare}", color = VitoGreen, style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold)
            }
            Spacer(modifier = Modifier.height(12.dp))
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(Icons.Default.MyLocation, contentDescription = null, tint = VitoGreen, modifier = Modifier.size(16.dp))
                Spacer(modifier = Modifier.width(8.dp))
                Text(ride.pickupAddress, color = Color.White, style = MaterialTheme.typography.bodyMedium)
            }
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(Icons.Default.Place, contentDescription = null, tint = Color.Gray, modifier = Modifier.size(16.dp))
                Spacer(modifier = Modifier.width(8.dp))
                Text(ride.destAddress, color = Color.White, style = MaterialTheme.typography.bodyMedium)
            }
            Spacer(modifier = Modifier.height(12.dp))
            Text("${ride.distance} km", color = Color.Gray, style = MaterialTheme.typography.bodySmall)
            Spacer(modifier = Modifier.height(12.dp))
            Button(
                onClick = onAccept,
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(containerColor = VitoGreen, contentColor = Color.Black),
                shape = RoundedCornerShape(8.dp)
            ) { Text("Accept Ride") }
        }
    }
}

/**
 * Active Ride Screen (Driver)
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DriverActiveRideScreen(
    rideStatus: String,
    pickupAddress: String,
    destAddress: String,
    riderName: String,
    riderPhone: String?,
    etaMinutes: Int?,
    onArrived: () -> Unit,
    onStartRide: () -> Unit,
    onCompleteRide: () -> Unit,
    onCancel: () -> Unit,
    modifier: Modifier = Modifier
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Current Ride", color = Color.White) },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = VitoSurface)
            )
        },
        modifier = modifier.background(VitoBackground)
    ) { padding ->
        Column(
            modifier = Modifier.fillMaxSize().padding(padding).padding(16.dp)
        ) {
            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(containerColor = VitoGreen.copy(alpha = 0.1f)),
                shape = RoundedCornerShape(12.dp)
            ) {
                Column(modifier = Modifier.padding(16.dp), horizontalAlignment = Alignment.CenterHorizontally) {
                    CircularProgressIndicator(modifier = Modifier.size(48.dp), color = VitoGreen, strokeWidth = 4.dp)
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(rideStatus.uppercase().replace("_", " "), color = VitoGreen, style = MaterialTheme.typography.titleMedium)
                    if (etaMinutes != null) {
                        Text("$etaMinutes min away", color = Color.Gray, style = MaterialTheme.typography.bodySmall)
                    }
                }
            }
            Spacer(modifier = Modifier.height(24.dp))
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
                        Text(riderName, color = Color.White, style = MaterialTheme.typography.titleMedium)
                        if (riderPhone != null) Text(riderPhone, color = Color.Gray, style = MaterialTheme.typography.bodySmall)
                    }
                    if (riderPhone != null) {
                        IconButton(onClick = { }) {
                            Icon(Icons.Default.Phone, contentDescription = "Call", tint = VitoGreen)
                        }
                    }
                }
            }
            Spacer(modifier = Modifier.height(16.dp))
            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(containerColor = VitoSurface),
                shape = RoundedCornerShape(12.dp)
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text("Pickup", color = Color.Gray, style = MaterialTheme.typography.bodySmall)
                    Text(pickupAddress, color = Color.White)
                    Spacer(modifier = Modifier.height(12.dp))
                    Text("Destination", color = Color.Gray, style = MaterialTheme.typography.bodySmall)
                    Text(destAddress, color = Color.White)
                }
            }
            Spacer(modifier = Modifier.weight(1f))
            when (rideStatus) {
                "arriving" -> Button(onClick = onArrived, modifier = Modifier.fillMaxWidth().height(56.dp), colors = ButtonDefaults.buttonColors(containerColor = VitoGreen, contentColor = Color.Black), shape = RoundedCornerShape(12.dp)) { Text("Arrived") }
                "in_progress" -> Button(onClick = onStartRide, modifier = Modifier.fillMaxWidth().height(56.dp), colors = ButtonDefaults.buttonColors(containerColor = VitoGreen, contentColor = Color.Black), shape = RoundedCornerShape(12.dp)) { Text("Start Ride") }
                else -> OutlinedButton(onClick = onCancel, modifier = Modifier.fillMaxWidth().height(56.dp), colors = ButtonDefaults.outlinedButtonColors(contentColor = VitoError), shape = RoundedCornerShape(12.dp)) { Text("Cancel") }
            }
        }
    }
}

/**
 * Driver Profile Screen
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DriverProfileScreen(
    username: String,
    plateNumber: String,
    rating: Double,
    totalRides: Int,
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
            modifier = Modifier.fillMaxSize().padding(padding).padding(16.dp)
        ) {
            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(containerColor = VitoSurface),
                shape = RoundedCornerShape(16.dp)
            ) {
                Column(modifier = Modifier.padding(20.dp), horizontalAlignment = Alignment.CenterHorizontally) {
                    Box(modifier = Modifier.size(80.dp).background(VitoGreen, CircleShape), contentAlignment = Alignment.Center) {
                        Icon(Icons.Default.DirectionsCar, contentDescription = null, tint = Color.Black, modifier = Modifier.size(40.dp))
                    }
                    Spacer(modifier = Modifier.height(12.dp))
                    Text(username, color = Color.White, style = MaterialTheme.typography.titleLarge)
                    Text("Plate: $plateNumber", color = Color.Gray)
                    Spacer(modifier = Modifier.height(12.dp))
                    Text("$rating ★", color = VitoGreen, style = MaterialTheme.typography.titleMedium)
                    Text("$totalRides rides", color = Color.Gray)
                }
            }
            Spacer(modifier = Modifier.height(24.dp))
            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(containerColor = VitoSurface),
                shape = RoundedCornerShape(12.dp)
            ) {
                Column {
                    DriverMenuItem(Icons.Default.DirectionsCar, "My Vehicle", { })
                    Divider(color = Color.Gray.copy(alpha = 0.2f))
                    DriverMenuItem(Icons.Default.History, "Ride History", { })
                    Divider(color = Color.Gray.copy(alpha = 0.2f))
                    DriverMenuItem(Icons.Default.Help, "Help & Support", { })
                    Divider(color = Color.Gray.copy(alpha = 0.2f))
                    DriverMenuItem(Icons.Default.Logout, "Log Out", onLogout, tint = VitoError)
                }
            }
        }
    }
}

@Composable
fun DriverMenuItem(icon: ImageVector, title: String, onClick: () -> Unit, tint: Color = Color.White) {
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