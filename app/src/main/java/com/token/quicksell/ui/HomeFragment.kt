package com.token.quicksell.ui

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.core.content.edit
import androidx.fragment.app.Fragment
import com.token.quicksell.R
import com.token.quicksell.databinding.FragmentHomeBinding


class HomeFragment : Fragment() {
    lateinit var binding: FragmentHomeBinding
    val prefs: SharedPreferences? = activity?.getPreferences(Context.MODE_PRIVATE)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        setSpinner()
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
                when (position) {
                    0 -> {}
                    1 -> {
                        prefs?.edit { putString("LANGUAGE", "en") }

                    }
                    2 -> {
                        prefs?.edit { putString("LANGUAGE", "ro") }
                    }
                }
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
                TODO("Not yet implemented")
            }
        }
    }
}