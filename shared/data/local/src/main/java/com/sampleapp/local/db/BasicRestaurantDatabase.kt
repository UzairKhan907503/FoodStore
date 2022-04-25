package com.sampleapp.local.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.sampleapp.local.db.daos.CategoryDao
import com.sampleapp.local.db.daos.ProductDao
import com.sampleapp.local.db.models.CategoryDBModel
import com.sampleapp.local.db.models.ProductDBModel

private const val DB_NAME = "food_store_database"

@Database(entities = [CategoryDBModel::class, ProductDBModel::class], version = 1)
abstract class FoodStoreDataBase : RoomDatabase() {
    abstract fun categoryDao(): CategoryDao
    abstract fun productDao(): ProductDao

    companion object {
        @Volatile
        private var instance: FoodStoreDataBase? = null

        fun getDatabase(context: Context): FoodStoreDataBase =
            instance ?: synchronized(this) {
                instance ?: buildDatabase(context).also { instance = it }
            }

        private fun buildDatabase(appContext: Context) =
            Room.databaseBuilder(appContext, FoodStoreDataBase::class.java, DB_NAME)
                .fallbackToDestructiveMigration()
                .build()
    }
}
