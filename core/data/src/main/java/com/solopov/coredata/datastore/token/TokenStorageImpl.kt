package com.solopov.coredata.datastore.token

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject


class TokenStorageImpl @Inject constructor(
    private val dataStore: DataStore<Preferences>,
) : TokenStorage {

    companion object {
        val TOKEN_KEY = stringPreferencesKey("access_jwt")
        val REFRESH_TOKEN_KEY = stringPreferencesKey("refresh_jwt")
    }

    override suspend fun setToken(token: String) {
        dataStore.edit { preferences ->
            preferences[TOKEN_KEY] = token
        }
    }

    override suspend fun getToken(): Flow<String> {
        return dataStore.data.map { preferences ->
            preferences[TOKEN_KEY].orEmpty()
        }
    }

    override suspend fun clearAllTokens() {
        dataStore.edit { preferences ->
            preferences.remove(TOKEN_KEY)
            preferences.remove(REFRESH_TOKEN_KEY)
        }
    }
}

