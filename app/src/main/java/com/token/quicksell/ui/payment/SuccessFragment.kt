package com.token.quicksell.ui.payment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.token.quicksell.R
import com.token.quicksell.databinding.FragmentSuccessBinding


class SuccessFragment : Fragment() {

    lateinit var binding: FragmentSuccessBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {

        binding = FragmentSuccessBinding.inflate(inflater, container, false)

        binding.buttonOk.setOnClickListener(
            Navigation.createNavigateOnClickListener(R.id.action_successFragment_to_homeFragment)
        )

        return binding.root
    }
}