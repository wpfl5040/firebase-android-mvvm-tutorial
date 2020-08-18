package com.wpfl5.fbtutorial.ui.main.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.wpfl5.fbtutorial.R
import com.wpfl5.fbtutorial.databinding.FragmentMainHomeBinding
import com.wpfl5.fbtutorial.ui.base.BaseFragment
import com.wpfl5.fbtutorial.ui.main.MainViewModel
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class MainHomeFragment : BaseFragment<FragmentMainHomeBinding, MainViewModel>(){
    override val layoutRes: Int = R.layout.fragment_main_home
    override val viewModel: MainViewModel by sharedViewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
            mainViewModel = viewModel

            btnFireStore.setOnClickListener {
                findNavController().navigate(MainHomeFragmentDirections.actionMainHomeFragmentToMainFirestoreFragment())
            }

        }
    }

}