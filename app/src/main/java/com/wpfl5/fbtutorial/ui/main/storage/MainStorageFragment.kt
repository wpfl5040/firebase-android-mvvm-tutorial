package com.wpfl5.fbtutorial.ui.main.storage

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import com.wpfl5.fbtutorial.R
import com.wpfl5.fbtutorial.databinding.FragmentMainStorageBinding
import com.wpfl5.fbtutorial.model.FbResponse
import com.wpfl5.fbtutorial.ui.adapter.StorageAdapter
import com.wpfl5.fbtutorial.ui.base.BaseFragment
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainStorageFragment : BaseFragment<FragmentMainStorageBinding, StorageViewModel>() {
    override val layoutRes: Int = R.layout.fragment_main_storage
    override val viewModel: StorageViewModel by viewModel()
    companion object{
        private const val REQUEST_GALLERY_CODE = 100
    }
    private val adapter = StorageAdapter(
        {
            //download Click Listener
            downloadFile(it.path)
        },
        {
            //delete Click Listener
            deleteFile(it.path)
        }
    )

    override fun onStart() {
        super.onStart()
        getList()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
            storageViewModel = viewModel
            imgbtnAdd.setOnClickListener { goToGallery() }
            swipeRefresh.setOnRefreshListener { getList() }
            recyclerStorage.adapter = adapter

        }
    }

    private fun goToGallery(){
        val intent = Intent()
        intent.type = "image/*"
        intent.action = Intent.ACTION_GET_CONTENT
        startActivityForResult(Intent.createChooser(intent, "이미지를 선택하세요."), REQUEST_GALLERY_CODE)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(data != null && resultCode == Activity.RESULT_OK){
            if(requestCode == REQUEST_GALLERY_CODE){
                val filePath = data.data
                if(filePath != null) saveImg(filePath)
            }
        }
    }


    private fun saveImg(uri: Uri) {
        viewModel.saveImg(uri).observe(this, Observer { result ->
            when(result){
                is FbResponse.Loading -> { }
                is FbResponse.Success -> { showToast(R.string.success) }
                is FbResponse.Fail -> { showToast(result.e.message!!) }
            }
        })
    }

    private fun getList() {
        viewModel.getList().observe(viewLifecycleOwner, Observer { result ->
            binding.result = result
            when(result){
                is FbResponse.Loading -> { }
                is FbResponse.Success -> { adapter.submitList(result.data) }
                is FbResponse.Fail -> { showToast(result.e.message!!) }
            }
        })
    }

    private fun deleteFile(path: String){
        viewModel.deleteFile(path).observe(this, Observer { result ->
            when(result){
                is FbResponse.Loading -> { }
                is FbResponse.Success -> { getList() }
                is FbResponse.Fail -> { showToast(result.e.message!!) }
            }
        })
    }

    private fun downloadFile(path: String) {
        viewModel.downloadFile(path).observe(this, Observer { result ->
            when(result){
                is FbResponse.Loading -> { }
                is FbResponse.Success -> { showToast(R.string.success) }
                is FbResponse.Fail -> { showToast(result.e.message!!) }
            }
        })
    }

}