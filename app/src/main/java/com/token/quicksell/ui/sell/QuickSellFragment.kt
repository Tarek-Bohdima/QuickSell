package com.token.quicksell.ui.sell

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.token.quicksell.R
import com.token.quicksell.databinding.FragmentQuickSellBinding


class QuickSellFragment : Fragment() {

    private lateinit var binding: FragmentQuickSellBinding
    private val viewModel: SellViewModel by lazy {
        val activity = requireNotNull(this.activity) {
            "You can only access the viewModel after onViewCreated()"
        }
        ViewModelProvider(
            this,
            SellViewModel.Factory(activity.application)
        )[SellViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentQuickSellBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = viewLifecycleOwner

        val adapter = ProductsAdapter()

        binding.recyclerviewIncluded.recyclerviewProducts.adapter = adapter

        viewModel.products.observe(viewLifecycleOwner) {
            adapter.data = it
        }

        binding.buttonPay.setOnClickListener(
            Navigation.createNavigateOnClickListener(R.id.action_quickSellFragment_to_paymentFragment)
        )

        return binding.root
    }
}