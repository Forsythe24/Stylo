package com.solopov.feature_home_impl

import com.solopov.feature_home_api.repository.ClothingRepository
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

class ChangeClothingInFavoritesStateUseCaseImplTest {

    private lateinit var changeClothingInFavoritesStateUseCase: ChangeClothingInFavoritesStateUseCaseImpl
    private val clothingRepository: ClothingRepository = mock()
    private val testDispatcher = TestCoroutineDispatcher()

    @Before
    fun setUp() {
        // Устанавливаем тестовый диспетчер для корутин
        Dispatchers.setMain(testDispatcher)
        changeClothingInFavoritesStateUseCase = ChangeClothingInFavoritesStateUseCaseImpl(clothingRepository, testDispatcher)
    }

    @After
    fun tearDown() {
        // Сбрасываем диспетчер после тестов
        Dispatchers.resetMain()
        testDispatcher.cleanupTestCoroutines()
    }

    @Test
    fun `test invoke with newState true calls addToFavorites`() = runBlocking {
        // Данный ID одежды
        val clothingId = 123L

        // Выполнение метода use case с newState = true
        changeClothingInFavoritesStateUseCase.invoke(clothingId, newState = true)

        // Проверка, что метод addToFavorites был вызван с нужным ID
        verify(clothingRepository, times(1)).addToFavorites(clothingId)
        // Убедимся, что метод removeFromFavorites не был вызван
        verify(clothingRepository, times(0)).removeFromFavorites(clothingId)
    }

    @Test
    fun `test invoke with newState false calls removeFromFavorites`() = runBlocking {
        // Данный ID одежды
        val clothingId = 123L

        // Выполнение метода use case с newState = false
        changeClothingInFavoritesStateUseCase.invoke(clothingId, newState = false)

        // Проверка, что метод removeFromFavorites был вызван с нужным ID
        verify(clothingRepository, times(1)).removeFromFavorites(clothingId)
        // Убедимся, что метод addToFavorites не был вызван
        verify(clothingRepository, times(0)).addToFavorites(clothingId)
    }
}
