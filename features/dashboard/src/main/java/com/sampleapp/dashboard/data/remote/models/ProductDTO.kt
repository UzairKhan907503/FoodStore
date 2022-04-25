package com.sampleapp.dashboard.data.remote.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ProductDTO(
    @SerialName("id")
    val id: String,
    @SerialName("categoryId")
    val categoryId: String,
    @SerialName("name")
    val name: String,
    @SerialName("url")
    val url: String,
    @SerialName("description")
    val description: String,
    @SerialName("salePrice")
    val salePrice: SalePriceDTO
){
    @Serializable
    data class SalePriceDTO(
        @SerialName("amount")
        val amount: String,
        @SerialName("currency")
        val currency: String
    )
}