package com.solopov.corecommon.remote

import com.solopov.corecommon.model.ClothingResponse
import javax.inject.Inject

class ClothingDataSource @Inject constructor() {
    suspend fun getAllClothing(): List<ClothingResponse> {
        return mockedAllClothing
    }

    companion object {
        private val mockedAllClothing = listOf(
            ClothingResponse(
                id = 0,
                photoUrl = "https://tvoe.ru/img/203mseb/product/754/1005/8/4660303287735.jpg",
                isFavorite = false,
                name = "Толстовка с полузамком",
                price = 3690
            ),
            ClothingResponse(
                id = 1,
                photoUrl = "https://www.f5shop.ru/upload/iblock/b62/xg5umsid1tszupmuo920maurlj28fncy/preview_233518_1.jpg",
                isFavorite = false,
                name = "Джинсы на высокой посадке",
                price = 2190
            ),
            ClothingResponse(
                id = 2,
                photoUrl = "https://malinabonita.com/images/detailed/15/B08-1-1.jpg",
                isFavorite = false,
                name = "Ботинки кожаные с пряжкой, черные",
                price = 6990
            ),
            ClothingResponse(
                id = 3,
                photoUrl = "https://www.westside.com/cdn/shop/files/300988177BROWN_1.jpg?v=1723620022",
                isFavorite = false,
                name = "Платье Westside, коричневое",
                price = 12190
            ),

        )
    }
}
