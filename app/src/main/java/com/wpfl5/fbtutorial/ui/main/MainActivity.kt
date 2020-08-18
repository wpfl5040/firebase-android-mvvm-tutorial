package com.wpfl5.fbtutorial.ui.main

import android.os.Bundle
import com.wpfl5.fbtutorial.R
import com.wpfl5.fbtutorial.databinding.ActivityMainBinding
import com.wpfl5.fbtutorial.ui.base.BaseActivity
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : BaseActivity<ActivityMainBinding, MainViewModel>() {
    override val layoutRes: Int = R.layout.activity_main
    override val viewModel: MainViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.apply {
            mainViewModel = viewModel
        }
    }
}