package com.sampleapp.local.db.models

import androidx.room.Embedded
import androidx.room.Relation

data class CategoryProductsDBModel(

    @Embedded val category: CategoryDBModel,
    @Relation(
        parentColumn = "categoryId",
        entityColumn = "categoryId"
    )
    val products: List<ProductDBModel>
)