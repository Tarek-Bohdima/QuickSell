package com.token.quicksell.ui.sell

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.token.quicksell.databinding.ItemListProductsBinding
import com.token.quicksell.domain.Product


class ProductsAdapter(private val clickListener: ProductListener) :
    ListAdapter<Product, ProductsAdapter.ProductItemViewHolder>(ProductDiffCallback()) {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): ProductItemViewHolder {
        return ProductItemViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: ProductItemViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item, clickListener)
    }

    class ProductItemViewHolder private constructor(
        private val viewDataBinding: ItemListProductsBinding,
    ) :
        RecyclerView.ViewHolder(viewDataBinding.root) {
        fun bind(product: Product, clickListener: ProductListener) {
            viewDataBinding.product = product
            viewDataBinding.clickListener = clickListener
            viewDataBinding.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup): ProductItemViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val itemBinding = ItemListProductsBinding.inflate(
                    layoutInflater,
                    parent, false
                )
                return ProductItemViewHolder(itemBinding)
            }
        }
    }
}

class ProductDiffCallback : DiffUtil.ItemCallback<Product>() {
    override fun areItemsTheSame(oldItem: Product, newItem: Product): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Product, newItem: Product): Boolean {
        return oldItem == newItem
    }

}

class ProductListener(val clickListener: (product: Product) -> Unit) {
    fun onClick(product: Product) = clickListener(product)
}