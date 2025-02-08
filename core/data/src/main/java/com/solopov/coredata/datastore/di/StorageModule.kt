package com.solopov.coredata.datastore.di

import com.solopov.coredata.datastore.token.TokenStorage
import com.solopov.coredata.datastore.token.TokenStorageImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface StorageModule {
    @Binds
    @Singleton
    fun tokenStorage(impl: TokenStorageImpl): TokenStorage
}
