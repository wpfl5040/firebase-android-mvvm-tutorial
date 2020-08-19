package com.wpfl5.fbtutorial.ui.main.crashlytics

import android.os.Bundle
import android.view.View
import com.wpfl5.fbtutorial.R
import com.wpfl5.fbtutorial.databinding.FragmentMainCrashlyticsBinding
import com.wpfl5.fbtutorial.ui.base.BaseFragment
import org.koin.androidx.viewmodel.ext.android.viewModel


class MainCrashlyticsFragment : BaseFragment<FragmentMainCrashlyticsBinding, CrashlyticsViewModel>(){
    override val layoutRes: Int = R.layout.fragment_main_crashlytics
    override val viewModel: CrashlyticsViewModel by viewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.crashViewModel = viewModel
    }
}