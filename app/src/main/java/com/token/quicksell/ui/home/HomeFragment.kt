package com.token.quicksell.ui.home

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.core.content.edit
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.token.quicksell.MainActivity
import com.token.quicksell.R
import com.token.quicksell.databinding.FragmentHomeBinding


class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        setSpinner()
        binding.buttonExit.setOnClickListener {
            activity?.finish()
        }

//        binding.buttonQuickSell.setOnClickListener { view: View ->
//            Navigation.findNavController(view).navigate(R.id.action_homeFragment_to_quickSellFragment)
//        }
        binding.buttonQuickSell.setOnClickListener(
            Navigation.createNavigateOnClickListener(R.id.action_homeFragment_to_quickSellFragment)
        )

        return binding.root
    }

    private fun setSpinner() {
        val spinner = binding.spinnerLanguage
        ArrayAdapter.createFromResource(
            requireContext(),
            R.array.Languages,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spinner.adapter = adapter
        }

        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long,
            ) {
                val prefs =
                    requireActivity().getSharedPreferences(getString(R.string.shared_preference_key),
                        Context.MODE_PRIVATE)
                        ?: return
                when (position) {
                    0 -> {}
                    1 -> {
                        prefs.edit { putString(getString(R.string.saved_language_key), "en") }
                            .also { recreateFragment() }
                    }
                    2 -> {
                        prefs.edit { putString(getString(R.string.saved_language_key), "ro") }
                            .also { recreateFragment() }
                    }
                }
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
            }
        }
    }

    private fun recreateFragment() {
        requireActivity().onBackPressed()
        val intent = Intent(requireActivity(), MainActivity::class.java)
        startActivity(intent)
    }
}