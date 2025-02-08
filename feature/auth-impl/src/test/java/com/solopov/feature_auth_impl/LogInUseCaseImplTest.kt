package com.solopov.feature_auth_impl

import com.solopov.feature_auth_api.data.repository.AuthRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify
import org.mockito.kotlin.times

class LogInUseCaseImplTest {

    private lateinit var logInUseCase: LogInUseCaseImpl
    private val authRepository: AuthRepository = mock()
    private val testDispatcher = TestCoroutineDispatcher()

    @Before
    fun setUp() {
        // Устанавливаем тестовый диспетчер для корутин
        Dispatchers.setMain(testDispatcher)
        logInUseCase = LogInUseCaseImpl(authRepository, testDispatcher)
    }

    @After
    fun tearDown() {
        // Сбрасываем диспетчер после тестов
        Dispatchers.resetMain()
        testDispatcher.cleanupTestCoroutines()
    }

    @Test
    fun `test invoke calls logIn method`() = runBlocking {
        // Выполняем метод use case
        logInUseCase.invoke()

        // Проверяем, что метод logIn был вызван один раз
        verify(authRepository, times(1)).logIn()
    }
}
