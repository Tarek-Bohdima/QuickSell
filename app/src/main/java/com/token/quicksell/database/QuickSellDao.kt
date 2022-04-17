package com.token.quicksell.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface QuickSellDao {

    @Query("SELECT * FROM quicksell_database ORDER BY category ASC")
    fun getProducts(): LiveData<List<DatabaseQuickSell>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(vararg products: DatabaseQuickSell)
}