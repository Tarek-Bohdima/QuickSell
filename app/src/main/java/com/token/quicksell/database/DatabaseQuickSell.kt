package com.token.quicksell.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.token.quicksell.domain.Product
import com.token.quicksell.utils.Constants
import timber.log.Timber

@Entity(tableName = "quicksell_database")
data class DatabaseQuickSell constructor(
    @PrimaryKey
    val id: Long,
    val name: String,
    val category: String,
    val image: String,
)

fun List<DatabaseQuickSell>.asDomainModel(): List<Product> {
    Timber.tag(Constants.TAG).d("DatabaseQuickSell: asDomainModel() called")
    return map {
        Product(
            id = it.id,
            name = it.name,
            category = it.category,
            image = it.image
        )
    }
}

fun List<Product>.asDatabaseModel(): Array<DatabaseQuickSell> {
    Timber.tag(Constants.TAG).d("DatabaseQuickSell: asDatabaseModel() called")
    return map {
        DatabaseQuickSell(
            id = it.id,
            name = it.name,
            category = it.category,
            image = it.image
        )
    }.toTypedArray()
}