package com.sampleapp.dashboard.utils

import com.sampleapp.dashboard.data.remote.models.CategoryDTO
import com.sampleapp.dashboard.data.remote.models.ProductDTO
import com.sampleapp.dashboard.domain.models.Category
import com.sampleapp.dashboard.domain.models.Product
import com.sampleapp.local.db.models.CategoryDBModel
import com.sampleapp.local.db.models.CategoryProductsDBModel
import com.sampleapp.local.db.models.ProductDBModel

/**
 * Mapping DBModels to Domain Models
 */

fun ProductDBModel.toDomainModel() = Product(
    productId = productId,
    categoryId = categoryId,
    name = name,
    url = url,
    description = description,
    price = price,
    currency = currency
)

fun CategoryDBModel.toDomainModel(products: List<Product>) = Category(
    categoryId = categoryId,
    name = name,
    description = description,
    products = products
)

@JvmName("toDomainModelProductDBModel")
fun List<ProductDBModel>.toDomainModel() = this.map { it.toDomainModel() }

fun CategoryProductsDBModel.toDomainModel() = category.toDomainModel(products.toDomainModel())

fun List<CategoryProductsDBModel>.toDomainModel() = this.map {
    it.toDomainModel()
}

fun ProductDTO.toDomainModel() = Product(
    categoryId = categoryId,
    productId = id,
    name = name,
    url = url,
    description = description,
    price = salePrice.amount,
    currency = salePrice.currency

)

@JvmName("toDomainModelProductDTO")
fun List<ProductDTO>.toDomainModel() = this.map {
    it.toDomainModel()
}

@JvmName("toDomainModelCategoryDTO")
fun List<CategoryDTO>.toDomainModel() = this.map {
    it.toDomainModel()
}

fun CategoryDTO.toDomainModel() = Category(
    categoryId = id,
    name = name,
    description = description,
    products = products.toDomainModel()
)


