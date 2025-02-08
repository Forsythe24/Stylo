package com.solopov.feature_auth_impl.data.repository

import com.solopov.coredata.datastore.token.TokenStorage
import com.solopov.feature_auth_api.data.repository.AuthRepository
import kotlinx.coroutines.delay
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val tokenStorage: TokenStorage
): AuthRepository {
    override suspend fun logIn() {
        delay(1000L)
        tokenStorage.setToken("mock")
    }
}
