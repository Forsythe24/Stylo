package com.solopov.coredata.coroutine

import android.app.Application
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class CoroutineModule {

    @Provides
    @ApplicationCoroutineScope
    fun scope(
        application: Application,
    ): CoroutineScope = (application as ApplicationCoroutineScopeHolder).applicationScope

    @Provides
    @Singleton
    @IoDispatcher
    fun ioDispatcher(): CoroutineDispatcher = Dispatchers.IO

    @Provides
    @Singleton
    @DefaultDispatcher
    fun defaultDispatcher(): CoroutineDispatcher = Dispatchers.Default

    @Provides
    @Singleton
    @MainDispatcher
    fun mainDispatcher(): CoroutineDispatcher = Dispatchers.Main
}
