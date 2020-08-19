package com.wpfl5.fbtutorial.ui.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel

abstract class BaseFragment<VDB : ViewDataBinding, VM : ViewModel> : Fragment() {

  abstract val layoutRes: Int

  abstract val viewModel: VM
  lateinit var binding: VDB

  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View? {
    binding = DataBindingUtil.inflate(inflater, layoutRes, container, false)
    binding.lifecycleOwner = this
    return binding.root
  }

  fun showToast(textResource: Any) {
      if(textResource is Int) Toast.makeText(activity, textResource, Toast.LENGTH_SHORT).show()
      else if(textResource is String) Toast.makeText(activity, textResource, Toast.LENGTH_SHORT).show()
  }


}