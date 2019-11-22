package com.example.reserve.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.reserve.R
import com.example.reserve.databinding.OrderItemBinding
import com.example.reserve.network.model.Order

class ReservationListAdapter  : RecyclerView.Adapter<ReservationListAdapter.ReservationListHolder>(){
    var orderList : ArrayList<Order> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ReservationListHolder(
        DataBindingUtil.inflate(
        LayoutInflater.from(parent.context), R.layout.order_item, parent, false
    ))

    override fun getItemCount() = orderList.size

    override fun onBindViewHolder(holder: ReservationListHolder, position: Int) {

        if (onItemClickListener != null) {
            holder.buttonDetail.setOnClickListener { v ->
                onItemClickListener?.onClick(v, position, holder)
            }
        }

        holder.bind(orderList[position])
    }


    inner class ReservationListHolder(private val binding: OrderItemBinding) : RecyclerView.ViewHolder(binding.root) {

        val buttonDetail: TextView = binding.buttonDetail

        fun bind(item: Order) {
            binding.item = item
        }
    }

    var onItemClickListener: OnItemClickListener? = null
    interface OnItemClickListener {
        fun onClick(
            view: View,
            position: Int,
            holder: ReservationListHolder
        )
    }
}