package com.solopov.stylo.domain

import com.solopov.coredata.coroutine.IoDispatcher
import com.solopov.coredata.datastore.token.TokenStorage
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import javax.inject.Inject

class GetTokenUseCase @Inject constructor(
    @IoDispatcher private val dispatcher: CoroutineDispatcher,
    private val tokenStorage: TokenStorage,
) {
    suspend operator fun invoke(): Flow<String> {
        return withContext(dispatcher) {
            tokenStorage.getToken()
        }
    }
}
