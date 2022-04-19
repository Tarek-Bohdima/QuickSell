package com.token.quicksell.ui.payment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupActionBarWithNavController
import com.token.quicksell.R
import com.token.quicksell.databinding.FragmentPaymentBinding


class PaymentFragment : Fragment() {
    private lateinit var binding: FragmentPaymentBinding

    private val backPressedDispatcher = object : OnBackPressedCallback(true) {
        override fun handleOnBackPressed() {
            // Redirect to our own function
            onBackPressed()
        }
    }

    // credits to https://stackoverflow.com/a/54029413/8899344
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setHasOptionsMenu(true)
        (requireActivity() as AppCompatActivity).apply {
            // Redirect system "Back" press to our dispatcher
            onBackPressedDispatcher.addCallback(viewLifecycleOwner, backPressedDispatcher)

            // Setup action bar to work with NavController
            setupActionBarWithNavController(findNavController())
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return if (item.itemId == android.R.id.home) {
            // Redirect "Up/Home" button clicks to our own function
            onBackPressed()
            true
        } else {
            super.onOptionsItemSelected(item)
        }
    }

    private fun onBackPressed() {
        findNavController().navigate(PaymentFragmentDirections.actionPaymentFragmentToHomeFragment())
    }

    override fun onDestroyView() {
        // It is optional to remove since our dispatcher is lifecycle-aware. But it wouldn't hurt to just remove it to be on the safe side.
        backPressedDispatcher.remove()
        super.onDestroyView()
    }

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

        val args = PaymentFragmentArgs.fromBundle(requireArguments())
        val price = args.totalPrice.toFloat()
        binding.priceAmount.text = getString(R.string.total_price_amount, price, "RON")
        Toast.makeText(requireActivity(), "totalPrice: ${args.totalPrice}", Toast.LENGTH_LONG)
            .show()

        return binding.root
    }

    private fun navigateToSuccessFragment(it: View) {
        it.findNavController()
            .navigate(PaymentFragmentDirections.actionPaymentFragmentToSuccessFragment())
    }
}