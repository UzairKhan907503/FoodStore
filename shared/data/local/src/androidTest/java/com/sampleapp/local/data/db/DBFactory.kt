package com.sampleapp.local.data.db

import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import com.sampleapp.local.db.FoodStoreDataBase

object DBFactory {

    fun getInMemoryDb() = Room.inMemoryDatabaseBuilder(
        ApplicationProvider.getApplicationContext(),
        FoodStoreDataBase::class.java
    ).allowMainThreadQueries()
        .build()
}
