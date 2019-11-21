package com.example.reserve.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.reserve.R
import com.example.reserve.databinding.MarketItemBinding
import com.example.reserve.network.model.Market

class MarketListAdapter : RecyclerView.Adapter<MarketListAdapter.MarketListHolder>(){

    private var marketList = ArrayList<Market>()

    fun setMarketList(marketList: ArrayList<Market>) {
        this.marketList = marketList
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = MarketListHolder(DataBindingUtil.inflate(
        LayoutInflater.from(parent.context), R.layout.market_item, parent, false
    ))

    override fun getItemCount() = marketList.size

    override fun onBindViewHolder(holder: MarketListHolder, position: Int) {
        holder.bind(marketList[position])
    }


    inner class MarketListHolder(private val binding: MarketItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Market) {
            binding.item = item
        }
    }
}