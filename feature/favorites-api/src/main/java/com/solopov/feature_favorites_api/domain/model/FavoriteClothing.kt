package com.solopov.feature_favorites_api.domain.model

data class FavoriteClothing(
    val id: Long,
    val photoUrl: String,
    val name: String,
    val price: Int,
)
