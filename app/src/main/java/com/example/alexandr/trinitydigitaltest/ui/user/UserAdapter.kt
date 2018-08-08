package com.example.alexandr.trinitydigitaltest.ui.user

import android.databinding.DataBindingUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.alexandr.trinitydigitaltest.R
import com.example.alexandr.trinitydigitaltest.data.UserModel
import com.example.alexandr.trinitydigitaltest.databinding.RecyclerViewItemUserBinding

class UserAdapter : RecyclerView.Adapter<UserViewHolder>() {
    private val items: MutableList<UserModel> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val itemView = DataBindingUtil.inflate<RecyclerViewItemUserBinding>(inflater, R.layout.recycler_view_item_user, parent, false)
        return UserViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        holder.binding.user = items[position]
    }

    fun updateItems(newItems: List<UserModel>) {
        val oldSize = items.size
        items.clear()
        items.addAll(newItems)
        notifyItemRangeRemoved(0, oldSize)
        notifyItemRangeInserted(0, items.size)
    }
}

class UserViewHolder(val binding: RecyclerViewItemUserBinding) : RecyclerView.ViewHolder(binding.root)