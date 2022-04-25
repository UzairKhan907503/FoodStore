package com.sampleapp.local.db.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.sampleapp.local.db.models.ProductDBModel
import kotlinx.coroutines.flow.Flow

@Dao
interface ProductDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(products: List<ProductDBModel>)

    @Query("DELETE FROM ProductDBModel")
    suspend fun deleteAll()
}