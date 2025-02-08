package com.solopov.corecommon.model

data class ClothingResponse(
    val id: Long,
    val photoUrl: String,
    val isFavorite: Boolean,
    val name: String,
    val price: Int,
)
