package com.token.quicksell.ui.home

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView
import com.token.quicksell.R
import com.token.quicksell.domain.Country

class SpinnerArrayAdapter(context: Context, countryList: List<Country>) :
    ArrayAdapter<Country>(context, 0, countryList) {
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        return initView(position, convertView, parent)
    }

    override fun getDropDownView(position: Int, convertView: View?, parent: ViewGroup): View {
        return initView(position, convertView, parent)
    }

    private fun initView(position: Int, convertView: View?, parent: ViewGroup): View {

        val country = getItem(position)
        val view = convertView ?: LayoutInflater.from(context)
            .inflate(R.layout.spinner_list_item, parent, false)
        val language: TextView = view.findViewById(R.id.spinner_row_language)
        language.text = country!!.language
        val flag: ImageView = view.findViewById(R.id.spinner_row_flag)
        flag.setImageResource(country.flag)

        return view
    }
}