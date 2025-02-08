package com.solopov.feature_auth_impl.data.repository.di

import com.solopov.feature_auth_api.data.repository.AuthRepository
import com.solopov.feature_auth_impl.data.repository.AuthRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.scopes.ViewModelScoped
import dagger.hilt.components.SingletonComponent

//@Module
//@InstallIn(SingletonComponent::class)
//interface DataModule {
//    @Binds
//    @ViewModelScoped
//    fun provideAuthRepository(authRepositoryImpl: AuthRepositoryImpl): AuthRepository = authRepositoryImpl
//}
