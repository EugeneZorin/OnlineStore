package com.example.catalog.presentation

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.catalog.R
import com.example.catalog.databinding.FragmentItemBinding

class CatalogAdapter: RecyclerView.Adapter<CatalogAdapter.ItemHolder>() {
    class ItemHolder(item: View): RecyclerView.ViewHolder(item) {
        private val binding = FragmentItemBinding.bind(item)
        fun bind() = with(binding){
            fragmentItem
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.fragment_item, parent, false)
        return ItemHolder(view)
    }

    override fun onBindViewHolder(holder: ItemHolder, position: Int) {
        holder.bind()
    }

    override fun getItemCount(): Int {
        return 10
    }

}