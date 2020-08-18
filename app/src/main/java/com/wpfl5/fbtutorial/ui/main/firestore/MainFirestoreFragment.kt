package com.wpfl5.fbtutorial.ui.main.firestore

import android.os.Bundle
import android.view.MenuInflater
import android.view.View
import android.widget.PopupMenu
import androidx.lifecycle.Observer
import com.wpfl5.fbtutorial.R
import com.wpfl5.fbtutorial.databinding.FragmentMainFirestoreBinding
import com.wpfl5.fbtutorial.model.FbResponse
import com.wpfl5.fbtutorial.model.User
import com.wpfl5.fbtutorial.ui.adapter.UserAdapter
import com.wpfl5.fbtutorial.ui.base.BaseFragment
import org.koin.androidx.viewmodel.ext.android.viewModel


class MainFirestoreFragment : BaseFragment<FragmentMainFirestoreBinding, FireStoreViewModel>(){
    override val layoutRes: Int = R.layout.fragment_main_firestore
    override val viewModel: FireStoreViewModel by viewModel()

    private val adapter = UserAdapter(
        {
            //btn_update Listener
            updateUser(it)
        },
        {
            //btn_delete Listener
            deleteUserData(it.id)
        }
    )

    override fun onStart() {
        super.onStart()
        getData()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
            firestoreViewModel = viewModel
            recyclerUser.adapter = adapter
            swipeRefresh.setOnRefreshListener { getData() }
            imgbtnAdd.setOnClickListener { createUser() }
        }
    }

    private fun getData() {
        viewModel.userList().observe(viewLifecycleOwner, Observer { result ->
            binding.result = result
            when(result){
                is FbResponse.Loading -> {  }
                is FbResponse.Success -> { adapter.submitList(result.data) }
                is FbResponse.Fail -> {  }
            }
        })
    }

    private fun deleteUserData(id: String) {
        viewModel.deleteUser(id).observe(this, Observer { result ->
            binding.result = result
            when(result){
                is FbResponse.Loading -> {  }
                is FbResponse.Success -> { getData() }
                is FbResponse.Fail -> { }
            }
        })
    }

    private fun updateUser(user: User) {
        //TODO : Please set user data
        val setUserData = User(user.id, "updateEmail@gmail.com", "updateName", user.created)
        viewModel.updateUser(setUserData).observe(this, Observer {
            when(it){
                is FbResponse.Loading -> { }
                is FbResponse.Success -> { getData() }
                is FbResponse.Fail -> { }
            }
        })
    }

    private fun createUser() {
        //TODO : Please set user data
        val user = User("createEmail@gmail.com", "createName")
        viewModel.createUser(user).observe(viewLifecycleOwner, Observer {result ->
            when(result) {
                is FbResponse.Loading -> { }
                is FbResponse.Success -> { getData() }
                is FbResponse.Fail -> { }
            }
        })

    }



}