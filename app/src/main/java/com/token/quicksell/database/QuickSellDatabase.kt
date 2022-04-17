package com.token.quicksell.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [DatabaseQuickSell::class], version = 1, exportSchema = false)
abstract class QuickSellDatabase : RoomDatabase() {

    abstract val quickQuickSellDao: QuickSellDao
}

private lateinit var INSTANCE: QuickSellDatabase

fun getDatabase(context: Context): QuickSellDatabase {
    synchronized(QuickSellDatabase::class.java) {
        if (!::INSTANCE.isInitialized) {
            INSTANCE = Room.databaseBuilder(
                context.applicationContext,
                QuickSellDatabase::class.java,
                "quicksell_database"
            ).build()
        }
    }
    return INSTANCE
}