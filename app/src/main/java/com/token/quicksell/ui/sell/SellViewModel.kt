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

class SellViewModel(application: Application) : AndroidViewModel(application) {
    private val database = getDatabase(application)
    private val repository = QuickSellRepository(database)

    val products = repository.getProducts()

    private val productsList = listOf(
        Product(1,
            "Olive Oil",
            "Sauces and Oils",
            "https://d1fpwdq7hppksx.cloudfront.net/olive-oil.jpg"),
        Product(2, "Bread", "Bread and Bakery", "https://d1fpwdq7hppksx.cloudfront.net/bread.jpg"),
        Product(3, "Milk", "Dairy", "https://d1fpwdq7hppksx.cloudfront.net/milk.jpg"),
        Product(4, "Coffee", "Beverages", "https://d1fpwdq7hppksx.cloudfront.net/coffee.jpg"),
        Product(5, "Tea", "Beverages", "https://d1fpwdq7hppksx.cloudfront.net/tea.jpg"),
        Product(6, "Detergent", "Cleaners", "https://d1fpwdq7hppksx.cloudfront.net/detergent.jpg"),
        Product(7, "Shampoo", "Personal Care", "https://d1fpwdq7hppksx.cloudfront.net/shampoo.jpg"),
        Product(8, "Coca-Cola", "Beverages", "https://d1fpwdq7hppksx.cloudfront.net/coca-cola.jpg"),
        Product(9, "Apples", "Fruits", "https://d1fpwdq7hppksx.cloudfront.net/apples.jpg"),
        Product(10, "Tomatoes", "Vegetables", "https://d1fpwdq7hppksx.cloudfront.net/tomatoes.jpg"),
    )

    init {
        viewModelScope.launch {
            repository.insertProducts(productsList)
        }
        Timber.tag(Constants.TAG).d("SellViewModel: init() called")
    }

    fun onProductClicked(product: Product) {

    }

    /**
     * Factory for constructing SellViewModel with parameter
     */
    class Factory(val app: Application) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            Timber.tag(Constants.TAG).d(
                "SellViewModel: Factory: create() called with: modelClass = $modelClass")
            if (modelClass.isAssignableFrom(SellViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return SellViewModel(app) as T
            }
            throw IllegalArgumentException("Unable to construct viewmodel")
        }
    }
}