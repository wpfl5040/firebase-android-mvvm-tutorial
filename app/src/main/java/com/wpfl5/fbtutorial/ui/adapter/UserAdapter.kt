package com.wpfl5.fbtutorial.ui.adapter

import android.view.MenuInflater
import android.widget.PopupMenu
import androidx.recyclerview.widget.DiffUtil
import com.wpfl5.fbtutorial.R
import com.wpfl5.fbtutorial.databinding.RecyclerUserBinding
import com.wpfl5.fbtutorial.model.User
import com.wpfl5.fbtutorial.ui.base.BaseAdapter


class UserAdapter(
    private val onUpdateClickListener: (User) -> Unit,
    private val onDeleteClickListener: (User) -> Unit
) : BaseAdapter<User, RecyclerUserBinding>(
    object : DiffUtil.ItemCallback<User>() {
        override fun areItemsTheSame(oldItem: User, newItem: User): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: User, newItem: User): Boolean {
            return oldItem == newItem
        }
    }
) {
    override val layoutRes: Int = R.layout.recycler_user
    override fun onBindViewHolder(binding: RecyclerUserBinding, item: User) {
        binding.apply {
            user = item
            cardView.setOnClickListener { imgMenu.performClick() }
            imgMenu.setOnClickListener {
                val popup = PopupMenu(it.context, it)
                val inflater: MenuInflater = popup.menuInflater
                inflater.inflate(R.menu.menu_store, popup.menu)
                popup.setOnMenuItemClickListener { menuItem ->
                    when(menuItem.itemId) {
                        R.id.btn_update -> onUpdateClickListener(item)
                        R.id.btn_delete -> onDeleteClickListener(item)
                    }
                    return@setOnMenuItemClickListener false
                }
                popup.show()
            }
        }
    }
}