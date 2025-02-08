package com.solopov.coredata.datastore.token

import kotlinx.coroutines.flow.Flow

interface TokenStorage {
    suspend fun setToken(token: String)
    suspend fun getToken(): Flow<String>
    suspend fun clearAllTokens()
}
