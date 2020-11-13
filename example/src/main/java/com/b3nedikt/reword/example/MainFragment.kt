package com.b3nedikt.reword.example

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.appcompat.widget.ContentFrameLayout
import androidx.fragment.app.Fragment
import dev.b3nedikt.app_locale.AppLocale
import dev.b3nedikt.reword.Reword
import kotlinx.android.synthetic.main.fragment_main.*

class MainFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val localeStrings = AppLocale.supportedLocales.map { it.language + " " + it.country }
        val adapter = ArrayAdapter(requireContext(),
                android.R.layout.simple_dropdown_item_1line, localeStrings)

        spinner.adapter = adapter
        spinner.setSelection(AppLocale.supportedLocales.indexOf(AppLocale.currentLocale))
    }

    override fun onResume() {
        super.onResume()

        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                AppLocale.desiredLocale = AppLocale.supportedLocales[position]

                val rootView = requireActivity()
                        .window
                        .decorView
                        .findViewById<ContentFrameLayout>(android.R.id.content)

                Reword.reword(rootView)

                stringArrayTextView.text = resources.getStringArray(R.array.string_array)
                        .joinToString("\n")

                quantityStringTextView.text = (0 until 3)
                        .joinToString("\n")
                        { resources.getQuantityString(R.plurals.quantity_string, it, it) }
            }

            override fun onNothingSelected(parent: AdapterView<*>?) = Unit
        }
    }
}