package com.vito.app.ui.admin

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
import com.vito.app.ui.theme.VitoWarning

/**
 * Admin Dashboard
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AdminDashboardScreen(
    onNavigateToUsers: () -> Unit,
    onNavigateToDrivers: () -> Unit,
    onNavigateToRides: () -> Unit,
    onNavigateToEarnings: () -> Unit,
    onNavigateToQrCodes: () -> Unit,
    onNavigateToSettings: () -> Unit,
    modifier: Modifier = Modifier
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Admin Panel", color = Color.White) },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = VitoSurface),
                actions = {
                    IconButton(onClick = { }) { Icon(Icons.Default.Notifications, contentDescription = "Notifications", tint = Color.White) }
                    IconButton(onClick = onNavigateToSettings) { Icon(Icons.Default.Settings, contentDescription = "Settings", tint = Color.White) }
                }
            )
        },
        modifier = modifier.background(VitoBackground)
    ) { padding ->
        LazyColumn(
            modifier = Modifier.fillMaxSize().padding(padding).padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            item { StatsOverviewSection() }
            item { Text("Management", color = Color.White, style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold) }
            item { AdminMenuCard(Icons.Default.People, "Users", "Manage client accounts", onNavigateToUsers) }
            item { AdminMenuCard(Icons.Default.DirectionsCar, "Drivers", "Manage driver accounts", onNavigateToDrivers) }
            item { AdminMenuCard(Icons.Default.LocalShipping, "Rides", "View ride history & analytics", onNavigateToRides) }
            item { AdminMenuCard(Icons.Default.AttachMoney, "Earnings", "Revenue & payouts", onNavigateToEarnings) }
            item { AdminMenuCard(Icons.Default.QrCode, "QR Codes", "Generate referral codes", onNavigateToQrCodes) }
        }
    }
}

@Composable
fun StatsOverviewSection() {
    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(12.dp)) {
        StatCard(title = "Users", value = "1,234", modifier = Modifier.weight(1f))
        StatCard(title = "Drivers", value = "567", modifier = Modifier.weight(1f))
    }
    Spacer(modifier = Modifier.height(12.dp))
    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(12.dp)) {
        StatCard(title = "Rides Today", value = "89", modifier = Modifier.weight(1f))
        StatCard(title = "Revenue", value = "$4.5K", modifier = Modifier.weight(1f))
    }
}

@Composable
fun StatCard(title: String, value: String, modifier: Modifier = Modifier) {
    Card(
        modifier = modifier,
        colors = CardDefaults.cardColors(containerColor = VitoSurface),
        shape = RoundedCornerShape(12.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp), horizontalAlignment = Alignment.CenterHorizontally) {
            Text(value, color = Color.White, style = MaterialTheme.typography.headlineSmall, fontWeight = FontWeight.Bold)
            Text(title, color = Color.Gray, style = MaterialTheme.typography.bodySmall)
        }
    }
}

@Composable
fun AdminMenuCard(icon: ImageVector, title: String, subtitle: String, onClick: () -> Unit) {
    Card(
        modifier = Modifier.fillMaxWidth().clickable { onClick() },
        colors = CardDefaults.cardColors(containerColor = VitoSurface),
        shape = RoundedCornerShape(12.dp)
    ) {
        Row(modifier = Modifier.fillMaxWidth().padding(16.dp), verticalAlignment = Alignment.CenterVertically) {
            Icon(icon, contentDescription = null, tint = VitoGreen, modifier = Modifier.size(32.dp))
            Spacer(modifier = Modifier.width(16.dp))
            Column(modifier = Modifier.weight(1f)) {
                Text(title, color = Color.White, style = MaterialTheme.typography.titleMedium)
                Text(subtitle, color = Color.Gray, style = MaterialTheme.typography.bodySmall)
            }
            Icon(Icons.Default.ChevronRight, contentDescription = null, tint = Color.Gray)
        }
    }
}

/**
 * Users Management Screen
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UsersManagementScreen(
    users: List<UserManagementItem>,
    onUserAction: (String, String) -> Unit,
    onCancel: () -> Unit,
    modifier: Modifier = Modifier
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Users", color = Color.White) },
                navigationIcon = {
                    IconButton(onClick = onCancel) { Icon(Icons.Default.ArrowBack, contentDescription = "Back", tint = Color.White) }
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = VitoSurface)
            )
        },
        modifier = modifier.background(VitoBackground)
    ) { padding ->
        LazyColumn(
            modifier = Modifier.fillMaxSize().padding(padding),
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(users) { user ->
                UserManagementCard(user = user, onAction = { action -> onUserAction(user.id, action) })
            }
        }
    }
}

data class UserManagementItem(val id: String, val username: String, val displayName: String, val status: String, val joinedAt: String)

@Composable
fun UserManagementCard(user: UserManagementItem, onAction: (String) -> Unit) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = VitoSurface)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth().padding(12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier.size(40.dp).background(VitoGreen.copy(alpha = 0.2f), CircleShape),
                contentAlignment = Alignment.Center
            ) { Text(user.displayName.take(1).uppercase(), color = VitoGreen) }
            Spacer(modifier = Modifier.width(12.dp))
            Column(modifier = Modifier.weight(1f)) {
                Text(user.displayName, color = Color.White, style = MaterialTheme.typography.bodyMedium)
                Text("@${user.username} • ${user.joinedAt}", color = Color.Gray, style = MaterialTheme.typography.bodySmall)
            }
            if (user.status == "active") {
                TextButton(onClick = { onAction("suspend") }) { Text("Suspend", color = VitoError) }
            }
        }
    }
}

/**
 * Drivers Management Screen
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DriversManagementScreen(
    drivers: List<DriverManagementItem>,
    onDriverAction: (String, String) -> Unit,
    onCancel: () -> Unit,
    modifier: Modifier = Modifier
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Drivers", color = Color.White) },
                navigationIcon = {
                    IconButton(onClick = onCancel) { Icon(Icons.Default.ArrowBack, contentDescription = "Back", tint = Color.White) }
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = VitoSurface)
            )
        },
        modifier = modifier.background(VitoBackground)
    ) { padding ->
        LazyColumn(
            modifier = Modifier.fillMaxSize().padding(padding),
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(drivers) { driver ->
                DriverManagementCard(driver = driver, onAction = { action -> onDriverAction(driver.id, action) })
            }
        }
    }
}

data class DriverManagementItem(val id: String, val username: String, val plateNumber: String, val rating: Double, val status: String, val totalRides: Int)

@Composable
fun DriverManagementCard(driver: DriverManagementItem, onAction: (String) -> Unit) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = VitoSurface)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth().padding(12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(modifier = Modifier.size(40.dp).background(VitoGreen, CircleShape), contentAlignment = Alignment.Center) {
                Icon(Icons.Default.DirectionsCar, contentDescription = null, tint = Color.Black, modifier = Modifier.size(20.dp))
            }
            Spacer(modifier = Modifier.width(12.dp))
            Column(modifier = Modifier.weight(1f)) {
                Text(driver.username, color = Color.White, style = MaterialTheme.typography.bodyMedium)
                Text("${driver.plateNumber} • ${driver.totalRides} rides • ${driver.rating}★", color = Color.Gray, style = MaterialTheme.typography.bodySmall)
            }
            if (driver.status == "pending") {
                Button(
                    onClick = { onAction("approve") },
                    colors = ButtonDefaults.buttonColors(containerColor = VitoGreen, contentColor = Color.Black),
                    shape = RoundedCornerShape(8.dp)
                ) { Text("Approve") }
            } else if (driver.status == "active") {
                TextButton(onClick = { onAction("suspend") }) { Text("Suspend", color = VitoError) }
            }
        }
    }
}

/**
 * Rides Analytics Screen
 */
@Composable
fun RidesAnalyticsScreen(
    totalRides: Int,
    totalRevenue: Double,
    averageFare: Double,
    topDrivers: List<DriverManagementItem>,
    onCancel: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.fillMaxSize().background(VitoBackground).padding(16.dp)
    ) {
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically) {
            Text("Rides Analytics", color = Color.White, style = MaterialTheme.typography.headlineSmall)
            IconButton(onClick = onCancel) { Icon(Icons.Default.Close, contentDescription = "Close", tint = Color.White) }
        }
        Spacer(modifier = Modifier.height(24.dp))
        Card(
            modifier = Modifier.fillMaxWidth(),
            colors = CardDefaults.cardColors(containerColor = VitoSurface),
            shape = RoundedCornerShape(12.dp)
        ) {
            Column(modifier = Modifier.padding(20.dp)) {
                Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                    Column {
                        Text("Total Rides", color = Color.Gray)
                        Text("$totalRides", color = Color.White, style = MaterialTheme.typography.headlineSmall)
                    }
                    Column(horizontalAlignment = Alignment.End) {
                        Text("Revenue", color = Color.Gray)
                        Text("$$totalRevenue", color = VitoGreen, style = MaterialTheme.typography.headlineSmall)
                    }
                }
                Spacer(modifier = Modifier.height(16.dp))
                Text("Average Fare: $averageFare", color = Color.White)
            }
        }
        Spacer(modifier = Modifier.height(24.dp))
        Text("Top Drivers", color = Color.White, style = MaterialTheme.typography.titleMedium)
        Spacer(modifier = Modifier.height(12.dp))
        LazyColumn(verticalArrangement = Arrangement.spacedBy(8.dp)) {
            items(topDrivers) { driver ->
                Card(modifier = Modifier.fillMaxWidth(), colors = CardDefaults.cardColors(containerColor = VitoSurface)) {
                    Row(modifier = Modifier.fillMaxWidth().padding(12.dp), horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically) {
                        Text(driver.username, color = Color.White)
                        Text("${driver.totalRides} rides", color = Color.Gray)
                    }
                }
            }
        }
    }
}

/**
 * QR Code Generator Screen
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun QrCodeGeneratorScreen(
    onGenerateCode: (Int) -> Unit,
    generatedCodes: List<QrCodeItem>,
    onCancel: () -> Unit,
    modifier: Modifier = Modifier
) {
    var useCount by remember { mutableStateOf("1") }
    
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("QR Codes", color = Color.White) },
                navigationIcon = {
                    IconButton(onClick = onCancel) { Icon(Icons.Default.ArrowBack, contentDescription = "Back", tint = Color.White) }
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = VitoSurface)
            )
        },
        modifier = modifier.background(VitoBackground)
    ) { padding ->
        Column(modifier = Modifier.fillMaxSize().padding(padding).padding(16.dp)) {
            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(containerColor = VitoSurface),
                shape = RoundedCornerShape(12.dp)
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text("Generate Referral Code", color = Color.White, style = MaterialTheme.typography.titleMedium)
                    Spacer(modifier = Modifier.height(12.dp))
                    OutlinedTextField(
                        value = useCount,
                        onValueChange = { useCount = it.filter { c -> c.isDigit() }.take(3) },
                        label = { Text("Allowed Uses") },
                        modifier = Modifier.fillMaxWidth(),
                        colors = OutlinedTextFieldDefaults.colors(focusedBorderColor = VitoGreen, unfocusedBorderColor = Color.Gray)
                    )
                    Spacer(modifier = Modifier.height(12.dp))
                    Button(
                        onClick = { onGenerateCode(useCount.toIntOrNull() ?: 1) },
                        modifier = Modifier.fillMaxWidth(),
                        colors = ButtonDefaults.buttonColors(containerColor = VitoGreen, contentColor = Color.Black)
                    ) { Text("Generate Code") }
                }
            }
            Spacer(modifier = Modifier.height(24.dp))
            Text("Generated Codes", color = Color.White, style = MaterialTheme.typography.titleMedium)
            Spacer(modifier = Modifier.height(12.dp))
            LazyColumn(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                items(generatedCodes) { code -> QrCodeItemCard(code = code) }
            }
        }
    }
}

data class QrCodeItem(val id: String, val token: String, val usesAllowed: Int, val usesCount: Int, val createdAt: String)

@Composable
fun QrCodeItemCard(code: QrCodeItem) {
    Card(modifier = Modifier.fillMaxWidth(), colors = CardDefaults.cardColors(containerColor = VitoSurface)) {
        Row(modifier = Modifier.fillMaxWidth().padding(12.dp), horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically) {
            Column {
                Text(code.token, color = VitoGreen, style = MaterialTheme.typography.titleMedium)
                Text("${code.usesCount}/${code.usesAllowed} uses • ${code.createdAt}", color = Color.Gray, style = MaterialTheme.typography.bodySmall)
            }
            IconButton(onClick = { }) { Icon(Icons.Default.ContentCopy, contentDescription = "Copy", tint = VitoGreen) }
        }
    }
}

/**
 * Settings Screen
 */
@Composable
fun AdminSettingsScreen(onLogout: () -> Unit, onCancel: () -> Unit, modifier: Modifier = Modifier) {
    Column(modifier = modifier.fillMaxSize().background(VitoBackground).padding(16.dp)) {
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically) {
            Text("Settings", color = Color.White, style = MaterialTheme.typography.headlineSmall)
            IconButton(onClick = onCancel) { Icon(Icons.Default.Close, contentDescription = "Close", tint = Color.White) }
        }
        Spacer(modifier = Modifier.height(24.dp))
        Card(modifier = Modifier.fillMaxWidth(), colors = CardDefaults.cardColors(containerColor = VitoSurface), shape = RoundedCornerShape(12.dp)) {
            Column {
                SettingsItem(Icons.Default.Person, "Profile", { })
                Divider(color = Color.Gray.copy(alpha = 0.2f))
                SettingsItem(Icons.Default.Notifications, "Notifications", { })
                Divider(color = Color.Gray.copy(alpha = 0.2f))
                SettingsItem(Icons.Default.Security, "Security", { })
                Divider(color = Color.Gray.copy(alpha = 0.2f))
                SettingsItem(Icons.Default.Info, "About", { })
            }
        }
        Spacer(modifier = Modifier.height(16.dp))
        Card(modifier = Modifier.fillMaxWidth(), colors = CardDefaults.cardColors(containerColor = VitoSurface), shape = RoundedCornerShape(12.dp)) {
            SettingsItem(Icons.Default.Logout, "Log Out", onLogout, tint = VitoError)
        }
    }
}

@Composable
fun SettingsItem(icon: ImageVector, title: String, onClick: () -> Unit, tint: Color = Color.White) {
    Row(modifier = Modifier.fillMaxWidth().clickable { onClick() }.padding(16.dp), verticalAlignment = Alignment.CenterVertically) {
        Icon(icon, contentDescription = null, tint = tint)
        Spacer(modifier = Modifier.width(16.dp))
        Text(title, color = tint, style = MaterialTheme.typography.bodyLarge, modifier = Modifier.weight(1f))
        Icon(Icons.Default.ChevronRight, contentDescription = null, tint = Color.Gray)
    }
}