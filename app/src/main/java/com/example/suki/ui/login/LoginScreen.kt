package com.example.suki.ui.login

import android.content.Context
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.suki.R
import com.example.suki.data.UserPreferences

@Composable
fun LoginScreen(
    onLoginSuccess: () -> Unit,
    onRegisterClick: () -> Unit
) {
    val context = LocalContext.current
    val sharedPreferences = context.getSharedPreferences("user_prefs", Context.MODE_PRIVATE)

    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var error by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF23213A)),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Box(
            modifier = Modifier
                .size(100.dp)
                .clip(CircleShape)
                .background(Color(0xFF7B6CF6)),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = Icons.Default.Person,
                contentDescription = null,
                tint = Color(0xFFD1C4E9),
                modifier = Modifier.size(60.dp)
            )
        }

        Spacer(modifier = Modifier.height(32.dp))

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

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            label = { Text("Contraseña") },
            singleLine = true,
            visualTransformation = PasswordVisualTransformation(),
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

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
                val savedUsername = UserPreferences.getUsername(context)
                val savedPassword = UserPreferences.getPassword(context)

                if (username == savedUsername && password == savedPassword) {
                    error = false
                    // Guardar sesión activa si deseas
                    sharedPreferences.edit().putBoolean("is_logged_in", true).apply()
                    onLoginSuccess()
                } else {
                    error = true
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
            Text("Ingresar")
        }

        if (error) {
            Text(
                text = "Usuario o contraseña incorrectos",
                color = Color.Red,
                fontSize = 14.sp,
                modifier = Modifier.padding(top = 8.dp)
            )
        }

        Spacer(modifier = Modifier.height(24.dp))

        Text("¿Aún no tienes cuenta?", color = Color.White, fontSize = 15.sp)

        TextButton(onClick = onRegisterClick) {
            Text("Regístrate", color = Color(0xFF7B6CF6))
        }
    }
}

