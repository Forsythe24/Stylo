package com.solopov.feature_home_api.domain.model

data class Clothing(
    val id: Long,
    val photoUrl: String,
    val isFavorite: Boolean,
    val name: String,
    val price: Int,
)
