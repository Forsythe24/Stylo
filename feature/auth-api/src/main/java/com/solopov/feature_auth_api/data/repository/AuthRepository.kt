package com.solopov.feature_auth_api.data.repository

interface AuthRepository {
    suspend fun logIn()
}
