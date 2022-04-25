package com.sampleapp.dashboard.utils

import com.sampleapp.dashboard.domain.models.Category
import com.sampleapp.dashboard.domain.models.Product
import com.sampleapp.local.db.models.CategoryDBModel
import com.sampleapp.local.db.models.CategoryProductsDBModel
import com.sampleapp.local.db.models.ProductDBModel

/**
 * Mapping Domain Models to DB Models
 */

fun Product.toDBModel() = ProductDBModel(
    categoryId = categoryId,
    productId = productId,
    name = name,
    url = url,
    description = description,
    currency = currency,
    price = price
)

@JvmName("toDBModelProduct")
fun List<Product>.toDBModel() = this.map { it.toDBModel() }

fun List<Category>.toDBModel() = this.map { it.toDBModel() }

fun Category.toDBModel() = CategoryDBModel(
    name = name,
    categoryId = categoryId,
    description = description
)

fun List<Category>.toCategoryProductDBModel() = this.map {
    CategoryProductsDBModel(it.toDBModel(),it.products.toDBModel())
}