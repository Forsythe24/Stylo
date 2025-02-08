package com.solopov.feature_favorites_impl

import com.solopov.feature_favorites_api.domain.model.FavoriteClothing
import com.solopov.feature_favorites_api.repository.FavoritesRepository
import com.solopov.feature_favorites_impl.domain.usecase.GetAllFavoritesUseCaseImpl
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations


@OptIn(ExperimentalCoroutinesApi::class)
class GetAllFavoritesUseCaseImplTest {

    @Mock
    private lateinit var favoritesRepository: FavoritesRepository

    private val testDispatcher = StandardTestDispatcher()

    private lateinit var getAllFavoritesUseCase: GetAllFavoritesUseCaseImpl

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)

        Dispatchers.setMain(testDispatcher)

        getAllFavoritesUseCase = GetAllFavoritesUseCaseImpl(
            favoritesRepository = favoritesRepository,
            coroutineDispatcher = testDispatcher
        )
    }

    @Test
    fun `invoke should return list of favorite clothing`() = runTest {
        val expectedFavorites = listOf(
            FavoriteClothing(id = 1, name = "Jacket", photoUrl = "url1", price = 1000),
            FavoriteClothing(id = 2, name = "Jeans", photoUrl = "url2", price = 2000)
        )

        `when`(favoritesRepository.getAllFavoriteClothing()).thenReturn(expectedFavorites)

        val result = getAllFavoritesUseCase()

        assertEquals(expectedFavorites, result)
    }

    @Test
    fun `invoke should return empty list when repository returns empty list`() = runTest {
        val expectedFavorites = emptyList<FavoriteClothing>()

        `when`(favoritesRepository.getAllFavoriteClothing()).thenReturn(expectedFavorites)

        val result = getAllFavoritesUseCase()

        assertEquals(expectedFavorites, result)
    }
}
