package com.ElOuedUniv.maktaba.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Category(
    val id: Long? = null,
    val name: String,
    val description: String,
    @SerialName("iconRes")
    val iconRes: Int
)
