package com.solopov.feature_home_impl.data.mapper

import com.solopov.corecommon.model.ClothingResponse
import com.solopov.feature_home_api.domain.model.Clothing

fun ClothingResponse.toDomain(): Clothing {
    return with(this) {
        Clothing(
            id = id,
            photoUrl = photoUrl,
            isFavorite = isFavorite,
            name = name,
            price = price,
        )
    }
}

fun List<ClothingResponse>.mapList(): List<Clothing> = map {
    it.toDomain()
}
