package com.vito.app.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.vito.app.ui.client.*
import com.vito.app.ui.driver.*
import com.vito.app.ui.admin.*

/**
 * VITO NavGraph - Main navigation composable
 */
@Composable
fun VitoNavGraph(
    navController: NavHostController,
    startDestination: String = Routes.CLIENT_HOME
) {
    NavHost(
        navController = navController,
        startDestination = startDestination
    ) {
        // Client screens
        composable(Routes.CLIENT_HOME) {
            ClientHomeScreen(
                onNavigateToBooking = { navController.navigate(Routes.RIDE_BOOKING) },
                onNavigateToSend = { navController.navigate(Routes.SEND) },
                onNavigateToMart = { navController.navigate(Routes.MART) },
                onNavigateToWallet = { navController.navigate(Routes.WALLET) },
                onNavigateToActivity = { navController.navigate(Routes.ACTIVITY) },
                onNavigateToProfile = { navController.navigate(Routes.PROFILE) }
            )
        }
        composable(Routes.RIDE_BOOKING) {
            RideBookingScreen(
                onBack = { navController.popBackStack() },
                onRideRequested = { navController.navigate(Routes.ACTIVE_RIDE) }
            )
        }
        composable(Routes.ACTIVE_RIDE) {
            ActiveRideScreen(
                onBack = { navController.popBackStack() }
            )
        }
        composable(Routes.SEND) {
            SendScreen(
                onBack = { navController.popBackStack() }
            )
        }
        composable(Routes.MART) {
            MartScreen(
                onBack = { navController.popBackStack() }
            )
        }
        composable(Routes.WALLET) {
            WalletScreen(
                onBack = { navController.popBackStack() }
            )
        }
        composable(Routes.ACTIVITY) {
            ActivityScreen(
                onBack = { navController.popBackStack() }
            )
        }
        composable(Routes.PROFILE) {
            ClientProfileScreen(
                onBack = { navController.popBackStack() }
            )
        }
        
        // Driver screens
        composable(Routes.DRIVER_HOME) {
            DriverHomeScreen(
                onNavigateToActiveJob = { navController.navigate(Routes.ACTIVE_JOB) },
                onNavigateToEarnings = { navController.navigate(Routes.EARNINGS) },
                onNavigateToQr = { navController.navigate(Routes.QR_SCAN) },
                onNavigateToProfile = { navController.navigate(Routes.DRIVER_PROFILE) }
            )
        }
        composable(Routes.ACTIVE_JOB) {
            ActiveJobScreen(
                onBack = { navController.popBackStack() }
            )
        }
        composable(Routes.EARNINGS) {
            DriverEarningsScreen(
                onBack = { navController.popBackStack() }
            )
        }
        composable(Routes.QR_SCAN) {
            DriverQrScreen(
                onBack = { navController.popBackStack() }
            )
        }
        composable(Routes.DRIVER_PROFILE) {
            DriverProfileScreen(
                onBack = { navController.popBackStack() }
            )
        }
        
        // Admin screens
        composable(Routes.ADMIN_DASHBOARD) {
            AdminDashboardScreen(
                onNavigateToQr = { navController.navigate(Routes.ADMIN_QR) },
                onNavigateToDrivers = { navController.navigate(Routes.ADMIN_DRIVERS) },
                onNavigateToClients = { navController.navigate(Routes.ADMIN_CLIENTS) },
                onNavigateToLiveOrders = { navController.navigate(Routes.ADMIN_LIVE_ORDERS) },
                onNavigateToMart = { navController.navigate(Routes.ADMIN_MART) },
                onNavigateToFinance = { navController.navigate(Routes.ADMIN_FINANCE) },
                onNavigateToAudit = { navController.navigate(Routes.ADMIN_AUDIT) }
            )
        }
        composable(Routes.ADMIN_QR) {
            AdminQrScreen(
                onBack = { navController.popBackStack() }
            )
        }
        composable(Routes.ADMIN_DRIVERS) {
            AdminDriversScreen(
                onBack = { navController.popBackStack() }
            )
        }
        composable(Routes.ADMIN_CLIENTS) {
            AdminClientsScreen(
                onBack = { navController.popBackStack() }
            )
        }
        composable(Routes.ADMIN_LIVE_ORDERS) {
            AdminLiveOrdersScreen(
                onBack = { navController.popBackStack() }
            )
        }
        composable(Routes.ADMIN_MART) {
            AdminMartScreen(
                onBack = { navController.popBackStack() }
            )
        }
        composable(Routes.ADMIN_FINANCE) {
            AdminFinanceScreen(
                onBack = { navController.popBackStack() }
            )
        }
        composable(Routes.ADMIN_AUDIT) {
            AdminAuditScreen(
                onBack = { navController.popBackStack() }
            )
        }
    }
}