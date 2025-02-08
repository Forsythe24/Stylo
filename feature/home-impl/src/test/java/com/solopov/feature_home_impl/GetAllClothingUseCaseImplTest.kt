package com.solopov.feature_home_impl

import com.solopov.feature_home_api.domain.model.Clothing
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
import org.mockito.kotlin.whenever
import kotlin.test.assertEquals

class GetAllClothingUseCaseImplTest {

    private lateinit var getAllClothingUseCase: GetAllClothingUseCaseImpl
    private val clothingRepository: ClothingRepository = mock()
    private val testDispatcher = TestCoroutineDispatcher()

    @Before
    fun setUp() {
        // Устанавливаем тестовый диспетчер для корутин
        Dispatchers.setMain(testDispatcher)
        getAllClothingUseCase = GetAllClothingUseCaseImpl(clothingRepository, testDispatcher)
    }

    @After
    fun tearDown() {
        // Сбрасываем диспетчер после тестов
        Dispatchers.resetMain()
        testDispatcher.cleanupTestCoroutines()
    }

    @Test
    fun `test invoke returns list of clothing`() = runBlocking {
        // Даем мок репозиторию вернуть список одежды
        val expectedClothingList = listOf(
            Clothing(id = 1, name = "T-shirt"),
            Clothing(id = 2, name = "Jeans")
        )
        
        whenever(clothingRepository.getAllClothing()).thenReturn(expectedClothingList)

        // Выполняем метод use case
        val result = getAllClothingUseCase.invoke()

        // Проверяем, что метод getAllClothing был вызван один раз
        verify(clothingRepository, times(1)).getAllClothing()

        // Проверяем, что результат соответствует ожидаемому списку
        assertEquals(expectedClothingList, result)
    }
}
