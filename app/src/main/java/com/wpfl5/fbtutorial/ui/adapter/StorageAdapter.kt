package com.wpfl5.fbtutorial.ui.adapter

import android.view.MenuInflater
import android.widget.PopupMenu
import androidx.recyclerview.widget.DiffUtil
import com.wpfl5.fbtutorial.R
import com.wpfl5.fbtutorial.databinding.RecyclerStorageBinding
import com.wpfl5.fbtutorial.model.StorageResponse
import com.wpfl5.fbtutorial.ui.base.BaseAdapter

class StorageAdapter (
    private val onDownloadClickListener: (StorageResponse) -> Unit,
    private val onDeleteClickListener: (StorageResponse) -> Unit
) : BaseAdapter<StorageResponse, RecyclerStorageBinding>(
    object : DiffUtil.ItemCallback<StorageResponse>() {
        override fun areItemsTheSame(oldItem: StorageResponse, newItem: StorageResponse): Boolean {
            return oldItem.path == newItem.path
        }

        override fun areContentsTheSame(oldItem: StorageResponse, newItem: StorageResponse): Boolean {
            return oldItem == newItem
        }
    }
) {
    override val layoutRes: Int = R.layout.recycler_storage
    override fun onBindViewHolder(binding: RecyclerStorageBinding, item: StorageResponse) {
        binding.apply {
            storage = item
            cardView.setOnClickListener { imgMenu.performClick() }
            imgMenu.setOnClickListener {
                val popup = PopupMenu(it.context, it)
                val inflater: MenuInflater = popup.menuInflater
                inflater.inflate(R.menu.menu_storage, popup.menu)
                popup.setOnMenuItemClickListener { menuItem ->
                    when(menuItem.itemId) {
                        R.id.btn_download -> onDownloadClickListener(item)
                        R.id.btn_delete -> onDeleteClickListener(item)
                    }
                    return@setOnMenuItemClickListener false
                }
                popup.show()
            }
        }
    }
}