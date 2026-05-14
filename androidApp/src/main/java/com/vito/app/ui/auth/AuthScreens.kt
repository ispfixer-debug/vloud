package com.vito.app.ui.auth

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import com.vito.app.ui.theme.VitoBackground
import com.vito.app.ui.theme.VitoError
import com.vito.app.ui.theme.VitoGreen

/**
 * Login Screen - PIN-based authentication
 */
@Composable
fun LoginScreen(
    onLoginSuccess: (String) -> Unit,
    onNavigateToSignup: () -> Unit,
    modifier: Modifier = Modifier
) {
    var username by remember { mutableStateOf("") }
    var pin by remember { mutableStateOf("") }
    var isLoading by remember { mutableStateOf(false) }
    var errorMessage by remember { mutableStateOf<String?>(null) }
    var pinVisible by remember { mutableStateOf(false) }

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(VitoBackground)
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "VITO",
            style = MaterialTheme.typography.displayLarge,
            color = VitoGreen
        )
        
        Spacer(modifier = Modifier.height(8.dp))
        
        Text(
            text = "Your ride, delivered.",
            style = MaterialTheme.typography.bodyLarge,
            color = Color.Gray
        )
        
        Spacer(modifier = Modifier.height(48.dp))
        
        OutlinedTextField(
            value = username,
            onValueChange = { 
                if (it.length <= 20) username = it.lowercase().filter { c -> c.isLetterOrDigit() || c == '_' }
            },
            label = { Text("Username") },
            leadingIcon = { Icon(Icons.Default.Person, contentDescription = null, tint = VitoGreen) },
            singleLine = true,
            modifier = Modifier.fillMaxWidth(),
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = VitoGreen,
                unfocusedBorderColor = Color.Gray,
                focusedLabelColor = VitoGreen,
                cursorColor = VitoGreen
            )
        )
        
        Spacer(modifier = Modifier.height(16.dp))
        
        OutlinedTextField(
            value = pin,
            onValueChange = { 
                if (it.length <= 6 && it.all { c -> c.isDigit() }) pin = it
            },
            label = { Text("PIN") },
            leadingIcon = { Icon(Icons.Default.Lock, contentDescription = null, tint = VitoGreen) },
            trailingIcon = {
                IconButton(onClick = { pinVisible = !pinVisible }) {
                    Icon(
                        if (pinVisible) Icons.Default.VisibilityOff else Icons.Default.Visibility,
                        contentDescription = null,
                        tint = Color.Gray
                    )
                }
            },
            singleLine = true,
            visualTransformation = if (pinVisible) VisualTransformation.None else PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.NumberPassword),
            modifier = Modifier.fillMaxWidth(),
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = VitoGreen,
                unfocusedBorderColor = Color.Gray,
                focusedLabelColor = VitoGreen,
                cursorColor = VitoGreen
            )
        )
        
        errorMessage?.let { error ->
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = error, color = VitoError, style = MaterialTheme.typography.bodySmall)
        }
        
        Spacer(modifier = Modifier.height(24.dp))
        
        Button(
            onClick = {
                if (username.length >= 3 && pin.length == 6) {
                    isLoading = true
                    errorMessage = null
                    onLoginSuccess(username)
                } else {
                    errorMessage = "Username must be 3+ chars, PIN must be 6 digits"
                }
            },
            enabled = !isLoading && username.length >= 3 && pin.length == 6,
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp),
            colors = ButtonDefaults.buttonColors(containerColor = VitoGreen, contentColor = Color.Black),
            shape = RoundedCornerShape(12.dp)
        ) {
            if (isLoading) {
                CircularProgressIndicator(modifier = Modifier.size(24.dp), color = Color.Black)
            } else {
                Text("Sign In", style = MaterialTheme.typography.titleMedium)
            }
        }
        
        Spacer(modifier = Modifier.height(16.dp))
        
        Row(horizontalArrangement = Arrangement.Center) {
            Text(text = "Don't have an account? ", color = Color.Gray)
            Text(
                text = "Sign Up",
                color = VitoGreen,
                modifier = Modifier.clickable { onNavigateToSignup() }
            )
        }
    }
}

/**
 * Signup Screen
 */
@Composable
fun SignupScreen(
    onSignupSuccess: (String) -> Unit,
    onNavigateToLogin: () -> Unit,
    modifier: Modifier = Modifier
) {
    var username by remember { mutableStateOf("") }
    var displayName by remember { mutableStateOf("") }
    var pin by remember { mutableStateOf("") }
    var confirmPin by remember { mutableStateOf("") }
    var isLoading by remember { mutableStateOf(false) }
    var errorMessage by remember { mutableStateOf<String?>(null) }
    var pinVisible by remember { mutableStateOf(false) }

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(VitoBackground)
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Join VITO",
            style = MaterialTheme.typography.displaySmall,
            color = VitoGreen
        )
        
        Spacer(modifier = Modifier.height(8.dp))
        Text(text = "Create your account", color = Color.Gray)
        
        Spacer(modifier = Modifier.height(32.dp))
        
        OutlinedTextField(
            value = username,
            onValueChange = { if (it.length <= 20) username = it.lowercase().filter { c -> c.isLetterOrDigit() || c == '_' } },
            label = { Text("Username") },
            singleLine = true,
            modifier = Modifier.fillMaxWidth(),
            colors = OutlinedTextFieldDefaults.colors(focusedBorderColor = VitoGreen, unfocusedBorderColor = Color.Gray)
        )
        
        Spacer(modifier = Modifier.height(12.dp))
        
        OutlinedTextField(
            value = displayName,
            onValueChange = { if (it.length <= 50) displayName = it },
            label = { Text("Display Name") },
            singleLine = true,
            modifier = Modifier.fillMaxWidth(),
            colors = OutlinedTextFieldDefaults.colors(focusedBorderColor = VitoGreen, unfocusedBorderColor = Color.Gray)
        )
        
        Spacer(modifier = Modifier.height(12.dp))
        
        OutlinedTextField(
            value = pin,
            onValueChange = { if (it.length <= 6 && it.all { c -> c.isDigit() }) pin = it },
            label = { Text("PIN (6 digits)") },
            singleLine = true,
            visualTransformation = if (pinVisible) VisualTransformation.None else PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.NumberPassword),
            modifier = Modifier.fillMaxWidth(),
            colors = OutlinedTextFieldDefaults.colors(focusedBorderColor = VitoGreen, unfocusedBorderColor = Color.Gray)
        )
        
        Spacer(modifier = Modifier.height(12.dp))
        
        OutlinedTextField(
            value = confirmPin,
            onValueChange = { if (it.length <= 6 && it.all { c -> c.isDigit() }) confirmPin = it },
            label = { Text("Confirm PIN") },
            singleLine = true,
            visualTransformation = PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.NumberPassword),
            modifier = Modifier.fillMaxWidth(),
            colors = OutlinedTextFieldDefaults.colors(focusedBorderColor = VitoGreen, unfocusedBorderColor = Color.Gray)
        )
        
        errorMessage?.let { error ->
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = error, color = VitoError, style = MaterialTheme.typography.bodySmall)
        }
        
        Spacer(modifier = Modifier.height(24.dp))
        
        Button(
            onClick = {
                when {
                    username.length < 3 -> errorMessage = "Username must be 3+ characters"
                    displayName.isBlank() -> errorMessage = "Display name is required"
                    pin.length != 6 -> errorMessage = "PIN must be 6 digits"
                    pin != confirmPin -> errorMessage = "PINs do not match"
                    else -> { isLoading = true; errorMessage = null; onSignupSuccess(username) }
                }
            },
            enabled = !isLoading,
            modifier = Modifier.fillMaxWidth().height(56.dp),
            colors = ButtonDefaults.buttonColors(containerColor = VitoGreen, contentColor = Color.Black),
            shape = RoundedCornerShape(12.dp)
        ) {
            Text("Create Account", style = MaterialTheme.typography.titleMedium)
        }
        
        Spacer(modifier = Modifier.height(16.dp))
        
        Row(horizontalArrangement = Arrangement.Center) {
            Text(text = "Already have an account? ", color = Color.Gray)
            Text(text = "Sign In", color = VitoGreen, modifier = Modifier.clickable { onNavigateToLogin() })
        }
    }
}