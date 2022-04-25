package com.sampleapp.local.db.daos

import androidx.room.*
import com.sampleapp.local.db.models.CategoryDBModel
import com.sampleapp.local.db.models.CategoryProductsDBModel
import kotlinx.coroutines.flow.Flow


@Dao
interface CategoryDao {

    @Query("SELECT * FROM CategoryDBModel")
    fun getAllCategoriesWithProducts(): Flow<List<CategoryProductsDBModel>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(categories: List<CategoryDBModel>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(category: CategoryDBModel)

    @Query("DELETE FROM CategoryDBModel")
    suspend fun deleteAll()

}