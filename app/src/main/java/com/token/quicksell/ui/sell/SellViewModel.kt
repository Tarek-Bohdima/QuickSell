package com.token.quicksell.ui.sell

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.token.quicksell.database.getDatabase
import com.token.quicksell.domain.Product
import com.token.quicksell.repository.QuickSellRepository
import com.token.quicksell.utils.Constants
import kotlinx.coroutines.launch
import timber.log.Timber
import java.lang.IllegalArgumentException

class SellViewModel(application: Application) : AndroidViewModel(application) {
    private val database = getDatabase(application)
    private val repository = QuickSellRepository(database)

    val products = repository.getProducts()

    val productsList = listOf<Product>(
        Product(1, "Olive Oil", "Sauces and Oils", ""),
        Product(2, "Bread", "Bread and Bakery", ""),
        Product(3, "Milk", "Dairy", ""),
        Product(4, "Coffee", "Beverages", ""),
        Product(5, "Tea", "Beverages", ""),
        Product(6, "Detergent", "Cleaners", ""),
        Product(7, "Shampoo", "Personal Care", ""),
        Product(8, "Coca-Cola", "Beverages", ""),
        Product(9, "Apples", "Fruits", ""),
        Product(10, "Tomatoes", "Vegetables", ""),
    )

    init {
        viewModelScope.launch {
            repository.insertProducts(productsList)
        }
        Timber.d(Constants.TAG, "SellViewModel: init() called")
    }

    /**
     * Factory for constructing SellViewModel with parameter
     */
    class Factory(val app: Application) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            Timber.d(Constants.TAG,  "SellViewModel: Factory: create() called with: modelClass = $modelClass")
            if (modelClass.isAssignableFrom(SellViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return SellViewModel(app) as T
            }
            throw IllegalArgumentException("Unable to construct viewmodel")
        }
    }
}