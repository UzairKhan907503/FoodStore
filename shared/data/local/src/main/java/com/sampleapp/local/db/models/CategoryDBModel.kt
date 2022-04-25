package com.sampleapp.local.db.models


import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class CategoryDBModel(
    @PrimaryKey
    val categoryId: String,
    val description: String,
    val name: String,
)