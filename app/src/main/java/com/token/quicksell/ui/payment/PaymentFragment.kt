package com.token.quicksell.ui.payment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import com.token.quicksell.R
import com.token.quicksell.databinding.FragmentPaymentBinding


class PaymentFragment : Fragment() {
    private lateinit var binding: FragmentPaymentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentPaymentBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = viewLifecycleOwner

        binding.imageButtonCard.setOnClickListener(
            Navigation.createNavigateOnClickListener(R.id.action_paymentFragment_to_successFragment)
        )

        binding.imageButtonCash.setOnClickListener(
            Navigation.createNavigateOnClickListener(R.id.action_paymentFragment_to_successFragment)
        )

        binding.buttonCancel.setOnClickListener(
            Navigation.createNavigateOnClickListener(R.id.action_paymentFragment_to_homeFragment)
        )
        return binding.root
    }
}