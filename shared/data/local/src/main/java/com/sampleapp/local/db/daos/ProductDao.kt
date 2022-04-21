package com.sampleapp.local.db.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.sampleapp.local.db.models.ProductDBModel
import kotlinx.coroutines.flow.Flow

@Dao
interface ProductDao {
    @Query("SELECT * FROM ProductDBModel")
    fun getAll(): Flow<List<ProductDBModel>>

    @Query("SELECT * FROM ProductDBModel WHERE productId = :productId AND categoryId = :categoryId")
    fun get(productId: String, categoryId: String): Flow<ProductDBModel>

    @Query("SELECT * FROM ProductDBModel WHERE categoryId = :categoryId")
    fun getByCategoryId(categoryId: String): Flow<List<ProductDBModel>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(products: List<ProductDBModel>): List<Long>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(product: ProductDBModel)

    @Query("DELETE FROM ProductDBModel")
    suspend fun deleteAll()
}