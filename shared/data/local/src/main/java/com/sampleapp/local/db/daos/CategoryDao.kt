package com.sampleapp.local.db.daos

import androidx.room.*
import com.sampleapp.local.db.models.CategoryDBModel
import com.sampleapp.local.db.models.CategoryProductsDBModel
import kotlinx.coroutines.flow.Flow


@Dao
interface CategoryDao {
    @Query("SELECT * FROM CategoryDBModel")
    fun getAll(): Flow<List<CategoryDBModel>>

    @Query("SELECT * FROM CategoryDBModel WHERE categoryId = :id")
    fun get(id: Int): Flow<CategoryDBModel>

    @Query("SELECT * FROM CategoryDBModel")
    fun getAllCategoriesWithProducts(): Flow<List<CategoryProductsDBModel>>

    @Query("SELECT * FROM CategoryDBModel WHERE categoryId = :id")
    fun getWithProducts(id: String): Flow<CategoryProductsDBModel>

    @Transaction
    @Query("SELECT * FROM ProductDBModel, CategoryDBModel WHERE ProductDBModel.name LIKE :query")
    fun getFilteredProducts(query: String): Flow<List<CategoryProductsDBModel>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(categories: List<CategoryDBModel>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(category: CategoryDBModel)

    @Query("DELETE FROM CategoryDBModel")
    suspend fun deleteAll()

}