package com.example.suki.data

import android.content.Context
import androidx.datastore.preferences.core.*
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking

private val Context.dataStore by preferencesDataStore(name = "user_prefs")

object UserPreferences {
    private val KEY_USERNAME = stringPreferencesKey("username")
    private val KEY_EMAIL = stringPreferencesKey("email")
    private val KEY_LOGGED_IN = booleanPreferencesKey("logged_in")
    private val KEY_PASSWORD = stringPreferencesKey("password")

    fun saveUser(context: Context, username: String, email: String, password: String) {
        runBlocking {
            context.dataStore.edit { prefs ->
                prefs[KEY_USERNAME] = username
                prefs[KEY_EMAIL] = email
                prefs[KEY_PASSWORD] = password
                prefs[KEY_LOGGED_IN] = true
            }
        }
    }

    fun getPassword(context: Context): String {
        return runBlocking {
            context.dataStore.data.first()[KEY_PASSWORD] ?: ""
        }
    }


    fun isLoggedIn(context: Context): Boolean {
        return runBlocking {
            context.dataStore.data.first()[KEY_LOGGED_IN] ?: false
        }
    }

    fun getUsername(context: Context): String {
        return runBlocking {
            context.dataStore.data.first()[KEY_USERNAME] ?: ""
        }
    }

    fun logout(context: Context) {
        runBlocking {
            context.dataStore.edit { prefs ->
                prefs[KEY_LOGGED_IN] = false // solo cerramos sesión
            }
        }
    }
}
