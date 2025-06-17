package com.example.suki.ui.register

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.suki.data.UserPreferences

@Composable
fun RegisterScreen(
    onBackToLogin: () -> Unit,
    onRegisterSuccess: () -> Unit // Esto te lleva al Home, por ejemplo
) {
    val context = LocalContext.current

    var username by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF23213A)),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        OutlinedTextField(
            value = username,
            onValueChange = { username = it },
            label = { Text("Usuario") },
            singleLine = true,
            shape = RoundedCornerShape(20.dp),
            colors = OutlinedTextFieldDefaults.colors(
                unfocusedContainerColor = Color(0xFF2C2842),
                focusedContainerColor = Color(0xFF2C2842),
                unfocusedTextColor = Color.White,
                focusedTextColor = Color.White,
                unfocusedLabelColor = Color(0xFFD1C4E9),
                focusedLabelColor = Color(0xFF7B6CF6)
            ),
            modifier = Modifier.fillMaxWidth(0.8f)
        )
        Spacer(modifier = Modifier.height(12.dp))
        OutlinedTextField(
            value = email,
            onValueChange = { email = it },
            label = { Text("Correo") },
            singleLine = true,
            shape = RoundedCornerShape(20.dp),
            colors = OutlinedTextFieldDefaults.colors(
                unfocusedContainerColor = Color(0xFF2C2842),
                focusedContainerColor = Color(0xFF2C2842),
                unfocusedTextColor = Color.White,
                focusedTextColor = Color.White,
                unfocusedLabelColor = Color(0xFFD1C4E9),
                focusedLabelColor = Color(0xFF7B6CF6)
            ),
            modifier = Modifier.fillMaxWidth(0.8f)
        )
        Spacer(modifier = Modifier.height(12.dp))
        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            label = { Text("Contraseña") },
            singleLine = true,
            shape = RoundedCornerShape(20.dp),
            colors = OutlinedTextFieldDefaults.colors(
                unfocusedContainerColor = Color(0xFF2C2842),
                focusedContainerColor = Color(0xFF2C2842),
                unfocusedTextColor = Color.White,
                focusedTextColor = Color.White,
                unfocusedLabelColor = Color(0xFFD1C4E9),
                focusedLabelColor = Color(0xFF7B6CF6)
            ),
            modifier = Modifier.fillMaxWidth(0.8f)
        )
        Spacer(modifier = Modifier.height(12.dp))
        OutlinedTextField(
            value = confirmPassword,
            onValueChange = { confirmPassword = it },
            label = { Text("Confirma contraseña") },
            singleLine = true,
            shape = RoundedCornerShape(20.dp),
            colors = OutlinedTextFieldDefaults.colors(
                unfocusedContainerColor = Color(0xFF2C2842),
                focusedContainerColor = Color(0xFF2C2842),
                unfocusedTextColor = Color.White,
                focusedTextColor = Color.White,
                unfocusedLabelColor = Color(0xFFD1C4E9),
                focusedLabelColor = Color(0xFF7B6CF6)
            ),
            modifier = Modifier.fillMaxWidth(0.8f)
        )

        Spacer(modifier = Modifier.height(24.dp))
        Button(
            onClick = {
                if (username.isNotBlank() && email.isNotBlank() &&
                    password == confirmPassword && password.isNotBlank()) {

                    UserPreferences.saveUser(context, username, email, password) // ✅

                    onRegisterSuccess()
                }
            },
            shape = RoundedCornerShape(20.dp),
            colors = ButtonDefaults.outlinedButtonColors(
                containerColor = Color.Transparent,
                contentColor = Color(0xFF7B6CF6)
            ),
            border = ButtonDefaults.outlinedButtonBorder,
            modifier = Modifier.fillMaxWidth(0.6f)
        ) {
            Text("Registrar")
        }

        Spacer(modifier = Modifier.height(24.dp))
        TextButton(onClick = onBackToLogin) {
            Text("Volver a Ingresar", color = Color(0xFF7B6CF6))
        }
    }
}
