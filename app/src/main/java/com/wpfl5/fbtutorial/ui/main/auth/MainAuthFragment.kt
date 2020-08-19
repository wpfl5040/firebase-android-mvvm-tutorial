package com.wpfl5.fbtutorial.ui.main.auth

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import com.wpfl5.fbtutorial.R
import com.wpfl5.fbtutorial.databinding.FragmentMainAuthBinding
import com.wpfl5.fbtutorial.model.AuthUser
import com.wpfl5.fbtutorial.model.FbResponse
import com.wpfl5.fbtutorial.ui.base.BaseFragment
import org.koin.androidx.viewmodel.ext.android.viewModel


class MainAuthFragment : BaseFragment<FragmentMainAuthBinding, AuthViewModel>() {
    override val layoutRes: Int = R.layout.fragment_main_auth
    override val viewModel: AuthViewModel by viewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
            authViewModel = viewModel
            btnRegister.setOnClickListener { btnClick("register") }
            btnLogin.setOnClickListener { btnClick("login") }
            btnReset.setOnClickListener { resetBtnClick() }
        }
    }

    private fun resetBtnClick(){
        with(binding) {
            inputEmail.error = null
            val email = inputEmail.editText?.text?.toString()
            when{
                email.isNullOrEmpty() -> inputEmail.error = "Please enter email"
                else -> resetObserver(email)
            }
        }
    }

    private fun btnClick(check: String){
        with(binding){
            inputEmail.error = null
            inputPassword.error = null
            val email = inputEmail.editText?.text?.toString()
            val pwd = inputPassword.editText?.text?.toString()
            when {
                email.isNullOrEmpty() -> inputEmail.error = "Please enter email"
                pwd.isNullOrEmpty() -> inputPassword.error = "Please enter password"
                else -> {
                    val authUser = AuthUser(email, pwd)
                    if(check=="register") registerObserver(authUser)
                    else if(check=="login") loginObserver(authUser)
                }
            }
        }
    }

    private fun registerObserver(authUser: AuthUser) {
        viewModel.register(authUser).observe(viewLifecycleOwner, Observer {result ->
            when(result){
                is FbResponse.Loading -> { }
                is FbResponse.Success -> { showToast(R.string.success) }
                is FbResponse.Fail -> { showToast(result.e.message!!) }
            }
        })
    }

    private fun loginObserver(authUser: AuthUser){
        viewModel.login(authUser).observe(viewLifecycleOwner, Observer { result ->
            when(result){
                is FbResponse.Loading -> { }
                is FbResponse.Success -> { showToast(R.string.success) }
                is FbResponse.Fail -> { showToast(result.e.message!!) }
            }
        })
    }

    private fun resetObserver(email: String){
        viewModel.resetPassword(email).observe(viewLifecycleOwner, Observer { result ->
            when(result){
                is FbResponse.Loading -> { }
                is FbResponse.Success -> { showToast(R.string.success_resetPwd) }
                is FbResponse.Fail -> { showToast(result.e.message!!) }
            }
        })
    }


}