package com.token.quicksell.ui.payment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.token.quicksell.databinding.FragmentPaymentBinding


class PaymentFragment : Fragment() {
    private lateinit var binding: FragmentPaymentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentPaymentBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = viewLifecycleOwner

        binding.imageButtonCash.setOnClickListener {
            navigateToSuccessFragment(it)
        }

        binding.imageButtonCard.setOnClickListener {
            navigateToSuccessFragment(it)
        }
        binding.buttonCancel.setOnClickListener {
            it.findNavController()
                .navigate(PaymentFragmentDirections.actionPaymentFragmentToHomeFragment())
        }
        return binding.root
    }

    private fun navigateToSuccessFragment(it: View) {
        it.findNavController()
            .navigate(PaymentFragmentDirections.actionPaymentFragmentToSuccessFragment())
    }
}