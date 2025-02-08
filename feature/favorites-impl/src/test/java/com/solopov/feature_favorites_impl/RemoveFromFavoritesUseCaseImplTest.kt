package com.solopov.feature_favorites_impl

import com.solopov.feature_favorites_api.repository.FavoritesRepository
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

class RemoveFromFavoritesUseCaseImplTest {

    private lateinit var removeFromFavoritesUseCase: RemoveFromFavoritesUseCaseImpl
    private val favoritesRepository: FavoritesRepository = mock()
    private val testDispatcher = TestCoroutineDispatcher()

    @Before
    fun setUp() {
        // Устанавливаем тестовый диспетчер для корутин
        Dispatchers.setMain(testDispatcher)
        removeFromFavoritesUseCase = RemoveFromFavoritesUseCaseImpl(favoritesRepository, testDispatcher)
    }

    @After
    fun tearDown() {
        // Сбрасываем диспетчер после тестов
        Dispatchers.resetMain()
        testDispatcher.cleanupTestCoroutines()
    }

    @Test
    fun `test invoke calls removeFromFavorites`() = runBlocking {
        // Данный ID одежды
        val clothingId = 123L

        // Выполнение метода use case
        removeFromFavoritesUseCase.invoke(clothingId)

        // Проверка, что метод removeFromFavorites был вызван с нужным ID
        verify(favoritesRepository, times(1)).removeFromFavorites(clothingId)
    }
}
