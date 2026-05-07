package com.ElOuedUniv.maktaba.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Book(
    val id: Long? = null,
    val isbn: String,
    val title: String,
    @SerialName("nbPages")
    val nbPages: Int,
    @SerialName("imageUrl")
    val imageUrl: String? = null
)

