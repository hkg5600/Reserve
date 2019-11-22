package com.example.reserve.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.reserve.R
import com.example.reserve.databinding.MenuItemBinding
import com.example.reserve.network.model.MarketMenu

class MenuListAdapter : RecyclerView.Adapter<MenuListAdapter.MenuListHolder>(){

    var menuList : ArrayList<MarketMenu> = ArrayList()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = MenuListHolder(DataBindingUtil.inflate(
        LayoutInflater.from(parent.context), R.layout.menu_item, parent, false
    ))

    override fun getItemCount() = menuList.size

    override fun onBindViewHolder(holder: MenuListHolder, position: Int) {
        if (onItemClickListener != null) {
            holder.makeReservation.setOnClickListener { v ->
                onItemClickListener?.onClick(v, position, holder)
            }
        }
        holder.bind(menuList[position])
    }


    inner class MenuListHolder(private val binding: MenuItemBinding) : RecyclerView.ViewHolder(binding.root) {
        val makeReservation = binding.holderLayout

        fun bind(item: MarketMenu) {
            binding.item = item
        }
    }

    var onItemClickListener: OnItemClickListener? = null
    interface OnItemClickListener {
        fun onClick(
            view: View,
            position: Int,
            holder: MenuListHolder
        )
    }
}