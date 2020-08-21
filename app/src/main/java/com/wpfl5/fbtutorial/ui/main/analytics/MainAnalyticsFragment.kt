package com.wpfl5.fbtutorial.ui.main.analytics

import android.os.Bundle
import android.view.View
import com.wpfl5.fbtutorial.R
import com.wpfl5.fbtutorial.databinding.FragmentMainAnalyticsBinding
import com.wpfl5.fbtutorial.ui.base.BaseFragment
import org.koin.androidx.viewmodel.ext.android.viewModel


class MainAnalyticsFragment : BaseFragment<FragmentMainAnalyticsBinding, AnalyticsViewModel>(){
    override val layoutRes: Int = R.layout.fragment_main_analytics
    override val viewModel: AnalyticsViewModel by viewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
            analyticsViewModel = viewModel
            btnSetCustomEvent.setOnClickListener {
                inputEvent.error = null

                val name = inputEvent.editText?.text?.toString()

                if(name.isNullOrBlank()) inputEvent.error = "Enter event name"
                else viewModel.customEvent(name)
            }
        }
    }
}