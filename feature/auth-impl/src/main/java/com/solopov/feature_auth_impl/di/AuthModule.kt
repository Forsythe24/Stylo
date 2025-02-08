package com.solopov.feature_auth_impl.di

import com.solopov.feature_auth_api.data.repository.AuthRepository
import com.solopov.feature_auth_api.domain.usecase.LogInUseCase
import com.solopov.feature_auth_impl.data.repository.AuthRepositoryImpl
import com.solopov.feature_auth_impl.domain.usecase.LogInUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
interface AuthModule {
    @Binds
    @ViewModelScoped
    fun bindAuthRepository(authRepositoryImpl: AuthRepositoryImpl): AuthRepository

    @Binds
    @ViewModelScoped
    fun bindLogInUseCase(logInUseCaseImpl: LogInUseCaseImpl): LogInUseCase
}
