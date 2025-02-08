package com.solopov.feature_favorites_impl.data.mapper

import com.solopov.corecommon.model.ClothingResponse
import com.solopov.feature_favorites_api.domain.model.FavoriteClothing

fun ClothingResponse.toFavorite(): FavoriteClothing {
    return with(this) {
        FavoriteClothing(
            id = id,
            photoUrl = photoUrl,
            name = name,
            price = price,
        )
    }
}

fun List<ClothingResponse>.mapToFavoritesList(): List<FavoriteClothing> = map {
    it.toFavorite()
}
