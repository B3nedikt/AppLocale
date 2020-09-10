package com.b3nedikt.reword.example

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import androidx.fragment.app.Fragment
import dev.b3nedikt.app_locale.AppLocale
import dev.b3nedikt.viewpump.ViewPump
import dev.b3nedikt.viewpump.ViewPumpContextWrapper

abstract class BaseFragment : Fragment() {

    override fun onResume() {
        super.onResume()

        ViewPump.setOverwriteContext(AppLocale.wrap(requireContext()))
    }

    override fun onGetLayoutInflater(savedInstanceState: Bundle?): LayoutInflater {
        val wrappedContext = ViewPumpContextWrapper.wrap(AppLocale.wrap(requireContext()))
        return wrappedContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
    }
}