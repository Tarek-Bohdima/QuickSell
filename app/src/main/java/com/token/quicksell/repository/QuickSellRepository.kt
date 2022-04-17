package com.token.quicksell.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.token.quicksell.database.QuickSellDatabase
import com.token.quicksell.database.asDatabaseModel
import com.token.quicksell.database.asDomainModel
import com.token.quicksell.domain.Product
import com.token.quicksell.utils.Constants
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import timber.log.Timber

class QuickSellRepository(private val database: QuickSellDatabase) {

    fun getProducts(): LiveData<List<Product>> {
        Timber.d(Constants.TAG, "QuickSellRepository: getProducts() called")
        return Transformations.map(
            database.quickQuickSellDao.getProducts()
        ) {
            it.asDomainModel()
        }
    }

    suspend fun insertProducts(products: List<Product>) {
        withContext(Dispatchers.IO) {
            database.quickQuickSellDao.insertAll(*products.asDatabaseModel())
        }
        Timber.d(Constants.TAG, "QuickSellRepository: insertProducts() called")
    }
}