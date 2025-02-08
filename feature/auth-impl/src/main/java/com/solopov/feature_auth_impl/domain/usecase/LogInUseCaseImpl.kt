package com.solopov.feature_auth_impl.domain.usecase

import com.solopov.coredata.coroutine.IoDispatcher
import com.solopov.feature_auth_api.data.repository.AuthRepository
import com.solopov.feature_auth_api.domain.usecase.LogInUseCase
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class LogInUseCaseImpl @Inject constructor(
    private val authRepository: AuthRepository,
    @IoDispatcher private val dispatcher: CoroutineDispatcher,
) : LogInUseCase {
    override suspend fun invoke() {
        return withContext(dispatcher) {
            authRepository.logIn()
        }
    }
}
