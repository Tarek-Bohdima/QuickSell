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
    private lateinit var viewModel: SellViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentQuickSellBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = viewLifecycleOwner

        viewModel = ViewModelProvider(this)[SellViewModel::class.java]

        binding.buttonPay.setOnClickListener(
            Navigation.createNavigateOnClickListener(R.id.action_quickSellFragment_to_paymentFragment)
        )

        return binding.root
    }
}